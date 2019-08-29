package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;

/**
 * Класс, хранящий результат действия департамента - общий сброс
 * банкоматов в начальное состяние.
 */
public class DepartmentComResetStateResult implements DepartmentActionResult {

    /**
     * Действие
     */
    final private DepartmentAction action = DepartmentAction.COMMON_RESET_STATE;

    /**
     * Выводит результат действия в консоль.
     */
    @Override
    public void display() {
        System.out.println("Action : " + action.getName());
    }
}
