package ru.otus.kasymbekovPN.HW15.L29.messageSystem;

public interface MsClient_ {
    void addHandler(MessageType_ type_, RequestHandler_ requestHandler);;
    boolean sendMessage(Message__ msg);
    void handle(Message__ msg);
    String getName();
    <T> Message__ produceMessage(String to, T data, MessageType_ msgType);
}
