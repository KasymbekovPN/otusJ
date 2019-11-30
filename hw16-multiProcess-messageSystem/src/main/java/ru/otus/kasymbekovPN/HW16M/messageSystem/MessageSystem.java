package ru.otus.kasymbekovPN.HW16M.messageSystem;

public interface MessageSystem {
    void addClient(MsClient msClient);
    MsClient getClient(String url);
    void removeClient(String clientId);
    boolean newMessage(Message message);
    void dispose() throws  InterruptedException;
}
