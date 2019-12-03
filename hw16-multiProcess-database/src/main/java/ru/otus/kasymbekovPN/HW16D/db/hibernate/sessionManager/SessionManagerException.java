package ru.otus.kasymbekovPN.HW16D.db.hibernate.sessionManager;

public class SessionManagerException extends RuntimeException {
    public SessionManagerException(String msg){
        super(msg);
    }

    public SessionManagerException(Exception ex){
        super(ex);
    }
}
