package com.crocodile.model;

import com.crocodile.io.Client;
import com.crocodile.io.LoadSecretWord;
import com.crocodile.io.LoadSettings;
import com.crocodile.io.Server;
import com.crocodile.view.ChatPanelServer;
import com.crocodile.view.PaintPanelClient;
import com.crocodile.view.StartFrame;
import com.crocodile.view.Styles;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game  {
    private Server server;
    private Client client;
    private State state;
    private JFrame frame;
    private ChatPanelServer chatServer;
    private PaintPanelClient paintPanelClient;

    public Game(JFrame frame) {
        state = new State();
        this.frame = frame;
    }

    public void startServerGame() {

        //Загрузка настроек
        loadSettings();

        //Создание и запуск сервера
        server = new Server(this);
        Thread serverThread = new Thread(server);
        serverThread.start();

        loadSecretWord();
    }

    public void startClientGame() {
        loadSettings();

        client = new Client(this);

        Thread clientThread = new Thread(client);
        clientThread.start();
    }

    public String getSecretWord() {
        return state.getSecretWord();
    }

    public void sendMessageToServer(String msg) {
        client.sendMsg(msg);
    }
    public boolean checkWord(String word) {
        if (isGoodSecretWord(word) == 0) {
            server.sendWordIsGuessed();
            wordIsGuessed();
            return true;
        }
        return false;
    }
    public void wordIsGuessed() {
        /*JOptionPane.showMessageDialog(null, "Слово угадано", "Конец игры", JOptionPane.INFORMATION_MESSAGE);*/
        int rezult;
       rezult = JOptionPane.showConfirmDialog(null,
                "Начать новую игру?",
                "Слово угадано",
                JOptionPane.YES_NO_OPTION);
        if (server!= null)
            server.shutDown();
        if (client != null)
            client.shutDown();
       if (rezult == JOptionPane.OK_OPTION) {
           frame.setVisible(false);
           StartFrame startFrame = new StartFrame(Styles.startFrameTitle);
       } else {
           System.exit(0);
       }
    }

    public void loadSettings() {
        try {
            LoadSettings loadSettings = new LoadSettings("res/settings.txt");
            loadSettings.loadSettingsFromFile();
            state.setPort(loadSettings.getServerPort());
            state.setServerIp(loadSettings.getServerIP());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int isGoodSecretWord(String word) {
        return state.getSecretWord().compareToIgnoreCase(word);
    }

    public void loadSecretWord() {
        try {
            LoadSecretWord loadSecretWord = new LoadSecretWord("res/words.txt", this);
            loadSecretWord.loadWordsFromFile();
            state.setSecretWord(loadSecretWord.getRandomWorld());
        } catch (FileNotFoundException e) {
            System.out.println("файл words.txt не найден!");
        }
    }
    public State getState() {
        return state;
    }

    public Server getServer() {
        return server;
    }

    public void setChat(ChatPanelServer chat) {
        this.chatServer = chat;
    }

    public int getPort() {
        return state.getPort();
    }

    public void setPaintPanelClient(PaintPanelClient paintPanelClient) {
        this.paintPanelClient = paintPanelClient;
    }

    public void showMessageOnChat(String msg) {
        if (chatServer != null) {
            chatServer.showMsg(msg);
        }
    }

    public String getIP() {
        return state.getServerIp();
    }

    public PaintPanelClient getPaintPanelClient() {
        return paintPanelClient;
    }
}
