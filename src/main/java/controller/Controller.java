package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Action;
import model.Game;
import view.View;


public class Controller {
    private View view;
    private Game game;

    public Controller(View view, Game game) {
        this.view = view;
        this.game = game;
    }

    public static void initialize(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 796, 910, true,
                SceneAntialiasing.BALANCED);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Quoridors");
        scene.setFill(Color.GRAY);
        View.drawMainMenu(scene);
    }

}
