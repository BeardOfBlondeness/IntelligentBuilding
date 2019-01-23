package com.michaelpearcey.IntelligentBuilding;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

import static javafx.scene.paint.Color.RED;

public class Person implements Serializable {

    private final int size = 20;
    private int x, y;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw() {
        GraphicsContext gc = Frame.gc;
        gc.setFill(RED);
        gc.fillOval(x, y, size, size);
    }

    public void moveTo(int xt, int yt) {
        if(xt-x > 0) x++;
        else if(xt-x < 0) x--;
        if(yt-y > 0) y++;
        else if(yt-y < 0) y--;
    }
}
