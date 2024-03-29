package ir.shayandaneshvar.model.Players;

import ir.shayandaneshvar.model.Actions.Action;
import ir.shayandaneshvar.model.Assets.Cell;
import ir.shayandaneshvar.model.Assets.Piece;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Objects;

public abstract class Player implements Runnable {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public abstract void run();

    public abstract Action getNextMove(Piece player, Piece still, Graph<Cell,
            DefaultEdge> board, List<Cell> cells);
}
