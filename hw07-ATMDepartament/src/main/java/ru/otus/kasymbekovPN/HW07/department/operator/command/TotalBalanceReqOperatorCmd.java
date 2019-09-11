package ru.otus.kasymbekovPN.HW07.department.operator.command;

import ru.otus.kasymbekovPN.HW07.department.Department;
import ru.otus.kasymbekovPN.HW07.department.operator.result.BaseOCR;

/**
 * Класс, реализующий команду оператора департамента,
 * выполняющую запрос общего баланса банкоматов департамента.
 */
public class TotalBalanceReqOperatorCmd extends BaseOperatorCmd implements Command {

    /**
     * Департемент, над которым производится действие.
     */
    private Department department;

    /**
     * Конструктор
     * @param department департамент
     */
    public TotalBalanceReqOperatorCmd(Department department) {
        super(
                OperatorCommand.TOTAL_BALANCE_REQUEST,
                new BaseOCR(OperatorCommand.NONE)
        );
        this.department = department;
    }

    /**
     * Выполнение команды (получение
     */
    @Override
    public void execute() {
        operatorCommandResult = department.getBalance();
    }
}
