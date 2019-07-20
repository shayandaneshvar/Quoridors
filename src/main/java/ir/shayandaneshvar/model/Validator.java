package ir.shayandaneshvar.model;

import java.util.HashSet;
import java.util.Optional;

public final class Validator {


    public static boolean isValid(boolean turn1, Action action, Classic game) {
        if (action instanceof Move) {
            System.out.println("Validating Move");
            return isMoveValid(turn1 ? game.getBoard().getAssets().getPiece1() :
                            game.getBoard().getAssets().getPiece2(), !turn1 ?
                            game.getBoard().getAssets().getPiece1() :
                            game.getBoard().getAssets().getPiece2(),
                    (Move) action, game);
        } else if (action instanceof Block) {
            System.out.println("Validating Block");
            if (turn1) {
                if (game.getBoard().getAssets().getPlayer1InactiveWalls() == 0) {
                    return false;
                }
            } else {
                if (game.getBoard().getAssets().getPlayer2InactiveWalls() == 0) {
                    return false;
                }
            }
            return isBlockValid((Block) action, game);
        }
        return false;
    }

    private static boolean isBlockValid(Block action,
                                        Classic game) {
        HashSet<Wall> walls = game.getBoard().getAssets().getAllWalls();
        if (walls.stream().filter(x -> x.getPosition().equals(action.getPosition())).findAny().isPresent()) {
            return false;
        }
        if (action.getPosition().getX() > 7 || action.getPosition().getX() < 0
                || action.getPosition().getY() > 7 || action.getPosition().getY(
        ) < 0) {
            return false;
        }
        //1-2
        //3-4
        Cell first = game.getBoard().getCells().stream().filter(x -> x.
                getPosition().equals(action.getPosition())).findFirst().get();
        Cell second = game.getBoard().getCells().stream().filter(x -> x.
                getPosition().getX() == action.getPosition().getX() + 1 && x.
                getPosition().getY() == action.getPosition().getY()).findFirst()
                .get();
        Cell fourth = game.getBoard().getCells().stream().filter(x -> x.
                getPosition().getX() == action.getPosition().getX() + 1 && x.
                getPosition().getY() == action.getPosition().getY() + 1).
                findFirst().get();
        Cell third = game.getBoard().getCells().stream().filter(x -> x.
                getPosition().getX() == action.getPosition().getX() && x.
                getPosition().getY() == action.getPosition().getY() + 1).
                findFirst().get();
        if (action.getDirection() == Direction.HORIZONTAL) {
            if (game.getBoard().getGameBoard().containsEdge(first, third) &&
                    game.getBoard().getGameBoard().containsEdge(fourth, second)) {
                return true;
            }
        } else if (action.getDirection() == Direction.VERTICAL) {
            if (game.getBoard().getGameBoard().containsEdge(first, second) &&
                    game.getBoard().getGameBoard().containsEdge(fourth, third)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMoveValid(Piece player, Piece still, Move action,
                                       Classic game) {
        System.out.println(player);
        System.out.println(still);
        if ((action.getPosition().getX() == player.getPosition().getX() &&
                action.getPosition().getY() == player.getPosition().getY()) ||
                (action.getPosition().getX() == still.getPosition().getX() &&
                        action.getPosition().getY() == still.getPosition().getY())) {
            System.out.println("1");
            return false;
        }
        if (action.getPosition().getX() < 0 || action.getPosition().getX() > 8
                || action.getPosition().getY() < 0 || action.getPosition().getY
                () > 8) {
            System.out.println("2");
            return false;
        }
        if (!isInRange(game.getBoard().getAssets().getPiece1().getPosition().
                        getX(),
                game.getBoard().getAssets().getPiece2().getPosition().getX(),
                game.getBoard().getAssets().getPiece1().getPosition().getY(),
                game.getBoard().getAssets().getPiece2().getPosition().getY(),
                1) && !isInRange(player.getPosition().getX(),
                action.getPosition().getX(), player.getPosition().getY(),
                action.getPosition().getY(), 1)) {
            System.out.println("3");
            return false;
        }
        if (isInRange(game.getBoard().getAssets().getPiece1().getPosition().
                        getX(),
                game.getBoard().getAssets().getPiece2().getPosition().getX(),
                game.getBoard().getAssets().getPiece1().getPosition().getY(),
                game.getBoard().getAssets().getPiece2().getPosition().getY(),
                1) && !isInRange(player.getPosition().getX(),
                action.getPosition().getX(), player.getPosition().getY(),
                action.getPosition().getY(), 2)) {
            System.out.println("4");
            return false;
        }

        Cell next = game.getBoard().getCells().stream().filter(x ->
                x.getPosition().getX() == action.getPosition().getX() &&
                        x.getPosition().getY() == action.getPosition().getY(
                        )).findFirst().get();
        Cell current = game.getBoard().getCells().stream().filter(x ->
                x.getPosition().getX() == player.getPosition().getX() &&
                        x.getPosition().getY() == player.getPosition().getY(
                        )).findFirst().get();
        Cell enemy = game.getBoard().getCells().stream().filter(x ->
                x.getPosition().getX() == still.getPosition().getX() &&
                        x.getPosition().getY() == still.getPosition().getY(
                        )).findFirst().get();
        Optional<Cell> afterEnemy = game.getBoard().getCells().stream().filter(x ->
                x.getPosition().getX() == 2 * still.getPosition().getX() - player.getPosition().getX() &&
                        x.getPosition().getY() == 2 * still.getPosition().getY(
                        ) - player.getPosition().getY()).findFirst();
        System.out.println("5");
        if (isInRange(player.getPosition().getX(),
                action.getPosition().getX(), player.getPosition().getY(),
                action.getPosition().getY(), 1)) {
            System.out.println("6");
            if (game.getBoard().getGameBoard().containsEdge(next, current)) {
                System.out.println("7");
                return true;
            } else if (!afterEnemy.isPresent() || !game.getBoard().getGameBoard().containsEdge(enemy,
                    afterEnemy.get())) {
                System.out.println("8");
                if (game.getBoard().getGameBoard().containsEdge(next, enemy) &&
                        game.getBoard().getGameBoard().containsEdge(enemy, current)) {
                    System.out.println("9");
                    return true;
                }
            } else {
                return false;
            }
        } else if (isInRange(player.getPosition().getX(),
                action.getPosition().getX(), player.getPosition().getY(),
                action.getPosition().getY(), 2)) {
            System.out.println("10");
            Position sumOfPos = getMiddle(current, next);
            if (enemy.getPosition().getX() == sumOfPos.getX() / 2d && enemy.
                    getPosition().getY() == sumOfPos.getY() / 2d) {
                System.out.println("11");
                if (game.getBoard().getGameBoard().containsEdge(current, enemy)
                        && game
                        .getBoard().getGameBoard().containsEdge(enemy, next)) {
                    System.out.println("12");
                    return true;
                } else {
                    System.out.println("13");
                    return false;
                }
            }
        }
        System.out.println("14");
        return false;
    }

    private static Position getMiddle(Cell current, Cell next) {
        return new Position((current.getPosition().getX() + next.getPosition(
        ).getX()),
                (current.getPosition().getY() + next.getPosition().getY()));
    }

    public static boolean isInRange(int x1, int x2, int y1, int y2, int range) {
        int difX = Math.abs(x1 - x2);
        int difY = Math.abs(y1 - y2);
        if ((difX + difY + Math.abs(difX - difY)) / 2 <= range) {
            return true;
        }
        return false;
    }

    private Validator() {
    }
}
