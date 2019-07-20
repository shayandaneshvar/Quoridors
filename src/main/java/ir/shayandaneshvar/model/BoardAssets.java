package ir.shayandaneshvar.model;

import java.util.Collection;
import java.util.HashSet;

public class BoardAssets {
    private Piece piece1;
    private Piece piece2;
    private HashSet<Wall> player1Walls;
    private HashSet<Wall> player2Walls;
    private int player1InactiveWalls;
    private int player2InactiveWalls;

    public int getPlayer1InactiveWalls() {
        return player1InactiveWalls;
    }

    public int getPlayer2InactiveWalls() {
        return player2InactiveWalls;
    }

    public void decrementPlayer1Walls() {
        player1InactiveWalls--;
    }

    public void decrementPlayer2Walls() {
        player2InactiveWalls--;
    }

    public BoardAssets(Piece piece1, Piece piece2) {
        this.piece1 = piece1;
        this.piece2 = piece2;
        this.player1Walls = new HashSet<>();
        this.player2Walls = new HashSet<>();
        player1InactiveWalls = 10;
        player2InactiveWalls = 10;
    }

    public Piece getPiece1() {
        return piece1;
    }

    public Piece getPiece2() {
        return piece2;
    }

    public void addToPlayer1Walls(Wall wall) {
        player1Walls.add(wall);
    }

    public void addToPlayer2Walls(Wall wall) {
        player2Walls.add(wall);
    }

    public HashSet<Wall> getPlayer1Walls() {
        return (HashSet<Wall>) player1Walls.clone();
    }

    public HashSet<Wall> getPlayer2Walls() {
        return (HashSet<Wall>) player2Walls.clone();
    }

    public HashSet<Wall> getAllWalls() {
        HashSet<Wall> walls = new HashSet<>();
        walls.addAll((Collection<? extends Wall>) player1Walls.clone());
        walls.addAll((Collection<? extends Wall>) player2Walls.clone());
        return walls;
    }

}
