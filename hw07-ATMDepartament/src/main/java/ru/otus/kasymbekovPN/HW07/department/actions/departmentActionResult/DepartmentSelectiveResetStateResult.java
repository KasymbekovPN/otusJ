package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;

import java.util.Map;

/**
 * Класс, хранящий результат дейсвтяи SELECTIVE_RESET_STATE
 */
public class DepartmentSelectiveResetStateResult implements DepartmentActionResult {

    /**
     * Инстанс, хранящий действие
     */
    private DepartmentActionResult initActRes;

    /**
     * Мапа "идентификатор наблюдателя" - "наличие подписки наблюдателя на наблюдаемого"
     */
    private Map<Integer, Boolean> observers;

    /**
     * Конструктор
     * @param initActRes Инстанс, хранящий действие
     * @param observers Мапа "идентификатор наблюдателя" - "наличие подписки наблюдателя на наблюдаемого"
     */
    public DepartmentSelectiveResetStateResult(DepartmentActionResult initActRes, Map<Integer, Boolean> observers) {
        this.initActRes = initActRes;
        this.observers = observers;
    }

    /**
     * Печать результата действия в консоль
     */
    @Override
    public void display() {
        StringBuilder sb = new StringBuilder("ACTION : ")
                .append(getAction().getName())
                .append("\nATMs : ");

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
