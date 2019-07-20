package ir.shayandaneshvar.controller;

import ir.shayandaneshvar.model.*;
import ir.shayandaneshvar.view.View;
import javafx.scene.Group;
import javafx.scene.Scene;

public enum Choice {
    EASY, MEDIUM, HARD, MULTIPLAYER, EXIT;

    public static void handleChoice(Choice choice, Group root, Scene scene,
                                    String p1, String p2) {
        Controller controller = null;
        Player player1 = new HumanPlayer(p1);
        Player player2 = null;
//        ir.shayandaneshvar.controller.startView();
        switch (choice) {
            case EASY:
                player2 = new RandomPlayer("Random Player");
                break;
            case MEDIUM:
//                ir.shayandaneshvar.controller.medium();
                break;
            case HARD:
//                ir.shayandaneshvar.controller.hard();
                break;
            case MULTIPLAYER:
                player2 = new HumanPlayer(p2);
                break;
            case EXIT:
                Runtime.getRuntime().exit(1);
                break;
        }
        controller = new Controller(new View(root, scene),
                new Classic(new Board(),
                        player1, player2));
        controller.startGame();
    }
}
