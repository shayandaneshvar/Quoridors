package model;

import javafx.util.Pair;

public class Position extends Pair<Integer, Integer> {
    /**
     * Creates a new pair
     *
     * @param x The x for this pair
     * @param y The value to use for this pair
     */
    public Position(Integer x, Integer y) {
        super(x, y);
    }

    public Integer getX() {
        return super.getKey();
    }

    public Integer getY() {
        return super.getValue();
    }

}
