package com.michaelpearcey.IntelligentBuilding;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.WHITE;

public class Room {

    private final int multiplier = 20, lineWidth = 20, offset = 10;
    private int x, y, width, height;
    private Point door;
    private GraphicsContext gc;

    public Room(int x, int y, int width, int height, Point door) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.door = door;
        gc = Frame.gc;
    }

    public Room() {}

    public int getX() { return x; }

    public void draw() {
        gc.setStroke(BLUE);
        gc.setLineWidth(lineWidth);
        gc.strokeLine(x*multiplier+offset, y*multiplier+offset, (x+width)*multiplier+offset, y*multiplier+offset);
        gc.strokeLine(x*multiplier+offset, y*multiplier+offset, x*multiplier+offset, (y+height)*multiplier+offset);
        gc.strokeLine((x+width)*multiplier+offset, y*multiplier+offset, (x+width)*multiplier+offset, (y+height)*multiplier+offset);
        gc.strokeLine(x*multiplier+offset, (y+height)*multiplier+offset, (x+width)*multiplier+offset, (y+height)*multiplier+offset);
        drawDoor();
    }

    private void drawDoor() {
        gc.setFill(WHITE);
        switch(door.x) {
            case 0:
                gc.fillRect((x+door.y)*multiplier+offset, y*multiplier-(lineWidth/2)+offset, lineWidth, lineWidth);
                break;
            case 1:
                gc.fillRect((x+width)*multiplier+offset-(lineWidth/2), (y+door.y)*multiplier+offset, lineWidth, lineWidth);
                break;
            case 2:
                gc.fillRect((x+door.y)*multiplier+offset, (y+height)*multiplier+offset-(lineWidth/2), lineWidth, lineWidth);
                break;
            case 3:
                gc.fillRect(x*multiplier+offset-(lineWidth/2), (y+door.y)*multiplier+offset, lineWidth, lineWidth);
                break;
        }
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
