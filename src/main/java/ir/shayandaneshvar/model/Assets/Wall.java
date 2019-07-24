package ir.shayandaneshvar.model.Assets;

import ir.shayandaneshvar.model.Assets.BoardItem;
import ir.shayandaneshvar.model.Direction;
import ir.shayandaneshvar.model.Position;

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
