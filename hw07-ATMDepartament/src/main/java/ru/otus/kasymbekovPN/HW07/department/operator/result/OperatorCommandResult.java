package ru.otus.kasymbekovPN.HW07.department.operator.result;

import ru.otus.kasymbekovPN.HW07.department.operator.command.OperatorCommand;

/**
 * Интерфейс для реализации классов, хранящих результат
 * выполнения команд оператора.
 */
public interface OperatorCommandResult {

    /**
     * Печать результата выполнения команды в консоль
     */
    void display();

    /**
     * Геттер наименования команды
     * @return Наименование команды.
     */
    OperatorCommand getOperatorCommand();
}
