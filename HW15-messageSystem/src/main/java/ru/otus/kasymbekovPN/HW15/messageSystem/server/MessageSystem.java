package ru.otus.kasymbekovPN.HW15.messageSystem.server;

public interface MessageSystem {
    void addClient(MsClient msClient);
    void removeClient(String clientId);
    boolean newMessage(Message message);
    void dispose() throws InterruptedException;
}
