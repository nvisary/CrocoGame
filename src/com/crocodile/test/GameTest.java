package com.crocodile.test;

import com.crocodile.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sgfromrus on 07.2017
 */
class GameTest {
    private Game game;
    @BeforeEach
    void setUp() {
        game = new Game(new JFrame("test"));
        game.getState().setSecretWord("Apple");
    }

    @Test
    void isGoodSecretWord() {
        assertTrue(game.checkWord("apple"));
        assertFalse(game.checkWord("banana"));
        assertFalse(game.checkWord("яблоко"));
    }

}