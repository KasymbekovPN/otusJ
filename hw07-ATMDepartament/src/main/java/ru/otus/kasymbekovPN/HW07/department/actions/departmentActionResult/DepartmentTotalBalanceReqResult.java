package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;

/**
 * Класс, хранящий результат действия TOTAL_REQ_BALANCE
 */
public class DepartmentTotalBalanceReqResult implements DepartmentActionResult{

    /**
     * Инстанс, содержащий действие
     */
    private DepartmentActionResult initActRes;

    /**
     * Общий баланс
     */
    private int totalBalance;

    /**
     * Конструктор
     * @param initActRes Инстанс, содержащий дейсвтие
     * @param totalBalance общий баланс
     */
    public DepartmentTotalBalanceReqResult(DepartmentActionResult initActRes, int totalBalance) {
        this.initActRes = initActRes;
        this.totalBalance = totalBalance;
    }

    /**
     * Печать результата действия в консоль
     */
    @Override
    public void display() {
        StringBuilder sb = new StringBuilder("ACTION : ")
                .append(getAction().getName())
                .append("; COMMON BALANCE : ")
                .append(totalBalance);

        System.out.println(sb);
    }

    /**
     * Геттер действия
     * @return действие
     */
    @Override
    public DepartmentAction getAction() {
        return initActRes.getAction();
    }
}
