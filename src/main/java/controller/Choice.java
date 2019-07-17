package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import model.Board;
import model.Classic;
import model.HumanPlayer;
import model.Player;
import view.View;

public enum Choice {
    EASY, MEDIUM, HARD, MULTIPLAYER, EXIT;

    public static void handleChoice(Choice choice, Group root, Scene scene,
                                    String p1, String p2) {
        Controller controller = null;
//        controller.startView();
        switch (choice) {
            case EASY:
//                controller.easy();
                break;
            case MEDIUM:
//                controller.medium();
                break;
            case HARD:
//                controller.hard();
                break;
            case MULTIPLAYER:
                Player player1 = new HumanPlayer(p1);
                Player player2 = new HumanPlayer(p2);
                controller = new Controller(new View(root, scene),
                        new Classic(new Board(),
                                player1, player2));
                break;
            case EXIT:
                Runtime.getRuntime().exit(1);
                break;
        }
        controller.startGame();
    }
}
