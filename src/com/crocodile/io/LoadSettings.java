package com.crocodile.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadSettings {
    private String fileName;
    private String serverIP = "";

    public int getServerPort() {
        return serverPort;
    }

    public String getServerIP() {
        return serverIP;
    }

    private int serverPort = 0;

    public LoadSettings(String fileName){
        this.fileName = fileName;
    }

    public void loadSettingsFromFile() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(fileName)));
        serverIP = properties.getProperty("serverIP");
        serverPort = Integer.valueOf(properties.getProperty("serverPort"));
        System.out.println(serverIP + ":" + serverPort);

    }



    // Загрузка настроек игры из файла
    //если это клиент то порт сервера и ip адрес сервера
    //если это сервер то только порт сервера(на разных компьютерах разные порты вбывают заняты, поэтому
    // задав статический порт мы можем сломать игру на одном из компьютеров, если этот порт будет уже занят)
}
