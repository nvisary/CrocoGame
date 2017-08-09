package com.crocodile.view;

import com.crocodile.model.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PaintPanelClient extends JPanel  {
    private Graphics myGraphics;
    private ArrayList<com.crocodile.model.Point> arrayList;

    public PaintPanelClient() {
        super();
        arrayList = new ArrayList<>();
    }

    public void myPaint() {
        myGraphics = getGraphics();
        Graphics2D g2 = (Graphics2D) myGraphics;
        g2.setStroke(new BasicStroke((float)3));
        drawPoints(g2);
    }
    private synchronized void drawPoints(Graphics2D g2) {
        if (!arrayList.isEmpty()) {
            for (int i = 0; i < arrayList.size(); i++) {
                com.crocodile.model.Point p = arrayList.get(i);
                g2.setColor(p.color);
                g2.drawLine(p.x1, p.y1, p.x2, p.y2);
            }
            arrayList.clear();
        }
    }
    public synchronized void addToArray(Point p) {
        arrayList.add(p);
    }
}