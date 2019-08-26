package ru.otus.kasymbekovPN.HW07.utils;

import java.util.Set;

//< rename ???
public interface Observable {
    //< в них initConnection
    void addObserver(Observer o);
    void removeObserver(Observer o);
    //<
    void balanceRequest();
    void balanceRequest(Set<Observer> obs);
    void resetObserverState();
    void resetObserverState(Set<Observer> obs);
}
