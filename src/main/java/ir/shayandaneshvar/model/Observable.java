package ir.shayandaneshvar.model;

import ir.shayandaneshvar.view.Observer;

public interface Observable {

    void addObserver(Observer observer);

    void updateObservers();
}
