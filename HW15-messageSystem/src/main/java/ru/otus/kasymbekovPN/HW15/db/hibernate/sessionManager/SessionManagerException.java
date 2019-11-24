package ru.otus.kasymbekovPN.HW15.db.hibernate.sessionManager;

public class SessionManagerException extends RuntimeException {
    public SessionManagerException(String msg){
        super(msg);
    }

    public SessionManagerException(Exception ex){
        super(ex);
    }
}
