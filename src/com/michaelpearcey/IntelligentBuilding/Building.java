package com.michaelpearcey.IntelligentBuilding;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.paint.Color.GREEN;

public class Building extends ArrayList<Room> implements Serializable {

    private Person p;
    private int targetX, targetY, targetRoom, xS, yS;
    private int toRoom = 0;
    private boolean greenFlashing = false;
    private ArrayList<Item> items;

    public Building() {items = new ArrayList<Item>();}

    public void addPerson() {
        Random ran = new Random();
        int roomId = ran.nextInt(size());
        targetRoom = roomId;
        targetX = get(roomId).getDoorNextCoords().x;
        targetY = get(roomId).getDoorNextCoords().y;
        Point pPoint = get(roomId).addPerson();
        p = new Person(pPoint.x, pPoint.y);
        runThread();
    }

    public void addRooms(ArrayList<Room> r) { addAll(r); }

    public void draw() {
        for(Room r : this) r.draw();
        if(greenFlashing) {
            GraphicsContext gc = Frame.gc;
            gc.setFill(GREEN);
            gc.fillRect(xS, yS, 20, 20);
        }
        if(items.size() > 0) {
            for(Item i : items) i.draw();
        }
    }

    public void fillDefault() {
        add(new Room(0, 0, 10, 10, new Point(2, 2)));
        add(new Room(12, 0, 10, 10, new Point(2, 3)));
        add(new Room(0, 14, 10, 10, new Point(0, 5)));
    }

    public String toString() {
        String ret = "";
        for(int i = 0; i < size(); i++) { ret = ret + get(i).toString() + "\n\n"; }
        return ret;
    }

    public void toFile() {
        Stage stage = Frame.primaryStage;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Building");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (
                    OutputStream fileO = new FileOutputStream(file.getAbsolutePath() + ".building");
                    OutputStream buffer = new BufferedOutputStream(fileO);
                    ObjectOutput output = new ObjectOutputStream(buffer)
            ) {
                output.writeObject(this);
            } catch (IOException ex) {
                System.out.println("Cannot perform output." + ex);
            }
        }
    }

    public void fromFile() {
        clear();
        Stage stage = Frame.primaryStage;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Building");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                System.out.println(file.getAbsolutePath());
                FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                Object obj = objectIn.readObject();
                System.out.println("The Object has been read from the file");
                addRooms((Building)obj);
                objectIn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void runThread() {
        AnimationTimer timer;
        timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                Frame.clearGC();
                draw();
                updateTarget();
                p.moveTo(targetX, targetY);
                p.draw();
            }
        };
        timer.start();
    }

    private void newTargetRoom() {
        Random r = new Random();
        targetRoom = r.nextInt(size());
        targetX = get(targetRoom).getDoorCoords().x;
        targetY = get(targetRoom).getDoorCoords().y;
        toRoom = 1;
    }

    private void updateTarget() {
        if(size() == 1) targetRoom = 0;
        if(size() > 0) {
            Room r = get(targetRoom);
            if(p.getX() == targetX && p.getY() == targetY) {
                if(toRoom == 0) {
                    if (targetX == r.getDoorNextCoords().x && targetY == r.getDoorNextCoords().y) {
                        targetX = r.getDoorCoords().x;
                        targetY = r.getDoorCoords().y;
                    } else if (targetX == r.getDoorCoords().x && targetY == r.getDoorCoords().y) {
                        newTargetRoom();
                    }
                } else if(toRoom == 1) {
                    ranPosInRoom();
                } else if(toRoom == 2) {
                    Random ran = new Random();
                    int ranItem = ran.nextInt(3);
                    switch(ranItem) {
                        case 0: items.add(new Light(targetX, targetY)); break;
                        case 1: items.add(new Table(targetX, targetY)); break;
                        case 2: items.add(new Toilet(targetX, targetY)); break;
                    }
                    targetX = get(targetRoom).getDoorNextCoords().x;
                    targetY = get(targetRoom).getDoorNextCoords().y;
                    toRoom = 0;
                }
            }
        }
    }

    public void setStart(int xS, int yS) {
        greenFlashing = true;
        this.xS = xS;
        this.yS = yS;
    }

    public void setEnd(int xE, int yE) {
        greenFlashing = false;
        Random r = new Random();
        int doorInt = 0;
        if(yE < 15) doorInt = 2;
        else if(yS/20 > 10) doorInt = 0;
        int doorPos = r.nextInt(3) + 2;
        add(new Room(xS/20, yS/20, xE-(xS/20), yE-(yS/20), new Point(doorInt, doorPos)));
        Frame.setText(toString());
    }

    void ranPosInRoom() {
        targetX = get(targetRoom).ranX();
        targetY = get(targetRoom).ranY();
        toRoom = 2;
    }
}
