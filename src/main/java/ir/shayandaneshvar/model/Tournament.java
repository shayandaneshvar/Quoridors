package ir.shayandaneshvar.model;

import ir.shayandaneshvar.view.Observer;
import ir.shayandaneshvar.view.View;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class Tournament extends Game {
    private Game[] games;
    private int player1Score;
    private int player2Score;
    private AtomicInteger play;

    public Tournament(Player player1, Player player2) {
        super(null, player1, player2);
        games = new Game[3];
        for (int i = 0; i < 3; i++) {
            Game game = new Classic(new Board(), player1, player2);
            game.enableTournament();
            games[i] = (game);
        }
        player1Score = 0;
        player2Score = 0;
        play = new AtomicInteger(0);
    }

    @Override
    public void run() {
        if (play.get() < 3) {
            games[play.get()].run();
        } else {
            return;
        }
        if (games[play.get()].isGameOver()) {
            if (games[play.get()].getWinner().equals(getPlayer1())) {
                player1Score++;
            } else {
                player2Score++;
            }
            play.getAndIncrement();
            if (play.get() < 3) {
                updateObservable();
            }
        }
        if (play.get() > 2) {
            Platform.runLater(this::handleGameOver);
        }
    }


    @Override
    public void handleGameOver() {
        if (player2Score < player1Score) {
            View.drawGameOver(getPlayer1().getName(), Color.CRIMSON, false);
        } else {
            View.drawGameOver(getPlayer2().getName(), Color.GREENYELLOW, false);
        }
    }

    @Override
    public void updateObservers() {
        if (play.get() > 2) {
            return;
        }
        games[play.get()].getObservers().stream().forEach(x -> x.update(games[play.get()]));
    }

    @Override
    public void addObserver(Observer observer) {
        games[play.get()].addObserver(observer);
    }

    public void updateObservable() {
        games[play.get()].addObserver(new View(((View) games[play.get() - 1].getObservers().get(0)).getRoot()));
        games[play.get() - 1].getObservers().clear();
        updateObservers();
    }
}
