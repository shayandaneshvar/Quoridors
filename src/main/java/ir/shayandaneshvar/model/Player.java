package ir.shayandaneshvar.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public abstract class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Action getNextMove(Piece player, Piece still, Graph<Cell,
            DefaultEdge> board, List<Cell> cells);
}
