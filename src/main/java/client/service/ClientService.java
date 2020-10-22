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
    JTextField sendField = new JTextField(45);
    JTextArea chatWindow = new JTextArea(15, 50);
    public JButton sendButton = new JButton("Send");

    public void startGraphic() {

        JFrame jFrame = new JFrame("ChatClient v0.1");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel chatPanel = new JPanel();
        chatWindow.setLineWrap(true);
        chatWindow.setWrapStyleWord(true);
        chatWindow.setEditable(false);

        JScrollPane jScrollPane = new JScrollPane(chatWindow);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        sendButton.addActionListener(new SendButtonListener());
        sendField.addActionListener(new SendButtonListener());

        chatPanel.add(chatWindow);
        chatPanel.add(jScrollPane);

        chatPanel.add(sendField);
        chatPanel.add(sendButton);

        jFrame.setSize(600, 330);

        jFrame.getContentPane().add(chatPanel, FlowLayout.LEFT);

        jFrame.setVisible(true);
    }

    public class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ex) {
            String strField = sendField.getText();
            if (strField.startsWith("/auth")) {
                startChat();
                try {
                    out.writeUTF(sendField.getText());
                    sendField.setText("");
                    sendField.requestFocus();
                } catch (IOException e) {
                    e.printStackTrace();}
            } else {
                try {
                    out.writeUTF(sendField.getText());
             } catch (IOException e) {
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
            e.printStackTrace();
        }
    }


    public void setAutorized(boolean b) {

    }
}
