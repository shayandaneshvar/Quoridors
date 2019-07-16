package view;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Game;
import model.Observable;
import model.Position;


public class View implements Observer {
    private Stage primaryStage;
    private Scene scene;
    private static Rectangle[][] cells;
    private static Rectangle[][] intersections;
    private static Rectangle[][] verticalCorridors;
    private static Rectangle[][] horizontalCorridors;

    public View(Stage primaryStage, Scene scene) {
        this.primaryStage = primaryStage;
        this.scene = scene;
        cells = new Rectangle[9][9];
        intersections = new Rectangle[8][8];
        verticalCorridors = new Rectangle[9][8];
        horizontalCorridors = new Rectangle[8][9];
    }

    public static void drawMainMenu(Scene scene) {

    }


    private void drawBoard(Game game) {
        // TODO: 7/16/2019
    }

    @Override
    public void update(Observable observable) {
        drawBoard((Game) observable);
    }

}
