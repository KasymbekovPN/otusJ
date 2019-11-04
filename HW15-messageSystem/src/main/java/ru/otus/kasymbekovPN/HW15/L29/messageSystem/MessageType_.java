package ru.otus.kasymbekovPN.HW15.L29.messageSystem;

public enum MessageType_ {
    USER_DATA("UserData");

    private final String value;

    MessageType_(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
