package server.service;

import org.apache.log4j.Logger;
import server.handler.ClientHandler;
import server.inter.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ServerImpl implements Server {

    private List<ClientHandler> clients;
    private AuthServiceImpl authService;
    public static final Logger logger = Logger.getLogger(ServerImpl.class);

    public ServerImpl() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            authService = new AuthServiceImpl();
            authService.start();
            clients = new LinkedList<>();
            while (true) {
                System.out.println("Ожидание подключения..");
                logger.info("Сервер запущен, ожидание подключения...");
                Socket socket = serverSocket.accept();
                System.out.println("Клиент подлючился!");
                logger.info("Подключился клиент.");
                new ClientHandler(this, socket);

            }
        } catch (IOException e) {
            System.out.println("Проблемы на сервере");
            logger.error("Не удалось запустить сервер.");
        } finally {
            if (authService!=null) {
                authService.stop();
                logger.info("Сервер остановлен.");
            }
        }
    }

    @Override
    public boolean isNickBusy(String nick) {
        for (ClientHandler c : clients) {
            if (c.getNick()!=null && (c.getNick().equals(nick))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void broadcastClientList() {
        StringBuilder builder = new StringBuilder();
        for (ClientHandler c:clients) {
            builder.append(c.getNick()).append(" ");
        }
        broadcastMsg(builder.toString());
    }

    @Override
    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler c : clients) {
            c.sendMsg(msg);
        }

    }

    @Override
    public synchronized void subcribe(ClientHandler client) {
        clients.add(client);
    }

    @Override
    public synchronized void unsubcribe(ClientHandler client) {
        clients.remove(client);
    }

    @Override
    public synchronized void sndMsgToClient(ClientHandler from, String to, String msg) {
        for (ClientHandler c : clients) {
            if (c.getNick().equals(to)) {
                c.sendMsg("from " + from.getNick()+ ":" + msg);
                from.sendMsg("client " + to + ": " + msg);
            }
        }
    }

    @Override
    public AuthServiceImpl getAuthService() {
        return authService;
    }
}
