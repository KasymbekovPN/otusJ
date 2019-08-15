package ru.otus.kasymbekovPN.HW06.atm;

public enum ATMAction {
    ADD("ATM withdrawal"),
    SUB("Putting money in an ATM");

    private String name;

    public String getName() {
        return name;
    }

    ATMAction(String name){
        this.name = name;
    }
}
