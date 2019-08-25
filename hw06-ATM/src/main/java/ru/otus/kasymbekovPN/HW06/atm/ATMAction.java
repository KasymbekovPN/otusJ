package ru.otus.kasymbekovPN.HW06.atm;

/**
 * Действия, производимые с банкоматом.
 */
public enum ATMAction {
    ADD("ATM withdrawal"),
    SUB("Putting money in an ATM");

    /**
     * Название действия.
     */
    private String name;

    public String getName() {
        return name;
    }

    ATMAction(String name){
        this.name = name;
    }
}
