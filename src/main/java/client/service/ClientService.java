package client.service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientService extends JFrame {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String myNick;
    private String title = "Chat v0.2";
    JTextField sendField = new JTextField();
    JTextArea chatWindow = new JTextArea();
    public JButton sendButton = new JButton("Send");


    public ClientService() {

        setTitle(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(50,50,400,600);

        setLayout(new BorderLayout());

        JPanel chat = new JPanel();
        JPanel input = new JPanel();

        chatWindow.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(chatWindow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        sendButton.addActionListener(new SendButtonListener());
        sendField.addActionListener(new SendButtonListener());

        chat.setLayout(new BoxLayout(chat, BoxLayout.X_AXIS));
        chat.add(sendField);
        chat.add(sendButton);

        input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
        input.add(chatWindow);

        getContentPane().add(chat, BorderLayout.SOUTH);
        getContentPane().add(input, BorderLayout.CENTER);

        setVisible(true);

    }


    public class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ex) {
            String strField = sendField.getText();
            if (strField.startsWith("/auth")) {
                try {
                    startChat();
                    out.writeUTF(sendField.getText());
                    sendField.setText("");
                    sendField.requestFocus();
                } catch (Exception e) {
                    chatWindow.append("Не удалось авторизоваться.");}
            } else {
                try {
                    out.writeUTF(sendField.getText());
                } catch (IOException e) {
                    chatWindow.append("Не удалось авторизоваться.");
                    e.printStackTrace();
                }
                sendField.setText("");
                sendField.requestFocus();
            }
        }
    }

    public void startChat() {
        myNick = "";

        try {
            socket = new Socket("localhost", 8002);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAutorized(false);


            Thread t1 = new Thread(() -> {
                try {
                    while (true) {
                        String strMsg = in.readUTF();
                        chatWindow.append(strMsg + "\n");
                        if (strMsg.startsWith("/authOk")) {
                            setAutorized(true);
                            //title = "+";
                            //setTitle("Подключился!");
                            //chatWindow.append(socket.getLocalAddress().toString() + ":" + socket.getPort());
                            myNick = strMsg.split("\\s")[1];
                            break;
                        }
                    }
                    while (true) {
                        String strMsg = in.readUTF();
                        if (strMsg.equals("/exit")) {
                            break;
                        }
                        chatWindow.append(strMsg + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    setAutorized(false);
                    try {
                        socket.close();
                        myNick = "";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            t1.setDaemon(true);
            t1.start();
        } catch (IOException e) {
            // System.out.println("Нет соединения с сервером. Попробуйте ещё раз");
            chatWindow.append("Нет соединения с сервером. Попробуйте ещё раз" + "\n");
            //e.printStackTrace();
        }
    }


    public void setAutorized(boolean b) {

    }
}
