package ru.otus.kasymbekovPN.HW07.department.command;

import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;

//< ???
public class BaseCmd {

    private OperatorCommand operatorCommand;

    protected CommandResult commandResult;

    public BaseCmd(OperatorCommand operatorCommand, CommandResult commandResult) {
        this.operatorCommand = operatorCommand;
        this.commandResult = commandResult;
    }

    public OperatorCommand getOperatorCommand() {
        return operatorCommand;
    }

    public CommandResult getResult() {
        return commandResult;
    }
}
