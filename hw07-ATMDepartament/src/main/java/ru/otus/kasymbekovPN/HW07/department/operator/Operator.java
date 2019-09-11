package ru.otus.kasymbekovPN.HW07.department.operator;

import ru.otus.kasymbekovPN.HW07.department.operator.command.BaseOperatorCmd;
import ru.otus.kasymbekovPN.HW07.department.operator.command.OperatorCommand;
import ru.otus.kasymbekovPN.HW07.department.operator.command.Command;
import ru.otus.kasymbekovPN.HW07.department.operator.result.BaseOCR;
import ru.otus.kasymbekovPN.HW07.department.operator.result.OperatorCommandResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс, реализующий оператора, работающего с департаментом.
 */
public class Operator {

    /**
     * ключ : идентификатор команды
     * значение : команда
     */
    private Map<OperatorCommand, Command> commands;

    /**
     * Конструктор
     */
    public Operator(){
        this.commands = new HashMap<>();
    }

    /**
     * Сеттер команды
     * @param command команда
     * @return инстанс
     */
    public Operator setCommand(Command command){
        var bc = (BaseOperatorCmd) command;
        commands.put(bc.getOperatorCommand(), command);
        return this;
    }

    /**
     * Выполнение команды
     * @param operatorCommand Идентификатор команды
     * @return Результат выполнения команды
     */
    public OperatorCommandResult execute(OperatorCommand operatorCommand){
        if (commands.containsKey(operatorCommand)){
            Command command = commands.get(operatorCommand);
            command.execute();
            return ((BaseOperatorCmd)command).getResult();
        }

        return new BaseOCR(OperatorCommand.NONE);
    }
}
