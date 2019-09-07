package ru.otus.kasymbekovPN.HW07.department.command;

import ru.otus.kasymbekovPN.HW07.department.Department;
import ru.otus.kasymbekovPN.HW07.department.command.results.BaseCR;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;

import java.util.Set;

//< !!!
//< CommandExtracting - rename ???

public class SelectiveBalanceRequestCmd extends BaseCmd implements Command {

    private Department department;

    private Set<Integer> atmIDs;

    public SelectiveBalanceRequestCmd(Department department, Set<Integer> atmIDs) {
        super(
                OperatorCommand.SELECTIVE_BALANCE_REQUEST,
                new BaseCR(OperatorCommand.NONE)
        );
        this.department = department;
        this.atmIDs = atmIDs;
    }

    @Override
    public void execute() {
        commandResult = department.getBalance(atmIDs);
    }
}

//<
//public class SelectiveBalanceRequestCmd implements Command, CommandExtracting {
//
//    final private OperatorCommand operatorCommand = OperatorCommand.SELECTIVE_BALANCE_REQUEST;
//
//    private Department department;
//
//    private CommandResult commandResult;
//
//    private Set<Integer> atmIDs;
//
//    public SelectiveBalanceRequestCmd(Department department, Set<Integer> atmIDs) {
//        this.department = department;
//        this.atmIDs = atmIDs;
//        this.commandResult = new BaseCR(OperatorCommand.NONE);
//    }
//
//    @Override
//    public void execute() {
//        commandResult = department.getBalance(atmIDs);
//    }
//
//    @Override
//    public OperatorCommand getOperatorCommand() {
//        return operatorCommand;
//    }
//
//    @Override
//    public CommandResult getResult() {
//        return commandResult;
//    }
//}
