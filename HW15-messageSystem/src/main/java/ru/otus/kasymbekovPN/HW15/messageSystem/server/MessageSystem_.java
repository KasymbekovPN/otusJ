package ru.otus.kasymbekovPN.HW15.messageSystem.server;

public interface MessageSystem_ {
    void addClient(MsClient_ msClient);
    void removeClient(String clientId);
    boolean newMessage(Message__ msg);
    void dispose() throws InterruptedException;
}
