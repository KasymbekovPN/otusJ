package ru.otus.kasymbekovPN.HW12.db.api.sessionManager;

public class SessionManagerException extends RuntimeException {
    public SessionManagerException(String msg){
        super(msg);
    }

    public SessionManagerException(Exception ex){
        super(ex);
    }
}
