package ir.shayandaneshvar.model;

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
