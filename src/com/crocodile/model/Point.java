package com.crocodile.model;

import java.awt.*;
import java.io.Serializable;

public class Point implements Serializable   {
    public int x1 = -1, x2 = -1, y1 = -1, y2 = -1;
    public boolean cleanPaintPanel = false;
    public boolean wordIsCorrect = false;
    public Color color = Color.black;

    public Point(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public Point(){}
}
