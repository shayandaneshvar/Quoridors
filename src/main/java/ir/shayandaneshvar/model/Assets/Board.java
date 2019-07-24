package ir.shayandaneshvar.model.Assets;

import ir.shayandaneshvar.model.Assets.BoardAssets;
import ir.shayandaneshvar.model.Assets.Cell;
import ir.shayandaneshvar.model.Assets.Piece;
import ir.shayandaneshvar.model.Position;
import javafx.scene.paint.Color;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Graph<Cell, DefaultEdge> gameBoard;
    private List<Cell> cells;
    private BoardAssets assets;

    public Board() {
        assets = new BoardAssets(new Piece(new Position(4, 0),
                Color.GREENYELLOW), new Piece(new Position(4, 8), Color.CRIMSON));
        this.gameBoard = new SimpleGraph<>(DefaultEdge.class);
        cells = new ArrayList<>();
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                Cell cell = new Cell(new Position(i, j));
                cells.add(cell);
                gameBoard.addVertex(cell);
            }
        }
        for (int k = 0; k < 9; k++) {
            int l = k;
            for (int i = 0; i < 9; i++) {
                int j = i;
                Cell main = cells.stream().filter(x -> x.getPosition().getX() ==
                        j && x.getPosition().getY() == l).findAny().get();
                if (k != 8) {
                    gameBoard.addEdge(main, cells.stream().filter(x -> x.getPosition().
                            getX() == j && x.getPosition().getY() == l + 1).findAny().get());
                }
                if (i != 8) {
                    gameBoard.addEdge(main, cells.stream().filter(x -> x.
                            getPosition().getX() == j + 1 && x.getPosition().getY() == l).findAny().get());
                }
            }
        }
    }

    public Graph<Cell, DefaultEdge> getGameBoard() {
        return gameBoard;
    }

    public ArrayList<Cell> getCells() {
        return (ArrayList<Cell>) ((ArrayList<Cell>) cells).clone();
    }

    public BoardAssets getAssets() {
        return assets;
    }
}