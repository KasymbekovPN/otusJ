package ru.otus.kasymbekovPN.HW07.department.command.results;

import ru.otus.kasymbekovPN.HW07.department.command.Cmd;

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
        StringBuilder sb = new StringBuilder("COMMAND NAME : ")
                .append(getCmd().getName())
                .append("; TOTAL BALANCE : ")
                .append(totalBalance);
        System.out.println(sb);
    }

    @Override
    public Cmd getCmd() {
        return initCR.getCmd();
    }
}
