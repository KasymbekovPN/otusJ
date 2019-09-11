package ru.otus.kasymbekovPN.HW07.department.operator.command;

import ru.otus.kasymbekovPN.HW07.department.Department;
import ru.otus.kasymbekovPN.HW07.department.operator.result.BaseOCR;

import java.util.Set;

/**
 * Класс, реализующий команду оператора департамента,
 * выполняющую выбочный запрос бананса банкоматов
 * департамента.
 */
public class SelectiveBalanceReqOperatorCmd extends BaseOperatorCmd implements Command {

    /**
     * Департамент, над которым производится действие
     */
    private Department department;

    /**
     * Идентификаторы банктоматов, баланс которых запрашивается
     */
    private Set<Integer> atmIDs;

    /**
     * Конструктор
     * @param department департамент
     * @param atmIDs идентификаторы банкоматов
     */
    public SelectiveBalanceReqOperatorCmd(Department department, Set<Integer> atmIDs) {
        super(
                OperatorCommand.SELECTIVE_BALANCE_REQUEST,
                new BaseOCR(OperatorCommand.NONE)
        );
        this.department = department;
        this.atmIDs = atmIDs;
    }

    /**
     * Выполнение действия (получение выборочного баланса
     * банктоматов департамента по идентификатору)
     */
    @Override
    public void execute() {
        operatorCommandResult = department.getBalance(atmIDs);
    }
}

