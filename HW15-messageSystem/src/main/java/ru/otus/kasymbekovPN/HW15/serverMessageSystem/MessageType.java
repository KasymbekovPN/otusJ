package ru.otus.kasymbekovPN.HW15.serverMessageSystem;

public enum MessageType {
    USER_DATA("UserData"),
    CHECK_USER("CheckUser"),
    ADD_USER("AddUser"),
    IS_ADMIN("IsAdmin"),
    IS_NOT_ADMIN("IsNotAdmin"),
    WRONG_AUTH_DATA("WrongAuthorizationData");

    private final String value;

    public String getValue() {
        return value;
    }

    MessageType(String value) {
        this.value = value;
    }
}
