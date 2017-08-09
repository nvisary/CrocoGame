package com.crocodile.test;

import com.crocodile.io.LoadSecretWord;
import com.crocodile.model.Game;
import com.crocodile.model.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


class LoadSecretWordTest {
    LoadSecretWord loadSecretWord;

    @BeforeEach
    void setUp() {

        loadSecretWord = new LoadSecretWord("res/words.txt", new Game(new JFrame()));
    }

    @Test
    void loadWordsFromFile() {
        try {
            loadSecretWord.loadWordsFromFile();
        } catch (FileNotFoundException e) {
            fail("file not found");
        }
    }

    @Test
    void getRandomWorld() {
        assertNotNull(loadSecretWord.getRandomWorld());
    }

}