package ru.otus.kasymbekovPN.HW07.department.actions;

/**
 * Действия, производимые департаментом
 */
public enum DepartmentAction {
    SUBSCRIBE("subscribe observer"),
    UNSUBSCRIBE("unsubscribe observer"),
    TOTAL_REQ_BALANCE("request common balance"),
    SELECTIVE_REQ_BALANCE("selective request balance"),
    TOTAL_RESET_STATE("common reset state"),
    SELECTIVE_RESET_STATE("selective reset state");

    /**
     * Название действия
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
     * @param name название действия
     */
    DepartmentAction(String name){
        this.name = name;
    }
}
