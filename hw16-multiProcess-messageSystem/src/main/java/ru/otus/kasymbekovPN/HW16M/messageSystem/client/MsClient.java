package ru.otus.kasymbekovPN.HW16M.messageSystem.client;

import message.MessageType;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.MSMessageHandler;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;

/**
 * Интерфейс, служащий для создания клиента системы сообщений {@link ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem} <br><br>
 *
 * {@link #addHandler(MessageType, MSMessageHandler)} - добавление обработчика сообщений {@link MSMessageHandler} <br><br>
 *
 * {@link #sendMessage(Message)} - отправка сообщения <br><br>
 *     
 * {@link #handle(Message)} - обработка принятого сообщения <br><br>
 *     
 * {@link #getUrl()} - геттер уникального url <br><br>
 *     
 * {@link #produceMessage(String, Object, MessageType)} - создание сообщения <br><br>
 */
public interface MsClient {
    void addHandler(MessageType type, MSMessageHandler handler);
    boolean sendMessage(Message message);
    void handle(Message message);
    String getUrl();
    <T> Message produceMessage(String toUrl, T data, MessageType type);
}
