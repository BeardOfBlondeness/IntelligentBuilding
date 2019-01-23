package com.michaelpearcey.IntelligentBuilding;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.Serializable;

public class Item implements Serializable {
    @FXML
    private int x, y;
    private Image image;

    public Item(int x, int y, String path) {
        this.x = x;
        this.y = y;
        System.out.println(path);
        image = new Image(getClass().getResourceAsStream(path));
    }

    public void draw() {
        GraphicsContext gc = Frame.gc;
        gc.drawImage(image, x, y, 20, 20);
        System.out.println("Drawing item: " + image.toString());
    }
}
