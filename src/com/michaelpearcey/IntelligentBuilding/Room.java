package com.michaelpearcey.IntelligentBuilding;

import java.awt.*;

public class Room {

    private int x, y, width, height;
    private Point door;

    public Room(int x, int y, int width, int height, Point door) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.door = door;
    }

    public Room() {}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point getDoor() {
        return door;
    }

    public void setDoor(Point door) {
        this.door = door;
    }
}
