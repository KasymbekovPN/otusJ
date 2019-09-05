package ru.otus.kasymbekovPN.HW07.department.command;

//< comment
public enum Cmd {
    NONE("None"),
    TOTAL_BALANCE_REQUEST("Total Balance Request"),
    SELECTIVE_BALANCE_REQUEST("Selective Balance Request");

    private String name;

    public String getName() {
        return name;
    }

    Cmd(String name){
        this.name = name;
    }
}
