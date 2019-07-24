package ir.shayandaneshvar.controller;

import ir.shayandaneshvar.model.Actions.Act;
import ir.shayandaneshvar.model.Direction;
import ir.shayandaneshvar.model.Games.Game;
import ir.shayandaneshvar.model.Position;
import ir.shayandaneshvar.view.View;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;


public class Controller {
    private Game game;

    public Controller(View view, Game game) {
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
                game.updateObservers();
                game.run();
            }
        };
        timer.scheduleAtFixedRate(task, 5, 700);
    }
}
