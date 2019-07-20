package ir.shayandaneshvar.model;

public class Cell extends BoardItem {

    public Cell(Position position) {
        super(position);
    }

    @Override
    public String toString() {
        return "Cell{" + super.getPosition() + "}";
    }
}
