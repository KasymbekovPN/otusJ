package ru.otus.kasymbekovPN.HW07.department.command.results;

import ru.otus.kasymbekovPN.HW07.department.command.OperatorCommand;

import java.util.Map;

//< ????
public class SelectiveBalanceReqCR implements CommandResult{

    private CommandResult initCR;

    private int selectiveBalance;

//    private Set<Integer> atmIDs;
    //<
    //< rename
    private Map<Integer, Boolean> atmExistMap;

    public SelectiveBalanceReqCR(CommandResult initCR, int selectiveBalance, Map<Integer, Boolean> atmExistMap) {
        this.initCR = initCR;
        this.selectiveBalance = selectiveBalance;
        this.atmExistMap = atmExistMap;
    }

    //<
//    public SelectiveBalanceReqCR(CommandResult initCR, int selectiveBalance, Map<Integer, Boolean> atmExisting) {
//        this.initCR = initCR;
//        this.selectiveBalance = selectiveBalance;
//        this.atmIDs = atmIDs;
//    }

    @Override
    public void display() {
        StringBuilder sb = new StringBuilder("\nCOMMAND NAME : ")
                .append(getOperatorCommand().getName())
                .append("; SELECTIVE BALANCE : ")
                .append(selectiveBalance)
                .append("\nATMs:\n");

        for(Map.Entry<Integer, Boolean> entry : atmExistMap.entrySet()){
            sb.append("ATM ID : ")
                    .append(entry.getKey())
                    .append("; EXISTS : ")
                    .append(entry.getValue())
                    .append("\n");
        }

        System.out.println(sb);
    }

    @Override
    public OperatorCommand getOperatorCommand() {
        return initCR.getOperatorCommand();
    }

    //<
    /*
    @Override
    public void display() {
        StringBuilder sb = new StringBuilder("COMMAND NAME : ")
                .append(getCmd().getName())
                .append("; TOTAL BALANCE : ")
                .append(totalBalance);
        System.out.println(sb);
    }
     */

}
