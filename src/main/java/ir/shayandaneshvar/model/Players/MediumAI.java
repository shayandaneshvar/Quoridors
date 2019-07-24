package ir.shayandaneshvar.model.Players;

import ir.shayandaneshvar.model.Actions.Action;
import ir.shayandaneshvar.model.Actions.Block;
import ir.shayandaneshvar.model.Actions.Move;
import ir.shayandaneshvar.model.Assets.Cell;
import ir.shayandaneshvar.model.Assets.Piece;
import ir.shayandaneshvar.model.Direction;
import ir.shayandaneshvar.model.Players.Player;
import ir.shayandaneshvar.model.Position;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Random;

public class MediumAI extends Player {
    public MediumAI(String name) {
        super(name);
    }

    @Override
    public void run() {
    }

    @Override
    public Action getNextMove(Piece player, Piece still, Graph<Cell, DefaultEdge
            > board, List<Cell> cells) {
        Random random = new Random();
        Cell current = cells.stream().filter(x -> x.getPosition().getX() ==
                player.getPosition().getX() && x.getPosition().getY() == player.
                getPosition().getY()).findFirst().get();
        if (Math.abs(random.nextInt()) % 3 == 0) {
            return getBlockingAction(player, still, board, cells, random);
        } else {
            return getMove(board, cells, current);
        }
    }

    private Action getMove(Graph<Cell, DefaultEdge> board, List<Cell> cells, Cell current) {
        DijkstraShortestPath<Cell, DefaultEdge> dijkstra =
                new DijkstraShortestPath<>(board);
        GraphPath<Cell, DefaultEdge> path = null;
        for (int i = 0; i < 9; i++) {
            final int I = i;
            Cell cell = cells.stream().filter(x -> x.getPosition().getY() ==
                    0 && x.getPosition().getX() == I).findFirst().get();
            if (path == null) {
                path = dijkstra.getPath(current, cell);
            }
            if (dijkstra.getPath(current, cell) != null && dijkstra.getPath
                    (current, cell).getLength() < path.getLength()) {
                path = dijkstra.getPath(current, cell);
            }
        }
        path.getVertexList().forEach(System.out::println);
        return new Move(path.getVertexList().get(1).getPosition());
    }

    private Action getBlockingAction(Piece player, Piece still, Graph<Cell,
            DefaultEdge> board, List<Cell> cells, Random random) {
        Cell leftDown = null;
        Cell left = null;
        Cell right = null;
        final int stillX = still.getPosition().getX();
        final int stillY = still.getPosition().getY();
        Cell currentEnemy = cells.stream().filter(x -> x.getPosition().equals(
                still.getPosition())).findFirst().get();
        Cell down = cells.stream().filter(x -> x.getPosition().getX() == stillX && x.
                getPosition().getY() == stillY + 1).findFirst().get();
        if (still.getPosition().getX() > 0) {
            left = cells.stream().filter(x -> x.getPosition().getX() == stillX -
                    1 && x.getPosition().getY() == stillY).findFirst().get();
            leftDown = cells.stream().filter(x -> x.getPosition().getX() ==
                    stillX && x.getPosition().getY() == stillY + 1).findFirst().get();
        }
        if (still.getPosition().getX() < 8) {
            right = cells.stream().filter(x -> x.getPosition().getX() == stillX
                    + 1 && x.getPosition().getY() == stillY).findFirst().get();
        }
        if (board.containsEdge(down, currentEnemy) && random.nextInt() % 2 == 0) {
            return new Block(currentEnemy.getPosition(), Direction.HORIZONTAL);
        } else if (left != null && board.containsEdge(left, leftDown)) {
            return new Block(new Position(currentEnemy.getPosition().getX() -
                    1, currentEnemy.getPosition().getY()), Direction.HORIZONTAL);
        } else if (random.nextInt() % 2 == 0) {
            return getNextMove(player, still, board, cells);
        }
        if (right != null && board.containsEdge(right, currentEnemy) &&
                random.nextInt() % 2 == 0) {
            return new Block(new Position(currentEnemy.getPosition().getX(),
                    currentEnemy.getPosition().getY()), Direction.VERTICAL);
        } else if (right != null && currentEnemy.getPosition().getY() != 0) {
            return new Block(new Position(currentEnemy.getPosition().getX(),
                    currentEnemy.getPosition().getY() - 1), Direction.VERTICAL);
        }
        if (left != null && board.containsEdge(left, currentEnemy) &&
                random.nextInt() % 2 == 0) {
            return new Block(new Position(currentEnemy.getPosition().getX() - 1,
                    currentEnemy.getPosition().getY()), Direction.VERTICAL);
        } else if (right != null && currentEnemy.getPosition().getY() != 0) {
            return new Block(new Position(currentEnemy.getPosition().getX() - 1,
                    currentEnemy.getPosition().getY() - 1), Direction.VERTICAL);
        }
        return getNextMove(player, still, board, cells);
    }
}
