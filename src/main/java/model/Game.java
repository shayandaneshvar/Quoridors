package model;

import view.Observer;

import java.util.ArrayList;
import java.util.List;


public abstract class Game implements Observable {
    private Board board;
    private Player player1;
    private Player player2;
    private int turn;

    public void nextTurn() {
        turn++;
    }

    public int getTurn() {
        return turn;
    }

    private List<Observer> observers;

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.observers = new ArrayList<>();
        turn = 0;
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

    public abstract void run();

    public abstract void handleGameOver();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void updateObservers() {
        observers.stream().forEach(x -> x.update(this));
    }
}
