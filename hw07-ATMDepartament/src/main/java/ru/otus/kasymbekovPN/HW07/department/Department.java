package ru.otus.kasymbekovPN.HW07.department;

import ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult.*;
import ru.otus.kasymbekovPN.HW07.utils.Caretaker;
import ru.otus.kasymbekovPN.HW07.utils.Displayable;
import ru.otus.kasymbekovPN.HW07.utils.Observable;
import ru.otus.kasymbekovPN.HW07.utils.Observer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

//< !!! Комментировать
public class Department implements Observable, Displayable {

    //< !!! Цепочка ответствености - запрос количества подписчиков, что-то ещё

    //< !!! Department contains caretaker(s)

    //< !!! set replace with map where key is unique ID of atm
//    private Set<Observer> observers;
    //<
    private Map<Integer, Observer> observers;

    private Caretaker caretaker;

    //< !!! caretaker передавать более элегантно
    public Department(Caretaker caretaker){
        this.observers = new HashMap<>();
        this.caretaker = caretaker;
    }

    @Override
    public void display() {
        //< ??? Правильное логирование
    }

    /**
     * Подписываем наблюдателя
     * @param o Инстанс-наблюдатель
     */
    @Override
    public DepartmentActionResult subscribe(Observer o) {

        //>
        //< replace void with DepartmentActionResult
        //<

        //< ??? Проверять уникальность ???
        observers.put(o.getID(), o);
        caretaker.setMemento(o.getID(), o.getState());

        return new DepartmentScribeResult(true, o);
    }

    /**
     * Отписываем наблюдателя
     * @param o Инстанс-наблюдатель
     */
    @Override
    public DepartmentActionResult unsubscribe(Observer o) {
        observers.remove(o.getID());
        caretaker.remove(o.getID());

        return new DepartmentScribeResult(false, o);
    }

    /**
     * Запрашивает баланс у всех, подписаных на реализацию
     * Observable, наблюдателей
     */
    @Override
    public DepartmentActionResult balanceRequest() {
        int commonBalance = 0;
        for (Map.Entry<Integer, Observer> entry : observers.entrySet()){
            commonBalance += entry.getValue().getBalance();
        }
        //<
//        AtomicInteger commonBalance = new AtomicInteger();
//        observers.forEach((k, v) -> {
//            commonBalance.addAndGet(v.getBalance());
//        });
        //<
        // ??? stream

        return new DepartmentComBalanceReqResult(commonBalance);
    }

    /**
     * Запрашавает баланс у налюдателей, представленных
     * в списке.
     * @param obs Идентификаторы наблюдателей
     */
    @Override
    public DepartmentActionResult balanceRequest(Set<Integer> obs) {

        //< !!! Must return DepartmentAction (interface)
        //<
        int balance = 0;
        for (Integer ob : obs) {
            if (observers.containsKey(ob)){
                balance += observers.get(ob).getBalance();
            }
        }

        return new DepartmentCustBalanceReqResult(balance, obs);
    }

    /**
     * Сбрасывает наблюдателей в исходное состоние.
     */
    @Override
    public DepartmentActionResult resetObserverState() {
        for(Map.Entry<Integer, Observer> entry : observers.entrySet()){
            entry.getValue().setState(
                    caretaker.getMemento(
                            entry.getKey()
                    )
            );
        }
        //<
        //<
        // stream

        return new DepartmentComResetStateResult();
    }

    /**
     * Сбрасывает наблюдателей, представленных в списке,
     * в исходное состояние.
     * @param obs Идентификаторы наблюдателей
     */
    @Override
    public DepartmentActionResult resetObserverState(Set<Integer> obs) {
        for (Integer ob : obs) {
            if (observers.containsKey(ob)){
                observers.get(ob).setState(caretaker.getMemento(ob));
            }
        }

        return new DepartmentCustResetStateResult(obs);
    }
}
