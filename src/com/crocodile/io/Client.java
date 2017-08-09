package com.crocodile.io;

import com.crocodile.model.Game;
import com.crocodile.model.Point;
import com.crocodile.view.PaintPanelClient;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
    private Game game ;
    private int port;
    private String ip;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private PaintPanelClient paintPanelClient;

    public Client(Game game){
        this.game = game;
    }
    @Override
    public void run() {
        port = game.getPort();
        ip = game.getIP();
        paintPanelClient = game.getPaintPanelClient();

        try {
            socket = new Socket(ip, port);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                Point point = (Point) objectInputStream.readObject();
                if (point.cleanPaintPanel) {
                    paintPanelClient.repaint();
                    continue;
                }
                if (point.wordIsCorrect) {
                    game.wordIsGuessed();
                    break;
                }

                paintPanelClient.addToArray(point);

                SwingUtilities.invokeLater(() -> paintPanelClient.myPaint());
            }
        } catch (IOException e) {
            System.out.println("Server lost");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not get object");
        }
    }
    public void sendMsg(String msg) {
        if (objectOutputStream != null) {
            try {
                objectOutputStream.writeObject(msg);
            } catch (IOException e) {
                System.out.println("Could not send message");
            }
        }
    }

    public void shutDown() {
        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


