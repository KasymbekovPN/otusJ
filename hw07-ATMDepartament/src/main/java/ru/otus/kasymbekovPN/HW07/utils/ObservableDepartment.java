package ru.otus.kasymbekovPN.HW07.utils;

import ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult.DepartmentActionResult;

import java.util.Set;

/**
 * Интерфейс, служащий для реализации функционала
 * департемента банкоматов, как наблюдаемого объекта.
 */
public interface ObservableDepartment {

    /**
     * Подписываем наблюдателя
     * @param departmentObserver Наблюдатель
     * @return Результат действия
     */
    DepartmentActionResult subscribe(DepartmentObserver departmentObserver);

    /**
     * Отписываем наблюдателя
     * @param departmentObserver Наблюдатель
     * @return Результат действия
     */
    DepartmentActionResult unsubscribe(DepartmentObserver departmentObserver);

    /**
     * Запрашивает баланс у всех, подписаных на реализацию
     * Observable, наблюдателей
     * @return Результат действия
     */
    DepartmentActionResult balanceRequest();

    /**
     * Запрашавает баланс у налюдателей, чьи идентификаторы
     * представленны в списке.
     * @param ids Идентификаторы наблюдателей
     * @return Результат действия
     */
    DepartmentActionResult balanceRequest(Set<Integer> ids);

    /**
     * Сбрасывает наблюдателей в исходное состоние.
     * @return Результат действия
     */
    DepartmentActionResult resetObserverState();

    /**
     * Сбрасываем наблюдателей, идентификаторы который
     * представлены в списке, в исходное состояние
     * @param ids Идентификаторы набдюдателей
     * @return Результат действия
     */
    DepartmentActionResult resetObserverState(Set<Integer> ids);
}
