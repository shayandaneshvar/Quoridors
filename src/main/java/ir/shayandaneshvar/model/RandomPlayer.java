package ir.shayandaneshvar.model;

import java.util.Random;

public class RandomPlayer extends Player {

    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public Action getNextMove() {
        Random random = new Random();
        if (Math.abs(random.nextInt()) % 10 == 0) {
            return new Block(new Position(Math.abs(random.nextInt() % 8),
                    Math.abs(random.nextInt() % 8)),
                    Direction.values()[Math.abs(random.nextInt() % 2)]);
        } else {
            return new Move(new Position(Math.abs(random.nextInt() % 9),
                    Math.abs(random.nextInt() % 9)));
        }
    }
}
