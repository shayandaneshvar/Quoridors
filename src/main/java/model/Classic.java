package model;

import java.util.concurrent.atomic.AtomicReference;

public class Classic extends Game {

    public Classic(Board board, Player player1, Player player2) {
        super(board, player1, player2);
    }

    // FIXME: 7/17/2019 
    @Override
    public void run() {
        updateObservers();
        Action act;
        System.out.println(getTurn());
        if (super.getTurn() % 2 == 0 && Validator.isValid(true, act =
                getPlayer1().getNextMove(), this)) {
            if (act instanceof Move) {
                getBoard().getAssets().getPiece1().setPosition(act.getPosition());
            } else {
                AtomicReference<Block> action =
                        new AtomicReference<>((Block) act);
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
                getBoard().getAssets().addToPlayer1Walls(new Wall(action.
                        get().getPosition(), action.get().getDirection()));
            }
            this.nextTurn();
            return;
        }
        if (super.getTurn() % 2 == 1 && Validator.isValid(false, act =
                        getPlayer2().getNextMove(),
                this)) {
            if (act instanceof Move) {
                getBoard().getAssets().getPiece2().setPosition(act.getPosition());
            } else {
                AtomicReference<Block> action =
                        new AtomicReference<>((Block) act);
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
                getBoard().getAssets().addToPlayer2Walls(new Wall(action.
                        get().getPosition(), action.get().getDirection()));
            }
            this.nextTurn();
            return;
        }

    }

    // TODO: 7/17/2019  
    @Override
    public boolean isGameOver() {
        return false;
    }
}
