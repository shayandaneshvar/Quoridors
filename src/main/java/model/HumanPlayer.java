package model;


import controller.Controller;
import controller.Triple;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public Action getNextMove() {
        Triple<Act, Position, Direction> result = Controller.handleInputs();
        if(result.getFirst() == Act.MOVE){
            return new Move(result.getSecond());
        }else {
            return new Block(result.getSecond(),result.getThird());
        }
    }
}
