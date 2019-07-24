package ir.shayandaneshvar.model.Assets;

import ir.shayandaneshvar.model.Assets.BoardItem;
import ir.shayandaneshvar.model.Position;
import javafx.scene.paint.Color;

public class Piece extends BoardItem {
    private Color color;
    public Piece(Position position, Color color) {
        super(position);
        this.color = color;
    }
}
