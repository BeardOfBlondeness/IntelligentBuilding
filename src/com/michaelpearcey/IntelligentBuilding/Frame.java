package com.michaelpearcey.IntelligentBuilding;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.*;

public class Frame extends Application {

    private final String title = "Intelligent Building";
    private final int width = 1200, height = 800;
    private final int paddingTop = 5, paddingSide = 2;
    private final int canvasX = 5, canvasY = 50, canvasWidth = 850, canvasHeight = 780;
    private BorderPane root;
    private Menu mFile, mConfig, mHelp;
    private MenuItem mNew, mSave, mLoad, mExit, mSettings, mClear, mWhat, mAbout;

    public static void main(String[] args) {
        Application.launch();
    }

    private void drawShapes(GraphicsContext gc) {
        gc.setFill(GREEN);
        gc.setStroke(BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(title);
        Button btn = new Button();
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        canvas.setTranslateX(canvasX);
        canvas.setTranslateY(canvasY);
        root = new BorderPane();
        root.setPadding(new Insets(paddingTop, paddingSide, paddingTop, paddingSide));
        root.setTop(setMenu());
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }

    /**
     * set up the menu of commands for the GUI
     * @return the menu bar
     */
    MenuBar setMenu() {
        MenuBar menuBar = new MenuBar();
        mFile = new Menu("File");
        mConfig = new Menu("Configuration");
        mHelp = new Menu("Help");
        mNew = new MenuItem("New");
        mSave = new MenuItem("Save");
        mLoad = new MenuItem("Load");
        mExit = new MenuItem("Exit");
        mSettings = new MenuItem("Settings");
        mClear = new MenuItem("Clear Building");
        mWhat = new MenuItem("Help");
        mAbout = new MenuItem("About");
        mFile.getItems().addAll(mNew, mSave, mLoad, mExit);
        mConfig.getItems().addAll(mSettings, mClear);
        mHelp.getItems().addAll(mWhat, mAbout);
        menuBar.getMenus().addAll(mFile, mConfig, mHelp);
        setMenuButtons();
        return menuBar;
    }

    void setMenuButtons() {
        mAbout.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                showAbout();
            }
        });
        mWhat.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                showHelp();
            }
        });
        mClear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                clearBuilding();
            }
        });
        mSettings.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                showSettings();
            }
        });
        mLoad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                loadBuilding();
            }
        });
        mSave.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                saveBuilding();
            }
        });
        mNew.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                newBuilding();
            }
        });
        mExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
    }

    void newBuilding() {}

    void saveBuilding() {}

    void loadBuilding() {}

    void showSettings() {}

    void clearBuilding() {}

    void showHelp() {}

    void showAbout() {}

}
