package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;

/**
 * Класс, хранящий результат действия департамента - запросить общий баланс
 */
public class DepartmentComBalanceReqResult implements DepartmentActionResult {

    /**
     * Действие
     */
    final private DepartmentAction action = DepartmentAction.COMMON_REQ_BALANCE;

    /**
     * Общий баланс
     */
    private int balance;

    /**
     * Конструктор
     * @param balance Общий баланс
     */
    public DepartmentComBalanceReqResult(int balance) {
        this.balance = balance;
    }

    /**
     * Выводит результат действия в консоль.
     */
    @Override
    public void display() {
        System.out.println("Action : " + action.getName());
        System.out.println("Common Balance : " + balance);
    }
}
