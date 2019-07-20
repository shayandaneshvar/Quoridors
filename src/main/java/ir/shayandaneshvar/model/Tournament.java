package ir.shayandaneshvar.model;

import java.util.TreeSet;

public class Tournament extends Game {
    private TreeSet games;
    private int player1Score;
    private int player2Score;

    public Tournament(Board board, Player player1, Player player2) {
        super(board, player1, player2);
        games = new TreeSet();
        games.add(new Classic(board, player1, player2));
        games.add(new Classic(board, player1, player2));
        games.add(new Classic(board, player1, player2));
        player1Score = 0;
        player2Score = 0;
    }

    @Override
    public void run() {

    }

    @Override
    public void handleGameOver() {

    }
}
