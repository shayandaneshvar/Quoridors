package ir.shayandaneshvar.model;

public class Wall extends BoardItem {

    private Direction direction;

    public Wall(Position position, Direction direction) {
        super(position);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
