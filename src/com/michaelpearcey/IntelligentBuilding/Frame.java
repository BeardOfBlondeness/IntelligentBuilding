package com.michaelpearcey.IntelligentBuilding;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.GREEN;

public class Frame extends Application {

    private final int width = 1200, height = 800;
    private final int paddingTop = 5, paddingSide = 2;
    private static final int canvasX = 5, canvasY = 50, canvasWidth = 850, canvasHeight = 780;
    private final String title = "Intelligent Building";
    public static GraphicsContext gc;
    public static Stage primaryStage;
    private boolean firstClick = true;
    private Building activeBuilding;
    private static BorderPane root;
    private Menu mFile, mConfig, mHelp;
    private static Text t;
    private MenuItem mNew, mSave, mLoad, mExit, mSettings, mClear, mWhat, mAbout;

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle(title);
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        gc = canvas.getGraphicsContext2D();
        activeBuilding = new Building();
        activeBuilding.fillDefault();
        activeBuilding.addPerson();
        activeBuilding.draw();
        canvas.setTranslateX(canvasX);
        canvas.setTranslateY(canvasY);
        root = new BorderPane();
        root.setPadding(new Insets(paddingTop, paddingSide, paddingTop, paddingSide));
        root.setTop(setMenu());
        root.getChildren().add(canvas);
        t = TextBuilder.create().text(activeBuilding.toString()).build();
        t.setX(950);
        t.setY(50);
        root.getChildren().add(t);
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
        canvas.setOnMouseClicked(event -> {
            if(firstClick) {
                activeBuilding.setStart((int)(event.getX()/20)*20, (int)(event.getY()/20)*20);
                firstClick = false;
            } else {
                activeBuilding.setEnd((int)(event.getX()/20), (int)(event.getY()/20));
                firstClick = true;
            }
        });
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

    public static void setText(String text) {
        t = TextBuilder.create().text(text).build();
        t.setX(950);
        t.setY(50);
        root.getChildren().add(t);
    }

    void newBuilding() {
        clearBuilding();
    }

    void saveBuilding() {
        activeBuilding.toFile();
    }

    void loadBuilding() {
        activeBuilding.fromFile();
    }

    void showSettings() {}

    void clearBuilding() {
        activeBuilding.clear();
    }

    void showHelp() {}

    void showAbout() {}

    public static void clearGC() {
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
    }

}
