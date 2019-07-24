package ir.shayandaneshvar.model.Assets;

import ir.shayandaneshvar.model.Position;

public abstract class BoardItem {
    private Position position;

    public BoardItem(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
