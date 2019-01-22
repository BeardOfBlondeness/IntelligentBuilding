package com.michaelpearcey.IntelligentBuilding;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Building extends ArrayList<Room> implements Serializable {

    public Building() {}

    public void addRooms(ArrayList<Room> r) { addAll(r); }

    public void draw() { for(Room r : this) r.draw(); }

    public void fillDefault() {
        add(new Room(0, 0, 10, 10, new Point(2, 2)));
        add(new Room(10, 0, 10, 10, new Point(2, 3)));
        add(new Room(0, 14, 10, 10, new Point(0, 5)));
    }

    public void toFile() {
        Stage stage = Frame.primaryStage;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (
                    OutputStream fileO = new FileOutputStream( file.getAbsolutePath() + ".building");
                    OutputStream buffer = new BufferedOutputStream(fileO);
                    ObjectOutput output = new ObjectOutputStream(buffer)
            ){ output.writeObject(this); }
            catch(IOException ex){ System.out.println("Cannot perform output." + ex); }
        }

    }

}
