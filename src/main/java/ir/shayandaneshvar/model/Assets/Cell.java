package ir.shayandaneshvar.model.Assets;

import ir.shayandaneshvar.model.Assets.BoardItem;
import ir.shayandaneshvar.model.Position;

public class Cell extends BoardItem {

    public Cell(Position position) {
        super(position);
    }

    @Override
    public String toString() {
        return "Cell{" + super.getPosition() + "}";
    }
}
