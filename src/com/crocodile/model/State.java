package com.crocodile.model;

import java.util.ArrayList;

public class State {
    private ArrayList<Point> points;
    private ArrayList<String> words;
    private String secretWord = "";
    private int port;

    public State() {
        points = new ArrayList<>();
        words = new ArrayList<>();
    }
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    private String serverIp = "";


    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }
    public ArrayList<String> getWords() {
        return words;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
}
