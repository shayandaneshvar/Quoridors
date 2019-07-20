package ir.shayandaneshvar.model;

public enum Sake {
    UP, RIGHT, DOWN, LEFT;

    public static Position getChangedPosition(Sake sake, Position position) {
        int x = position.getX();
        int y = position.getY();
        switch (sake) {
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
            case DOWN:
                y++;
                break;
            case UP:
                y--;
                break;
        }
        System.out.println("---------------");
        System.out.println(x + "," + y);
        System.out.println("---------------");
        return new Position(x, y);
    }
}
