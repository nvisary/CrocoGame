package com.crocodile.view;

import javax.swing.*;
import java.awt.*;

public class PicMsg extends JPanel {
    private String text = "";

    public PicMsg() {
        super();
    }

    public void paintText(String text) {
        this.text = text;
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        //Для "Четкой графики"
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        g2.setStroke(new BasicStroke((float) 3));
        g2.setFont(new Font("Calibri", Font.ITALIC, 20));
        g2.drawOval(200, 135, 10, 10);
        g2.drawOval(180, 100, 20, 20);
        g2.drawString(text, 110, 50);
        g2.drawOval(100, 1, 100, 100);
    }
}
