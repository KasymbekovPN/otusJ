package ru.otus.kasymbekovPN.HW07.department.command;

import ru.otus.kasymbekovPN.HW07.department.Department;
import ru.otus.kasymbekovPN.HW07.department.command.results.BaseCR;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;

//< !!! comment

public class TotalBalanceRequestCmd extends BaseCmd implements Command {

    private Department department;

    public TotalBalanceRequestCmd(Department department) {
        super(
                OperatorCommand.TOTAL_BALANCE_REQUEST,
                new BaseCR(OperatorCommand.NONE)
        );
        this.department = department;
    }

    @Override
    public void execute() {
        commandResult = department.getBalance();
    }
}

//<
//public class TotalBalanceRequestCmd implements Command, CommandExtracting {
//
//    final private OperatorCommand operatorCommand = OperatorCommand.TOTAL_BALANCE_REQUEST;
//
//    private Department department;
//
//    private CommandResult commandResult;
//
//    public TotalBalanceRequestCmd(Department department) {
//        this.department = department;
//        this.commandResult = new BaseCR(OperatorCommand.NONE);
//    }
//
//    @Override
//    public void execute() {
//        commandResult = department.getBalance();
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
