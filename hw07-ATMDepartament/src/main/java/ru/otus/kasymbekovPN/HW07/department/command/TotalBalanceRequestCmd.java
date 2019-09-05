package ru.otus.kasymbekovPN.HW07.department.command;

import ru.otus.kasymbekovPN.HW07.department.Department;
import ru.otus.kasymbekovPN.HW07.department.command.results.BaseCR;
import ru.otus.kasymbekovPN.HW07.department.command.results.CommandResult;
import ru.otus.kasymbekovPN.HW07.department.command.results.TotalBalanceReqCR;

//< !!! comment
public class TotalBalanceRequestCmd implements Command, CommandExtracting {

    final private Cmd cmd = Cmd.TOTAL_BALANCE_REQUEST;

    private Department department;

    private CommandResult commandResult;

    public TotalBalanceRequestCmd(Department department) {
        this.department = department;
        this.commandResult = new BaseCR(Cmd.NONE);
    }

    @Override
    public void execute() {
        commandResult = new TotalBalanceReqCR(
                new BaseCR(Cmd.TOTAL_BALANCE_REQUEST),
                department.getTotalBalance()
        );
        // make result
        //<
//        System.out.println(department.getTotalBalance());
    }

    @Override
    public Cmd getCmd() {
        return cmd;
    }

    @Override
    public CommandResult getResult() {
        return commandResult;
    }
}
