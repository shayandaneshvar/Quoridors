package ir.shayandaneshvar.model;

@FunctionalInterface
public interface Validator {
    boolean isValid(boolean turn1, Action action, Game game);
}
