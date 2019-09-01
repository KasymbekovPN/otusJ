package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;
import ru.otus.kasymbekovPN.HW07.utils.DepartmentObserver;

/**
 * Класс, хранящий результат действий SUBSCRIBE, UNSUBSCRIBE
 */
public class DepartmentScribeResult implements DepartmentActionResult {

    /**
     * Инстанс, содержащий действие
     */
    private DepartmentActionResult initActRes;

    /**
     * Наблюдатель
     */
    private DepartmentObserver departmentObserver;

    /**
     * Успешность действия
     */
    private boolean success;

    /**
     * Конструктор
     * @param initActRes Инстанс, содкржащий действие
     * @param departmentObserver Наблюдатель
     */
    public DepartmentScribeResult(DepartmentActionResult initActRes, DepartmentObserver departmentObserver,
                                  boolean success){
        this.initActRes = initActRes;
        this.departmentObserver = departmentObserver;
        this.success = success;
    }

    /**
     * Печать результата действия в консоль
     */
    @Override
    public void display() {
        StringBuilder sb = new StringBuilder("ACTION : ")
                .append(getAction().getName())
                .append("; OBSERVER ID : ")
                .append(departmentObserver.getID())
                .append("; SUCCESS : ")
                .append(success);

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
