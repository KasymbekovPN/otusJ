package ru.otus.kasymbekovPN.HW15.messageSystem.server;

public interface MsClient {
    void addHandler(MessageType type, RequestHandler requestHandler);
    boolean sendMessage(Message message);
    void handle(Message message);
    String getName();
    <T> Message produceMessage(String to, T data, MessageType messageType);
}
