package ru.otus.kasymbekovPN.HW07.department.actions;

/**
 * Действия, производимые департаментом
 */
public enum DepartmentAction {
    SUBSCRIBE("subscribe observer"),
    UNSUBSCRIBE("unsubscribe observer"),
    COMMON_REQ_BALANCE("request common balance"),
    CUSTOM_REQ_BALANCE("custom request balance"),
    COMMON_RESET_STATE("common reset state"),
    CUSTOM_RESET_STATE("custom reset state");

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
