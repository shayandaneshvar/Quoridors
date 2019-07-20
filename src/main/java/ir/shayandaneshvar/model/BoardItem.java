package ir.shayandaneshvar.model;

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
