package ir.shayandaneshvar.model.Actions;

import ir.shayandaneshvar.model.Actions.Action;
import ir.shayandaneshvar.model.Direction;
import ir.shayandaneshvar.model.Position;

public class Block extends Action {
    private Direction direction;

    public Block(Position position, Direction direction) {
        super(position);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
