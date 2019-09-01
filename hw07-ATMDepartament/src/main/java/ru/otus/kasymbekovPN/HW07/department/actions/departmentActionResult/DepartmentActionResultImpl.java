package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;

/**
 * Класс, содержащий действие департамента.
 */
public class DepartmentActionResultImpl implements DepartmentActionResult {
    private DepartmentAction action;

    public DepartmentActionResultImpl(DepartmentAction action){
        this.action = action;
    }

    /**
     * Печать названия дейстия в консоль.
     */
    @Override
    public void display() {
        System.out.println("ACTION : " + action.getName());
    }

    /**
     * Геттер действия
     * @return Действия
     */
    @Override
    public DepartmentAction getAction() {
        return action;
    }
}
