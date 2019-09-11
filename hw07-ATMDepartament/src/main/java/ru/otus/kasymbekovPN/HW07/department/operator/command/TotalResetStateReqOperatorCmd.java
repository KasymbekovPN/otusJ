package ru.otus.kasymbekovPN.HW07.department.operator.command;

import ru.otus.kasymbekovPN.HW07.department.Department;
import ru.otus.kasymbekovPN.HW07.department.operator.result.BaseOCR;

/**
 * Класс, реализующий команду оперетора департамента,
 * выполняющую сброс банкоматов департамента в начальное
 * состояние.
 */
public class TotalResetStateReqOperatorCmd extends BaseOperatorCmd implements Command  {

    /**
     * Департамент, над которым производится действие.
     */
    private Department department;

    /**
     * Конструктор
     * @param department департамент
     */
    public TotalResetStateReqOperatorCmd(Department department) {
        super(
                OperatorCommand.TOTAL_RESET_STATE_REQUEST,
                new BaseOCR(OperatorCommand.NONE)
        );
        this.department = department;
    }

    /**
     * Выполнение команды (сброс всех банкоматов департамент
     * в начальное состояние)
     */
    @Override
    public void execute() {
        operatorCommandResult = department.resetState();
    }
}
