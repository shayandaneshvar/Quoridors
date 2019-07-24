package ir.shayandaneshvar.model.Validators;

import ir.shayandaneshvar.model.Actions.Action;
import ir.shayandaneshvar.model.Games.Game;

@FunctionalInterface
public interface Validator {
    boolean isValid(boolean turn1, Action action, Game game);
}
