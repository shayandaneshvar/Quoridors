package ir.shayandaneshvar.model;

import javafx.scene.paint.Color;

public class Piece extends BoardItem {
    private Color color;
    public Piece(Position position, Color color) {
        super(position);
        this.color = color;
    }
}
