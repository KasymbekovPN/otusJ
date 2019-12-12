package ru.otus.kasymbekovPN.HW16M.messageSystem.client;

import message.MessageType;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.MSMessageHandler;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;

public interface MsClient {
    void addHandler(MessageType type, MSMessageHandler handler);
    boolean sendMessage(Message message);
    void handle(Message message);
    String getUrl();
//    <T> Message produceMessage(String fromUrl, T data, ReqRespType type);
    //<
    <T> Message produceMessage(String toUrl, T data, MessageType type);
}
