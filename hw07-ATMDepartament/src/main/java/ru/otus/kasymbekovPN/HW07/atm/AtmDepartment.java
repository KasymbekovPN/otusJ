package ru.otus.kasymbekovPN.HW07.atm;

import ru.otus.kasymbekovPN.HW07.utils.Caretaker;
import ru.otus.kasymbekovPN.HW07.utils.Displayable;
import ru.otus.kasymbekovPN.HW07.utils.Observable;
import ru.otus.kasymbekovPN.HW07.utils.Observer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//< !!! Комментировать
public class AtmDepartment implements Observable, Displayable {

    //< !!! Department contains caretaker(s)

    //< !!! set replace with map where key is unique ID of atm
//    private Set<Observer> observers;
    //<
    private Map<Integer, Observer> observers;

    private Caretaker caretaker;

    //< !!! caretaker передавать более элегантно
    public AtmDepartment(Caretaker caretaker){
        this.observers = new HashMap<>();
        this.caretaker = caretaker;
    }

    @Override
    public void display() {
        //< ???
    }

    @Override
    public void addObserver(Observer o) {
//        observers.add(o);
        //<
        //< ??? Проверять уникальность ???
        observers.put(o.getID(), o);
        caretaker.setMemento(o.getID(), o.getState());
    }

    @Override
    public void removeObserver(Observer o) {
//        observers.remove(o);
        //<
        observers.remove(o.getID());
        caretaker.remove(o.getID());
    }

    @Override
    public void balanceRequest() {
//        for (Observer observer : observers) {
//            observer.getBalance();
//        }
        //<
        //< replace for with foreach
        int commonBalance = 0;
        for (Map.Entry<Integer, Observer> entry : observers.entrySet()){
            commonBalance += entry.getValue().getBalance();
        }
    }

    @Override
    public void balanceRequest(Set<Integer> obs) {

        //< !!! Must return DepartmentAction (interface)
        //<

//        for (Observer ob : obs) {
//            if (observers.contains(ob)){
//                ob.getBalance();
//            }
//        }

        //<

        int balance = 0;
        for (Integer ob : obs) {
            if (observers.containsKey(ob)){
                balance += observers.get(ob).getBalance();
            }
        }
    }

    @Override
    public void resetObserverState() {
//        for (Observer observer : observers) {
//            //<
//            //< Отсылать Memento
//            //<
////            observer.reset();
//        }
        //<
        //< ??? replace for with foreach
        for(Map.Entry<Integer, Observer> entry : observers.entrySet()){
            entry.getValue().setState(
                    caretaker.getMemento(
                            entry.getKey()
                    )
            );
        }
    }

    @Override
    public void resetObserverState(Set<Integer> obs) {
        for (Integer ob : obs) {
            if (observers.containsKey(ob)){
                observers.get(ob).setState(caretaker.getMemento(ob));
            }
        }
        //<
//        for (Observer ob : obs) {
//            if (observers.contains(ob)){
////                ob.reset();
//            }
//        }
    }
}
