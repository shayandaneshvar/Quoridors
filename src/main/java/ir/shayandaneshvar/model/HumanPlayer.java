package ir.shayandaneshvar.model;


import ir.shayandaneshvar.controller.Controller;
import ir.shayandaneshvar.controller.Triple;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public Action getNextMove(Piece player, Piece still, Graph<Cell, DefaultEdge> board, List<Cell> cells) {
        Triple<Act, Position, Direction> result = Controller.handleInputs();
        if (result.getFirst() == Act.MOVE) {
            return new Move(result.getSecond());
        } else {
            return new Block(result.getSecond(), result.getThird());
        }
    }

}
