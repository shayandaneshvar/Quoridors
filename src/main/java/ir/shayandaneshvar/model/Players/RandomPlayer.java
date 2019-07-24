package ir.shayandaneshvar.model.Players;

import ir.shayandaneshvar.model.Actions.Action;
import ir.shayandaneshvar.model.Actions.Block;
import ir.shayandaneshvar.model.Actions.Move;
import ir.shayandaneshvar.model.Assets.Cell;
import ir.shayandaneshvar.model.Assets.Piece;
import ir.shayandaneshvar.model.Direction;
import ir.shayandaneshvar.model.Players.Player;
import ir.shayandaneshvar.model.Position;
import ir.shayandaneshvar.model.Sake;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {

    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public void run() {
    }

    @Override
    public Action getNextMove(Piece player, Piece still, Graph<Cell, DefaultEdge> board, List<Cell> cells) {
        Random random = new Random();
        if (Math.abs(random.nextInt()) % 10 == 0) {
            return new Block(new Position(Math.abs(random.nextInt() % 8),
                    Math.abs(random.nextInt() % 8)),
                    Direction.values()[Math.abs(random.nextInt() % 2)]);
        } else {
            int temp = Math.abs(random.nextInt() % 4);
            return new Move(Sake.getChangedPosition(Sake.values()[Math.abs(temp
                            == 2 ? Math.abs(random.nextInt() % 4) : temp)],
                    player.getPosition()));
        }
    }
}
