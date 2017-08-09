package com.crocodile.view;

import javax.swing.*;

public class LabelPanel extends JPanel{
    private JLabel lblword;
    private String word;
    public LabelPanel(String word) {
        super();
        this.word = word;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        lblword = new JLabel();
        lblword.setForeground(Styles.labelColor);
        lblword.setFont(Styles.labelFont);
        lblword.setText("Draw: " + word);
        lblword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(lblword);
    }
}
