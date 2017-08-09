package com.crocodile.io;

import com.crocodile.model.*;
import com.crocodile.model.Point;

import java.awt.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private boolean serverRun = false;
    private Game game;
    private int port;
    private State state;

    public Server (Game game){
        this.game = game;
        this.port = game.getPort();
        this.state = game.getState();
    }
    @Override
    public void run() {
        try {
            //запуск сервера и ожидание клиентов
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();

            //Установка потоков передачи точек
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            serverRun = true;
        } catch (IOException e) {
            System.out.println("Ошибка не удалось подключится или установить поток с клиентом");
        }
         receiveMessages();
    }

    private void receiveMessages() {
        while(true) {
            try {
                String msg = (String) objectInputStream.readObject();

                game.checkWord(msg);
                game.showMessageOnChat(msg);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    public void sendWordIsGuessed() {
        Point p = new Point();
        p.wordIsCorrect = true;
        sendPoint(p);
    }
    public void addPoint(int x1, int y1, int x2, int y2, Color color){
        Point p = new Point(x1, y1, x2, y2);
        p.color = color;
        state.getPoints().add(p);
    }

    public boolean isServerRun(){
        return serverRun;
    }
    public void sendAllPoints(){
        if (!state.getPoints().isEmpty()) {
            for (int i = 0; i < state.getPoints().size(); i++) {
                Point p = state.getPoints().get(i);
                sendPoint(p);
            }
            //state.getPoints().clear();
        }
    }
    public void sendPoint(com.crocodile.model.Point p) {
        try {
            if (objectOutputStream != null) {
                objectOutputStream.writeObject(p);
            }
        } catch (IOException e1) {
            System.out.println("Не удалось отправить поинт");
        }
    }

    public void sendClearPaintPanel() {
        Point p = new Point();
        p.cleanPaintPanel = true;
        sendPoint(p);
    }

    public void shutDown() {
        if (serverRun) {
            try {
                serverSocket.close();
                socket.close();
                objectOutputStream.close();
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
