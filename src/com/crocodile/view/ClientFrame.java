package com.crocodile.view;

import com.crocodile.io.LoadSettings;
import com.crocodile.model.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClientFrame extends JFrame {
    private PaintPanelClient paint;
    private JPanel chat;
    private LoadSettings loadSettings;
    private Game game;
    public ClientFrame() {

        game = new Game(this);
        setFrameParametrs();

        setPaintPanelParametrs();

        setChatParametrs();

        game.startClientGame();
    }

    private void setFrameParametrs() {
        setTitle(Styles.clientFrameTitle);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Styles.widthWindow, Styles.heightWindow);
        setLocation(Styles.xLocationFrame, Styles.yLocationFrame);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Styles.backgroundFrameColor);
        setVisible(true);
    }
    private void setPaintPanelParametrs() {
        paint = new PaintPanelClient();
        game.setPaintPanelClient(paint);

        paint.setBackground(new Color(223, 222, 241));
        paint.setLocation(Styles.xLocationPaintPanel, Styles.yLocationPaintPanel);
        paint.setSize(Styles.paintPanelWidth, Styles.paintPanelHeight);

        getContentPane().add(paint);
    }
    private void setChatParametrs() {
        chat = new ChatPanelClient(game);
        chat.setSize(350, 400);
        chat.setLocation(550, 100);
        getContentPane().add(chat);
    }
}
