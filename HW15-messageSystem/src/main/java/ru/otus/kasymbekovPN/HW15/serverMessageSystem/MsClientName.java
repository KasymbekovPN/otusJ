package ru.otus.kasymbekovPN.HW15.serverMessageSystem;

/**
 * Перечисление клиентов {@link MsClient} системы обмена сообщениями {@link MessageSystem}.
 */
public enum MsClientName {
    FRONTEND("frontendService"),
    DATABASE("databaseService");

    private String name;

    public String getName() {
        return name;
    }

    MsClientName(String name) {
        this.name = name;
    }
}
