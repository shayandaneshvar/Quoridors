package ir.shayandaneshvar.model.Actions;

import ir.shayandaneshvar.model.Position;

public abstract class Action {
    private Position position;

    public Action(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
