package model;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import view.View;

import java.util.concurrent.atomic.AtomicReference;

public class Classic extends Game {

    public Classic(Board board, Player player1, Player player2) {
        super(board, player1, player2);
    }

    @Override
    public void run() {
        updateObservers();
        Platform.runLater(this::handleGameOver);
        Action act;
        if (super.getTurn() % 2 == 0 && Validator.isValid(true, act =
                getPlayer1().getNextMove(), this)) {
            if (act instanceof Move) {
                getBoard().getAssets().getPiece1().setPosition(act.getPosition());
            } else {
                handleAction((Block) act, true);
            }
            handleFaultyBlocking();
            this.nextTurn();
            return;
        }
        if (super.getTurn() % 2 == 1 && Validator.isValid(false, act =
                        getPlayer2().getNextMove(),
                this)) {
            if (act instanceof Move) {
                getBoard().getAssets().getPiece2().setPosition(act.getPosition());
            } else {
                handleAction((Block) act, false);
            }
            handleFaultyBlocking();
            this.nextTurn();
            return;
        }

    }

    private void handleAction(Block act, boolean firstPlayer) {
        AtomicReference<Block> action =
                new AtomicReference<>(act);
        Cell first = getBoard().getCells().stream().filter(x -> x.
                getPosition().equals(action.get().getPosition())).findFirst().get();
        Cell second = getBoard().getCells().stream().filter(x -> x.
                getPosition().getX() == action.get().getPosition().getX() + 1 && x.
                getPosition().getY() == action.get().getPosition().getY()).findFirst()
                .get();
        Cell fourth = getBoard().getCells().stream().filter(x -> x.
                getPosition().getX() == action.get().getPosition().getX() + 1 && x.
                getPosition().getY() == action.get().getPosition().getY() + 1).
                findFirst().get();
        Cell third = getBoard().getCells().stream().filter(x -> x.
                getPosition().getX() == action.get().getPosition().getX() && x.
                getPosition().getY() == action.get().getPosition().getY() + 1).
                findFirst().get();
        if (action.get().getDirection() == Direction.HORIZONTAL) {
            getBoard().getGameBoard().removeEdge(first, third);
            getBoard().getGameBoard().removeEdge(second, fourth);
        } else if (action.get().getDirection() == Direction.VERTICAL) {
            getBoard().getGameBoard().removeEdge(first, second);
            getBoard().getGameBoard().removeEdge(third, fourth);
        }
        if (firstPlayer) {
            getBoard().getAssets().addToPlayer1Walls(new Wall(action.
                    get().getPosition(), action.get().getDirection()));
        } else {
            getBoard().getAssets().addToPlayer2Walls(new Wall(action.
                    get().getPosition(), action.get().getDirection()));
        }
    }

    private void handleFaultyBlocking() {
        // TODO: 7/17/2019
    }

    @Override
    public void handleGameOver() {
        if (getBoard().getAssets().getPiece1().getPosition().getY() == 8) {
            System.out.println("Player 1 Has Won!");
            View.drawGameOver(getPlayer1().getName(), Color.CRIMSON);
        } else if (getBoard().getAssets().getPiece2().getPosition().getY() == 0) {
            System.out.println("Player 2 Has Won!");
            View.drawGameOver(getPlayer2().getName(), Color.GREENYELLOW);
        }
    }
}
