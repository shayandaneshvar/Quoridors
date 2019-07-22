package ir.shayandaneshvar.model;

import ir.shayandaneshvar.view.Observer;

import java.util.ArrayList;
import java.util.List;


public abstract class Game implements Observable, Runnable {
    protected boolean tournament;
    private Board board;
    private Player player1;
    private Player player2;
    private int turn;
    private boolean isGameOver;
    private List<Observer> observers;
    private Player winner;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.observers = new ArrayList<>();
        isGameOver = false;
        tournament = false;
        winner = null;
        turn = 0;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void enableTournament() {
        tournament = true;
    }

    public void nextTurn() {
        turn++;
    }

    public int getTurn() {
        return turn;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
    @Override
    public abstract void run();

    public abstract void handleGameOver();

    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void updateObservers() {
        observers.stream().forEach(x -> x.update(this));
    }
}
