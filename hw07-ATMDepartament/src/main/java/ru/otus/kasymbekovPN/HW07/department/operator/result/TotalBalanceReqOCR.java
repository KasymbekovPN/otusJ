package ru.otus.kasymbekovPN.HW07.department.operator.result;

import ru.otus.kasymbekovPN.HW07.department.operator.command.OperatorCommand;

/**
 * Класс, хранящий результат выполнения команды "TOTAL_BALANCE_REQUEST"
 *
 * OCR - Operator's Command Result
 */
public class TotalBalanceReqOCR implements OperatorCommandResult {

    /**
     * Базовый результат, содержит только наименование команды
     */
    private OperatorCommandResult initOCR;

    /**
     * Общий баланс банкоматов департамента
     */
    private int totalBalance;

    /**
     * Конструктор
     * @param initOCR Базовый результат
     * @param totalBalance Общий баланс
     */
    public TotalBalanceReqOCR(OperatorCommandResult initOCR, int totalBalance) {
        this.initOCR = initOCR;
        this.totalBalance = totalBalance;
    }

    /**
     * Печать результата выполнения команды в консоль
     */
    @Override
    public void display() {
        StringBuilder sb = new StringBuilder("\nCOMMAND NAME : ")
                .append(getOperatorCommand().getName())
                .append("; TOTAL BALANCE : ")
                .append(totalBalance);
        System.out.println(sb);
    }

    /**
     * Геттер наименования команды
     * @return Наименование команды.
     */
    @Override
    public OperatorCommand getOperatorCommand() {
        return initOCR.getOperatorCommand();
    }
}
