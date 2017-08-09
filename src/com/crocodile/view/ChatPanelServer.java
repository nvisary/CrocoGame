package com.crocodile.view;

import javax.swing.*;
import java.awt.*;

public class ChatPanelServer extends JPanel {
    private JTextArea textArea;
    private JScrollPane scrollPane;
    public ChatPanelServer() {
        super();

        setLayout(null);
        setBackground(Color.white);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(Styles.chatTextAreaFont);
        textArea.setBackground(new Color(223,222,241));
        scrollPane = new JScrollPane(textArea);
        scrollPane.setSize(350, 350);
        scrollPane.setLocation(0, 0);

        add(scrollPane);
    }
    public void showMsg(String msg) {
        SwingUtilities.invokeLater(() -> textArea.append(msg + "\n"));
    }


}
