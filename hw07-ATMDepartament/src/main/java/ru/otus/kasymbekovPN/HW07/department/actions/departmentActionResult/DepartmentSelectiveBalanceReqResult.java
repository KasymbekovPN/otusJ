package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;

import java.util.Map;

/**
 * Класс, хранящий результат действия SELECTIVE_REQ_BALANCE
 */
public class DepartmentSelectiveBalanceReqResult implements DepartmentActionResult {

    /**
     * Инстанс, хранящий в себе действие
     */
    private DepartmentActionResult initActRes;

    /**
     * Выборочный баланс
     */
    private int selectiveBalance;

    /**
     * Мапа "идентификатор наблюдателя" - "наличие подписки наблюдателя на наблюдаемого"
     */
    private Map<Integer, Boolean> observers;

    /**
     * Конструктор
     * @param initActRes ИНстанс, содержащий действие
     * @param selectiveBalance выборочный баланс
     * @param observers Мапа "идентификатор наблюдателя" - "наличие подписки наблюдателя на наблюдаемого"
     */
    public DepartmentSelectiveBalanceReqResult(DepartmentActionResult initActRes,
                                               int selectiveBalance,
                                               Map<Integer, Boolean> observers) {
        this.initActRes = initActRes;
        this.selectiveBalance = selectiveBalance;
        this.observers = observers;
    }

    /**
     * Печать результата действия в консоль
     */
    @Override
    public void display() {

        StringBuilder sb = new StringBuilder("ACTION : ")
                .append(getAction().getName())
                .append("; SELECTIVE BALANCE : ")
                .append(selectiveBalance)
                .append("; \nATMs : ");
        for(Map.Entry<Integer, Boolean> entry : observers.entrySet()){
            sb.append("\nID : ")
                    .append(entry.getKey())
                    .append("; EXIST-STATUS : ")
                    .append(entry.getValue());
        }

        System.out.println(sb);
    }

    /**
     * Геттер действия
     * @return Действие
     */
    @Override
    public DepartmentAction getAction() {
        return initActRes.getAction();
    }


}
