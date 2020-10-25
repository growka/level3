package server.handler;

import server.inter.DBService;
import server.inter.Server;
import server.service.DBServiceImpl;
import server.service.UserEntity;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClientHandler {

    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Date date = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy k:mm:ss");

    private UserEntity user;

    public String getNick() {
        return user.getName();
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.user = user;
            new Thread(() -> {
                try {
                    long a = System.currentTimeMillis();
                    while ((a - System.currentTimeMillis()) > -120000) {
                        if (authentication()) {
                            readMessage();
                        } else {
                            System.out.println("Клиент не авторизовался в течении 120 секунд");
                        }
                    }

                } catch (IOException e) {
                    System.out.println("Потеряна связь с клиентом " + user.getName());
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    private boolean authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String [] dataArray = str.split("\\s");
                user = server.getAuthService().getUser(dataArray[1], dataArray[2]);
                if (user != null) {
                    if (!server.isNickBusy(user.getName())) {
                        sendMsg("/authOk " + user.getName());
                        this.user = user;
                        server.subcribe(this);
                        server.broadcastMsg(this.user.getName() + " зашёл в чат.");
                        return true;
                    } else {
                        sendMsg("Учетная запись уже используется!");
                        return false;
                    }
                } else {
                    sendMsg("Неверный логин/пароль");
                    System.out.println("Неудачная аутентификация пользователя " + dataArray[1] + " с паролем " + dataArray[2]);
                    return false;
                }
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(sdf.format(new Date()) + " " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void readMessage() throws IOException {
        while (true) {
            String clientStr = in.readUTF();
            if (clientStr.startsWith("/")) {
                if (clientStr.equals("/exit")) {
                    return;
                }
                if (clientStr.startsWith("/clients")) {
                    server.broadcastClientList();
                }
                if (clientStr.startsWith("/w")) {
                    String [] strArray = clientStr.split("\\s");
                    String nickname = strArray[1];
                    String msg = clientStr.substring(4 + nickname.length());
                    server.sndMsgToClient(this,nickname,msg);
                }
                if (clientStr.startsWith("/setname")) {
                    String [] strArray = clientStr.split("\\s");
                    String oldName = user.getName();
                    DBServiceImpl dbService = new DBServiceImpl();
                    dbService.updateUserName(user, strArray[1]);
                    server.broadcastMsg(oldName + " сменил ник на " + user.getName());
                }
                    continue;
            }
            server.broadcastMsg(user.getName() + ": " + clientStr);
        }

    }

    public void closeConnection() {
        server.unsubcribe(this);
        server.broadcastMsg(user.getName() + " вышел из чата.");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

