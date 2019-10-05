package ru.otus.kasymbekovPN.HW09.api.sessionManager;

public class SessionManagerException extends RuntimeException {
    public SessionManagerException(String msg){
        super(msg);
    }
    public SessionManagerException(Exception ex){
        super(ex);
    }
}
