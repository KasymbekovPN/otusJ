package ru.otus.kasymbekovPN.HW07.department.actions.departmentActionResult;

import ru.otus.kasymbekovPN.HW07.department.actions.DepartmentAction;

import java.util.Set;

/**
 * Класс, хранящий результат действия департамента - выборочный сброс
 * банкоматов в начальное состояние.
 */
public class DepartmentCustResetStateResult implements DepartmentActionResult {

    /**
     * Действие
     */
    final private DepartmentAction action = DepartmentAction.CUSTOM_RESET_STATE;

    /**
     * Идентификаторы банкоматов
     */
    private Set<Integer> IDs;

    /**
     * Конструктор
     * @param IDs идентификаторы банкоматов
     */
    public DepartmentCustResetStateResult(Set<Integer> IDs) {
        this.IDs = IDs;
    }

    /**
     * Выводит в консоль результат действия
     */
    @Override
    public void display() {
        System.out.println("Action : " + action.getName());
        for (Integer id : IDs) {
            System.out.println("# : " + id);
        }
    }
}
