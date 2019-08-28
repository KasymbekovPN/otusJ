package ru.otus.kasymbekovPN.HW07.utils;

import java.util.Set;

//< rename ???
public interface Observable {

    //< в add/removeObserver initConnection

    /**
     * Подписываем наблюдателя
     * @param o Инстанс-наблюдатель
     */
    void addObserver(Observer o);

    /**
     * Отписываем наблюдателя
     * @param o Инстанс-наблюдатель
     */
    void removeObserver(Observer o);
    //<

    /**
     * Запрашивает баланс у всех, подписаных на реализацию
     * Observable, наблюдателей
     */
    void balanceRequest();

    /**
     * Запрашавает баланс у налюдателей, представленных
     * в списке.
     * @param obs Идентификаторы наблюдателей
     */
    void balanceRequest(Set<Integer> obs);

    /**
     * Сбрасывает наблюдателей в исходное состоние.
     */
    void resetObserverState();

    /**
     * Сбрасывает наблюдателей, представленных в списке,
     * в исходное состояние.
     * @param obs Идентификаторы наблюдателей
     */
    void resetObserverState(Set<Integer> obs);
}
