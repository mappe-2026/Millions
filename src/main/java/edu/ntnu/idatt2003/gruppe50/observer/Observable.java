package edu.ntnu.idatt2003.gruppe50.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for observable objects in the Observer pattern.
 * Maintains a list of observers and notifies them of state changes.
 */
public abstract class Observable {
    private final List<Observer> observers = new ArrayList<>();


    /**
     * Adds an observer to the list.
     * @param o the observer to add
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Removes an observer from the list
     * @param o the observer to remove
     */
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Updates all the observers.
     */
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
