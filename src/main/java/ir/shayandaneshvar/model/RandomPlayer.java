package ir.shayandaneshvar.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {

    public RandomPlayer(String name) {
        super(name);
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
