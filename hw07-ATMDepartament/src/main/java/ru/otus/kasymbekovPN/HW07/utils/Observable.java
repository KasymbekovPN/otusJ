package ru.otus.kasymbekovPN.HW07.utils;

import ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult.DepartmentActionResult;

import java.util.Set;

//< rename ???
public interface Observable {

    //< в add/removeObserver initConnection

    /**
     * Подписываем наблюдателя
     * @param o Инстанс-наблюдатель
     */
    DepartmentActionResult subscribe(Observer o);

    /**
     * Отписываем наблюдателя
     * @param o Инстанс-наблюдатель
     */
    DepartmentActionResult unsubscribe(Observer o);

    /**
     * Запрашивает баланс у всех, подписаных на реализацию
     * Observable, наблюдателей
     */
    DepartmentActionResult balanceRequest();

    /**
     * Запрашавает баланс у налюдателей, представленных
     * в списке.
     * @param obs Идентификаторы наблюдателей
     */
    DepartmentActionResult balanceRequest(Set<Integer> obs);

    /**
     * Сбрасывает наблюдателей в исходное состоние.
     */
    DepartmentActionResult resetObserverState();

    /**
     * Сбрасывает наблюдателей, представленных в списке,
     * в исходное состояние.
     * @param obs Идентификаторы наблюдателей
     */
    DepartmentActionResult resetObserverState(Set<Integer> obs);
}
