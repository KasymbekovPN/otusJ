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

    /**
     * Геттер названия действия
     * @return название действия
     */
    public String getName() {
        return name;
    }

    /**
     * Конструктор
     * @param name Название действия
     */
    AtmAction(String name){
        this.name = name;
    }
}
