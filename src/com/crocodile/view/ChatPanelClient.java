package com.crocodile.view;

import com.crocodile.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanelClient extends JPanel implements ActionListener{
    private JTextField userInputField;
    private JTextArea textArea;
    private JButton btnSend;
    private JScrollPane scrollPane;
    private String msg = "";
    private Game game;

    public ChatPanelClient(Game game) {
        super();
        this.game = game;

        setLayout(null);
        setBackground(Color.white);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(Styles.chatTextAreaFont);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setSize(350, 349);
        scrollPane.setLocation(0, 0);

        btnSend = new JButton();
        btnSend.setSize(60, 49);
        btnSend.setLocation(292, 349);
        btnSend.addActionListener(this);

        userInputField = new JTextField();
        userInputField.setFont(Styles.chatTextInputFont);
        userInputField.setLocation(1, 349);
        userInputField.setSize(290, 50 );
        userInputField.addActionListener(this);

        add(userInputField);
        add(btnSend);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        msg = userInputField.getText();
        if (!checkWord(msg)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Word can only contain English letters",
                    "Error!",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        SwingUtilities.invokeLater(() -> {
            textArea.append(msg + "\n");
            userInputField.setText("");
        });
        game.sendMessageToServer(msg);
    }

    public boolean checkWord(String word) {
        boolean retValue = true;
        if (word.equals(""))
            retValue = false;
        for (int i = 0; i < word.length();i++ ) {
            char ch = word.charAt(i);
            if (ch < 'A' || ch > 'z') {
                retValue = false;
            }
        }
        return retValue;
    }
    



}
