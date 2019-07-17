package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Act;
import model.Direction;
import model.Game;
import model.Position;
import view.View;

import java.util.Timer;
import java.util.TimerTask;


public class Controller {
    private View view;
    private Game game;

    public Controller(View view, Game game) {
        this.view = view;
        this.game = game;
        game.addObserver(view);
        game.updateObservers();
    }

    public static void initialize(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 840, 840, true,
                SceneAntialiasing.BALANCED);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Quoridors");
        scene.setFill(Color.GRAY);
        View.drawMainMenu(scene, root);
    }

    public static Triple<Act, Position, Direction> handleInputs() {
        System.out.println("Handling Input");
        return View.getInputs();
    }

    public void startGame() {
        System.out.println("startGame");
        game.updateObservers();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                game.run();
            }
        };
        timer.scheduleAtFixedRate(task, 5, 1);
    }
}
