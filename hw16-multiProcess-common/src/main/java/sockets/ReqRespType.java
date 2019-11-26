package sockets;

public enum ReqRespType {
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

    ReqRespType(String value) {
        this.value = value;
    }
}
