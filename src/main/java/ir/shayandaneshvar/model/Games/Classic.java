package ir.shayandaneshvar.model.Games;

import ir.shayandaneshvar.model.Actions.Action;
import ir.shayandaneshvar.model.Actions.Block;
import ir.shayandaneshvar.model.Actions.Move;
import ir.shayandaneshvar.model.Assets.Board;
import ir.shayandaneshvar.model.Assets.Cell;
import ir.shayandaneshvar.model.Assets.Wall;
import ir.shayandaneshvar.model.Validators.ClassicValidator;
import ir.shayandaneshvar.model.Direction;
import ir.shayandaneshvar.model.Players.Player;
import ir.shayandaneshvar.view.View;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import java.util.concurrent.atomic.AtomicReference;

public class Classic extends Game {

    public Classic(Board board, Player player1, Player player2) {
        super(board, player1, player2);
        validator = new ClassicValidator();
    }

    @Override
    public void run() {
        Platform.runLater(this::handleGameOver);
        Action act;
        if (super.getTurn() % 2 == 0 && validator.isValid(true, act =
                        getPlayer1().getNextMove(getBoard().getAssets().getPiece1(),
                                getBoard().getAssets().getPiece2(),
                                getBoard().getGameBoard(), getBoard().getCells()),
                this)) {
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
        if (super.getTurn() % 2 == 1 && validator.isValid(false, act =
                        getPlayer2().getNextMove(getBoard().getAssets().getPiece2(),
                                getBoard().getAssets().getPiece1(),
                                getBoard().getGameBoard(), getBoard().getCells()),
                this)) {
            if (act instanceof Move) {
                getBoard().getAssets().getPiece2().setPosition(act.getPosition());
            } else {
                handleAction((Block) act, false);
                getBoard().getAssets().decrementPlayer2Walls();
            }
            this.nextTurn();
            Platform.runLater(this::handleFaultyBlocking);
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
            setGameOver(true);
            if (getTurn() % 2 == 1) {
                super.setWinner(getPlayer2());
                View.drawGameOver(getPlayer2().getName(), Color.GREENYELLOW, tournament);
            } else {
                super.setWinner(getPlayer1());
                View.drawGameOver(getPlayer1().getName(), Color.CRIMSON, tournament);
            }
        }
    }

    @Override
    public void handleGameOver() {
        if (getBoard().getAssets().getPiece1().getPosition().getY() == 8) {
            setGameOver(true);
            System.out.println("Player 1 Has Won!");
            View.drawGameOver(getPlayer1().getName(), Color.CRIMSON, tournament);
            super.setWinner(getPlayer1());
        } else if (getBoard().getAssets().getPiece2().getPosition().getY() == 0) {
            setGameOver(true);
            System.out.println("Player 2 Has Won!");
            super.setWinner(getPlayer2());
            View.drawGameOver(getPlayer2().getName(), Color.GREENYELLOW, tournament);
        }
    }
}
