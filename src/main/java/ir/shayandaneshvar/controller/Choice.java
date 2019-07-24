package ir.shayandaneshvar.controller;

import ir.shayandaneshvar.model.*;
import ir.shayandaneshvar.view.View;
import javafx.scene.Group;
import javafx.scene.Scene;

public enum Choice {
    EASY, MEDIUM, HARD, MULTIPLAYER, TOURNAMENT, EXIT;

    public static void handleChoice(Choice choice, Group root, Scene scene,
                                    String play1, String play2) {
        Controller controller;
        Player player1 = new HumanPlayer(play1);
        Player player2 = null;
        //        ir.shayandaneshvar.controller.startView();
        switch (choice) {
            case EASY:
                player2 = new RandomPlayer("Random Player");
                break;
            case MEDIUM:
                player2 = new MediumAI("Computer");
                break;
            case HARD:
                // TODO: 7/20/2019  
//                ir.shayandaneshvar.controller.hard();
                break;
            case MULTIPLAYER:
            case TOURNAMENT:
                player2 = new HumanPlayer(play2);
                break;
            case EXIT:
                Runtime.getRuntime().exit(1);
                break;
                default:
                    System.err.println("Impossible Situation Occurred");
        }
        if (choice != TOURNAMENT) {
            controller = new Controller(new View(root),
                    new Classic(new Board(),
                            player1, player2));
        } else {
            controller = new Controller(new View(root),
                    new Tournament(player1, player2));
        }
        controller.startGame();
    }
}
