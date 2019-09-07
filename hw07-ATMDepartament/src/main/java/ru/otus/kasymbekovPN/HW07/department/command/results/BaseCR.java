package ru.otus.kasymbekovPN.HW07.department.command.results;

import ru.otus.kasymbekovPN.HW07.department.command.OperatorCommand;

//< !!!
public class BaseCR implements CommandResult {

    private OperatorCommand operatorCommand;

    public BaseCR(OperatorCommand operatorCommand) {
        this.operatorCommand = operatorCommand;
    }

    @Override
    public void display() {
        System.out.println("\nCOMMAND NAME : " + operatorCommand.getName());
    }

    @Override
    public OperatorCommand getOperatorCommand() {
        return operatorCommand;
    }
}
