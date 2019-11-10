package ru.otus.kasymbekovPN.HW15.messageSystem.server;

public enum MessageType {
    USER_DATA("UserData");

    private final String value;

    public String getValue() {
        return value;
    }

    MessageType(String value) {
        this.value = value;
    }
}
