package ir.shayandaneshvar.model.Players;


import ir.shayandaneshvar.controller.Controller;
import ir.shayandaneshvar.controller.Triple;
import ir.shayandaneshvar.model.Actions.Act;
import ir.shayandaneshvar.model.Actions.Action;
import ir.shayandaneshvar.model.Actions.Block;
import ir.shayandaneshvar.model.Actions.Move;
import ir.shayandaneshvar.model.Assets.Cell;
import ir.shayandaneshvar.model.Assets.Piece;
import ir.shayandaneshvar.model.Direction;
import ir.shayandaneshvar.model.Players.Player;
import ir.shayandaneshvar.model.Position;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void run() {
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
