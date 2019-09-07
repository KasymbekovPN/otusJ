package ru.otus.kasymbekovPN.HW07.department.command.results;

import ru.otus.kasymbekovPN.HW07.department.command.OperatorCommand;

//< ???
public class TotalBalanceReqCR implements CommandResult {

    private CommandResult initCR;

    private int totalBalance;

    public TotalBalanceReqCR(CommandResult initCR, int totalBalance) {
        this.initCR = initCR;
        this.totalBalance = totalBalance;
    }

    @Override
    public void display() {
        StringBuilder sb = new StringBuilder("\nCOMMAND NAME : ")
                .append(getOperatorCommand().getName())
                .append("; TOTAL BALANCE : ")
                .append(totalBalance);
        System.out.println(sb);
    }

    @Override
    public OperatorCommand getOperatorCommand() {
        return initCR.getOperatorCommand();
    }
}
