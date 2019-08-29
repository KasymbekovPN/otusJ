package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.Department;
import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;
import ru.otus.kasymbekovPN.HW07.utils.Observer;

/**
 * Класс, хранящий результат действий департеманта:
 *  - подписка
 *  - отписка
 */
public class DepartmentScribeResult implements DepartmentActionResult {

    /**
     * Действие
     */
    private DepartmentAction action;

    /**
     * Наблюдатель
     */
    private Observer observer;

    /**
     * Конструктор
     * @param isSubscribe является ли действие подпиской
     * @param observer наблюдатель
     */
    public DepartmentScribeResult(boolean isSubscribe, Observer observer){
        this.action = isSubscribe ? DepartmentAction.SUBSCRIBE : DepartmentAction.UNSUBSCRIBE;
        this.observer = observer;
    }

    /**
     * Выводит результат действия в консоль.
     */
    @Override
    public void display() {
        System.out.println("Action : " + action.getName());
        System.out.println("Observer ID : " + observer.getID());
    }
}
