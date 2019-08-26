package ru.otus.kasymbekovPN.HW07.atm;

/**
 * Действия, производимые с банкоматом.
 */
public enum AtmAction {
    ADD("ATM withdrawal"),
    SUB("Putting money in an ATM");

    /**
     * Название действия.
     */
    private String name;

    public String getName() {
        return name;
    }

    AtmAction(String name){
        this.name = name;
    }
}
