package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;

import java.util.Set;

/**
 * Класс, хранящий результат дейсвия департамента - выборочно запросить баланс.
 */
public class DepartmentCustBalanceReqResult implements DepartmentActionResult {

    /**
     * Действие
     */
    final private DepartmentAction action = DepartmentAction.CUSTOM_REQ_BALANCE;

    /**
     * Баланс
     */
    private int balance;

    /**
     * Идентификаторы банкоманов
     */
    private Set<Integer> IDs;

    /**
     * Конструктор
     * @param balance баланс
     * @param IDs идентификаторы банкоматов
     */
    public DepartmentCustBalanceReqResult(int balance, Set<Integer> IDs) {
        this.balance = balance;
        this.IDs = IDs;
    }

    /**
     * Выводит результат действия в консоль.
     */
    @Override
    public void display() {
        System.out.println("Action : " + action.getName());
        System.out.println("Custom Balance : " + balance);
        for (Integer id : IDs) {
            System.out.println("# : " + id);
        }
    }
}
