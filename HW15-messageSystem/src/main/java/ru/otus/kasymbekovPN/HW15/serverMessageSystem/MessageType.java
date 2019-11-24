package ru.otus.kasymbekovPN.HW15.serverMessageSystem;

public enum MessageType {
    AUTH_USER("AuthUser"),
    ADD_USER("AddUser"),
    DEL_USER("DelUser");

    private final String value;

    public String getValue() {
        return value;
    }

    MessageType(String value) {
        this.value = value;
    }
}
