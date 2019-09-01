package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;

/**
 * Интерфейс для реализации результата действия
 * департамента банкоматов.
 */
public interface DepartmentActionResult {

    /**
     * Печать результата действия в консоль.
     */
    void display();

    /**
     * Геттер действия
     * @return Действия
     */
    DepartmentAction getAction();
}
