package ru.otus.kasymbekovPN.HW16M.messageSystem;

public interface MessageSystem {
    void adClient(MsClient msClient);
    void removeClient(String clientId);
    boolean newMessage(Message message);
    void dispose() throws  InterruptedException;
}
