package message;

public enum MessageType {
    I_AM_REQUEST("iAmRequest"),
    I_AM_RESPONSE("iAmResponse"),
    WRONG_TYPE("wrong"),
    AUTH_USER_REQUEST("authUserRequest"),
    ADD_USER_REQUEST("addUserRequest"),
    DEL_USER_REQUEST("delUserRequest"),
    AUTH_USER_RESPONSE("authUserResponse"),
    ADD_USER_RESPONSE("addUserResponse"),
    DEL_USER_RESPONSE("delUserResponse");

    private String value;

    public String getValue() {
        return value;
    }

    MessageType(String value) {
        this.value = value;
    }
}
