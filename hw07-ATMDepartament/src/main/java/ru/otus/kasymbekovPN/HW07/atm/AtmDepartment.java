package ru.otus.kasymbekovPN.HW07.atm;

import ru.otus.kasymbekovPN.HW07.utils.Displayable;
import ru.otus.kasymbekovPN.HW07.utils.Observable;
import ru.otus.kasymbekovPN.HW07.utils.Observer;

import java.util.HashSet;
import java.util.Set;

public class AtmDepartment implements Observable, Displayable {

    private Set<Observer> observers;

    public AtmDepartment(){
        observers = new HashSet<>();
    }

    @Override
    public void display() {
        //< ???
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void balanceRequest() {
        for (Observer observer : observers) {
            observer.getBalance();
        }
    }

    @Override
    public void balanceRequest(Set<Observer> obs) {
        for (Observer ob : obs) {
            if (observers.contains(ob)){
                ob.getBalance();
            }
        }
    }

    @Override
    public void resetObserverState() {
        for (Observer observer : observers) {
            //<
            //< Отсылать Memento
            //<
            observer.reset();
        }
    }

    @Override
    public void resetObserverState(Set<Observer> obs) {
        for (Observer ob : obs) {
            if (observers.contains(ob)){
                ob.reset();
            }
        }
    }
}
