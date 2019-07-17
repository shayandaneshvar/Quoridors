package model;

public abstract class Action {
    private Position position;

    public Action(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
