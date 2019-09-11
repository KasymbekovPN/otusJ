package ru.otus.kasymbekovPN.HW07.department.operator.result;

import ru.otus.kasymbekovPN.HW07.department.operator.command.OperatorCommand;

/**
 * Класс, хранящий базовый результат выполнения команд :
 *  + наименование команды
 *
 * OCR - Operator's Command Result
 */
public class BaseOCR implements OperatorCommandResult {

    /**
     * Наименование команды
     */
    private OperatorCommand operatorCommand;

    /**
     * Конструктор
     * @param operatorCommand наименование команды
     */
    public BaseOCR(OperatorCommand operatorCommand) {
        this.operatorCommand = operatorCommand;
    }

    /**
     * Печать в консоль наименования команды
     */
    @Override
    public void display() {
        System.out.println("\nCOMMAND NAME : " + operatorCommand.getName());
    }

    /**
     * Геттер наименования команды
     * @return Наименование команды
     */
    @Override
    public OperatorCommand getOperatorCommand() {
        return operatorCommand;
    }
}
