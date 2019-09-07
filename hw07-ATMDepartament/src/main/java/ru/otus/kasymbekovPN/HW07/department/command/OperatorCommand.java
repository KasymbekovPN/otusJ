package ru.otus.kasymbekovPN.HW07.department.command;

//< comment
public enum OperatorCommand {
    NONE("None"),
    TOTAL_BALANCE_REQUEST("Total Balance Request"),
    SELECTIVE_BALANCE_REQUEST("Selective Balance Request"),
    TOTAL_RESET_STATE_REQUEST("Total Reset ATM state Request");

    private String name;

    public String getName() {
        return name;
    }

    OperatorCommand(String name){
        this.name = name;
    }
}
