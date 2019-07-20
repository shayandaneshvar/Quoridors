package ir.shayandaneshvar.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Controller.initialize(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}