package model;

public class Classic extends Game {

    public Classic(Board board, Player player1, Player player2) {
        super(board, player1, player2);
    }

    // FIXME: 7/17/2019 
    @Override
    public void run() {
        Action action;
        if (super.getTurn() % 2 == 0 && Validator.isValid(true, action =
                getPlayer1().getNextMove(), this)) {

            this.nextTurn();
        } else if (Validator.isValid(false, action = getPlayer2().getNextMove(),
                this)) {

            this.nextTurn();
        }

    }

    // TODO: 7/17/2019  
    @Override
    public boolean isGameOver() {
        return false;
    }
}
