package ru.otus.kasymbekovPN.HW16M.messageSystem;

public interface MessageSystem {
    boolean newMessage(Message message);
    void dispose() throws  InterruptedException;
}
