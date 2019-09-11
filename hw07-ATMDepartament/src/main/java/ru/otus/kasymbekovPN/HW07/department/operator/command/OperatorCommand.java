package ru.otus.kasymbekovPN.HW07.department.operator.command;

/**
 * Перечень команд оператора департамента
 */
public enum OperatorCommand {

    /**
     * Пустая команда
     */
    NONE("None"),

    /**
     * Запрос общего баланса банкоматов департамента
     */
    TOTAL_BALANCE_REQUEST("Total Balance Request"),

    /**
     * Выборочный запрос баланса банкоматов департамента
     */
    SELECTIVE_BALANCE_REQUEST("Selective Balance Request"),

    /**
     * Общий сброс в начальное состояние банкоматов департамента
     */
    TOTAL_RESET_STATE_REQUEST("Total Reset ATM state Request");

    /**
     * Название команды
     */
    private String name;

    /**
     * Геттер названия команды
     * @return Название команды
     */
    public String getName() {
        return name;
    }

    /**
     * Конструктор
     * @param name Название команды
     */
    OperatorCommand(String name){
        this.name = name;
    }
}
