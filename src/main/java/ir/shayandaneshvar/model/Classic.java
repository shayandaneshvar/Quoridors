package ir.shayandaneshvar.model;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;
import ir.shayandaneshvar.view.View;

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
                getBoard().getAssets().decrementPlayer1Walls();
            }
            Platform.runLater(this::handleFaultyBlocking);
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
                getBoard().getAssets().decrementPlayer2Walls();
            }
            Platform.runLater(this::handleFaultyBlocking);
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
        boolean isPlayer1Surrounded = true;
        boolean isPlayer2Surrounded = true;
        GraphIterator<Cell, DefaultEdge> iteratorPlayer1 = new BreadthFirstIterator<>
                (getBoard().getGameBoard(), getBoard().getCells().stream().
                        filter(x -> x.getPosition().equals(getBoard().getAssets(
                        ).getPiece1().getPosition())).findFirst().get());
        while (iteratorPlayer1.hasNext()) {
            Cell cell = iteratorPlayer1.next();
            if (cell.getPosition().getY() == 8) {
                isPlayer1Surrounded = false;
                break;
            }
        }
        GraphIterator<Cell, DefaultEdge> iteratorPlayer2 = new
                BreadthFirstIterator<>(getBoard().getGameBoard(), getBoard().
                getCells().stream().filter(x -> x.getPosition().equals(getBoard
                ().getAssets().getPiece2().getPosition())).findFirst().get());
        while (iteratorPlayer2.hasNext()) {
            Cell cell = iteratorPlayer2.next();
            if (cell.getPosition().getY() == 0) {
                isPlayer2Surrounded = false;
                break;
            }
        }
        if (isPlayer1Surrounded || isPlayer2Surrounded) {
            if (getTurn() % 2 == 1) {
                View.drawGameOver(getPlayer2().getName(), Color.GREENYELLOW);
            } else {
                View.drawGameOver(getPlayer1().getName(), Color.CRIMSON);
            }
        }
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
