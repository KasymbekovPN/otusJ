package ru.otus.kasymbekovPN.HW07.department.operator.command;

import ru.otus.kasymbekovPN.HW07.department.operator.result.OperatorCommandResult;

/**
 * Базовый класс для классов реализующих команды
 * операторов.
 *
 * Содержит в себе:
 *  + идентификатор команды (operatorCommand)
 *  + результат выполнения команды (commandResult)
 */
public class BaseOperatorCmd {

    /**
     * Идентификатор команды
     */
    private OperatorCommand operatorCommand;

    /**
     * Результат выполнения команды
     */
    protected OperatorCommandResult operatorCommandResult;

    /**
     * Конструктор
     * @param operatorCommand идентификатор команды
     * @param operatorCommandResult результат выполнения команды
     */
    BaseOperatorCmd(OperatorCommand operatorCommand, OperatorCommandResult operatorCommandResult) {
        this.operatorCommand = operatorCommand;
        this.operatorCommandResult = operatorCommandResult;
    }

    /**
     * Геттер идентификатора команды
     * @return Идентификатор команды
     */
    public OperatorCommand getOperatorCommand() {
        return operatorCommand;
    }

    /**
     * Геттер результата выполнения команды
     * @return результат выполнения команды
     */
    public OperatorCommandResult getResult() {
        return operatorCommandResult;
    }
}
