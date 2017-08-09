package com.crocodile.view;

import com.crocodile.model.Game;

import javax.swing.*;

public class ServerFrame extends JFrame {
    private PaintPanelServer paint;
    private ChatPanelServer chat;
    private LabelPanel lblPanel;

    private Game game;
    public ServerFrame () {
        game = new Game(this);
        game.startServerGame();

        setFrameParametrs();
        setChatParametrs();
        setPaintPanelParametrs();
        setLabelParametrs();
    }

    private void setFrameParametrs() {
        setTitle(Styles.serverFrameTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Styles.widthWindow, Styles.heightWindow);
        setLocation(Styles.xLocationFrame, Styles.yLocationFrame);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Styles.backgroundFrameColor);
    }

    private void setLabelParametrs() {
        lblPanel = new LabelPanel(game.getSecretWord());
        lblPanel.setSize(Styles.labelWidth,Styles.labelHeight);
        lblPanel.setLocation(Styles.xLocationLabel, Styles.yLocationLabel);
        getContentPane().add(lblPanel);
    }

    private void setPaintPanelParametrs() {
        paint = new PaintPanelServer(game);
        paint.setSize(Styles.paintPanelWidth, Styles.paintPanelHeight);
        paint.setLocation(Styles.xLocationPaintPanel, Styles.yLocationPaintPanel);
        getContentPane().add(paint);
    }

    private void setChatParametrs() {
        chat = new ChatPanelServer();
        game.setChat(chat);
        chat.setSize(Styles.chatWidthServer, Styles.chatHeightServer);
        chat.setLocation(Styles.xLocationChat, Styles.yLocationChat);
        getContentPane().add(chat);
    }
}
