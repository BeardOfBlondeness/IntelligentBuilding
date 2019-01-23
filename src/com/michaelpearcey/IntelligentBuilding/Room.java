package com.michaelpearcey.IntelligentBuilding;

import javafx.scene.canvas.GraphicsContext;
import java.awt.*;
import java.io.Serializable;
import java.util.Random;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.WHITE;

public class Room implements Serializable {

    private final int multiplier = 20, lineWidth = 20, offset = 10;
    private int x, y, width, height;
    private Point door;
    private Person p;

    public Room(int x, int y, int width, int height, Point door) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.door = door;
    }

    public Point addPerson() {
        Random ran = new Random();
        int ranX = ran.nextInt(width-2);
        int ranY = ran.nextInt(height-2);
        int xPos = (x+ranX+1) * multiplier;
        int yPos = (y+ranY+1) * multiplier;
        return new Point(xPos, yPos);
    }

    public int ranX() {
        Random ran = new Random();
        return (ran.nextInt(width-2) + 1 + x)*multiplier;
    }

    public int ranY() {
        Random ran = new Random();
        return (ran.nextInt(height-2) + 1 + y)*multiplier;
    }

    public String toString() {
        String ret = "";
        ret = ret + "Room at: " + Integer.toString(x) + "," + Integer.toString(y) + " to " + Integer.toString(x+width) + "," + Integer.toString(y+height);
        switch(door.x) {
            case 0:
                ret = ret + "\nDoor on top wall at: ";
                break;
            case 1:
                ret = ret + "\nDoor on right wall at: ";
                break;
            case 2:
                ret = ret + "\nDoor on bottom wall at: ";
                break;
            case 3:
                ret = ret + "\nDoor on left wall at: ";
                break;
        }
        ret = ret + Integer.toString(door.y);
        return ret;
    }

    public void draw() {
        GraphicsContext gc = Frame.gc;
        gc.setStroke(BLUE);
        gc.setLineWidth(lineWidth);
        gc.strokeLine(x*multiplier+offset, y*multiplier+offset, (x+width)*multiplier+offset, y*multiplier+offset);
        gc.strokeLine(x*multiplier+offset, y*multiplier+offset, x*multiplier+offset, (y+height)*multiplier+offset);
        gc.strokeLine((x+width)*multiplier+offset, y*multiplier+offset, (x+width)*multiplier+offset, (y+height)*multiplier+offset);
        gc.strokeLine(x*multiplier+offset, (y+height)*multiplier+offset, (x+width)*multiplier+offset, (y+height)*multiplier+offset);
        drawDoor();
    }

    private void drawDoor() {
        GraphicsContext gc = Frame.gc;
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

    public Point getDoorNextCoords() {
        switch(door.x) {
            case 0:
                return new Point((door.y+x)*multiplier+lineWidth/2, (y+1)*multiplier);
            case 1:
                return new Point((x+width-1)*multiplier, (door.y+y)*multiplier+lineWidth/2);
            case 2:
                return new Point((door.y+x)*multiplier+lineWidth/2, (y+height-1)*multiplier);
            case 3:
                return new Point((x+1)*multiplier, (door.y+y)*multiplier+lineWidth/2);
            default:
                return new Point(0, 0);
        }
    }

    public Point getDoorCoords() {
        switch(door.x) {
            case 0:
                return new Point((door.y+x)*multiplier+lineWidth/2, (y)*multiplier);
            case 1:
                return new Point((x+width)*multiplier, (door.y+y)*multiplier+lineWidth/2);
            case 2:
                return new Point((door.y+x)*multiplier+lineWidth/2, (y+height)*multiplier);
            case 3:
                return new Point((x)*multiplier, (door.y+y)*multiplier+lineWidth/2);
            default:
                return new Point(0, 0);
        }
    }
}
