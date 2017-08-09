package com.crocodile.io;

import com.crocodile.model.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class LoadSecretWord {
    private String fileName;
    private Scanner in;
    private Random rnd;
    private Game game;
    public LoadSecretWord(String fileName, Game game){
        this.fileName = fileName;
        rnd = new Random();
        this.game = game;
    }
    public void loadWordsFromFile() throws FileNotFoundException {
        in = new Scanner(new File(fileName));
        while (in.hasNext()) {
            if (in.hasNext()) {
                game.getState().getWords().add(in.next());
            }
        }
    }
    public String getRandomWorld() {
        if (!game.getState().getWords().isEmpty()) {
            return game.getState().getWords().get(rnd.nextInt(game.getState().getWords().size()));
        }
        return "";
    }

}
