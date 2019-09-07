package ru.otus.kasymbekovPN.HW07.department.command;

import ru.otus.kasymbekovPN.HW07.department.Department;
import ru.otus.kasymbekovPN.HW07.department.command.results.BaseCR;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;

//< ????

public class TotalResetStateRequestCmd extends BaseCmd implements Command  {

    private Department department;

    public TotalResetStateRequestCmd(Department department) {
        super(
                OperatorCommand.TOTAL_RESET_STATE_REQUEST,
                new BaseCR(OperatorCommand.NONE)
        );
        this.department = department;
    }

    @Override
    public void execute() {
        commandResult = department.resetState();
    }
}

//<
//public class TotalResetStateRequestCmd implements Command, CommandExtracting {
//
//    final private OperatorCommand operatorCommand = OperatorCommand.TOTAL_RESET_STATE_REQUEST;
//
//    private Department department;
//
//    private CommandResult commandResult;
//
//    public TotalResetStateRequestCmd(Department department) {
//        this.department = department;
//        this.commandResult = new BaseCR(OperatorCommand.NONE);
//    }
//
//    @Override
//    public void execute() {
//        commandResult = department.resetState();
//    }
//
//    //< in base class
//    @Override
//    public OperatorCommand getOperatorCommand() {
//        return operatorCommand;
//    }
//
//    //< in base class
//    @Override
//    public CommandResult getResult() {
//        return commandResult;
//    }
//}
