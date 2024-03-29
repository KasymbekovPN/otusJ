package ru.otus.kasymbekovPN.HW16M.messageSystem.client;

import common.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import message.MessageType;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.MSMessageHandler;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Клиент системы сообщений {@link ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem} <br><br>
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
public class MsClientImpl implements MsClient {

    private static final Logger logger = LoggerFactory.getLogger(MsClientImpl.class);

    private final String url;
    private final MessageSystem messageSystem;
    private final Map<String, MSMessageHandler> handlers = new ConcurrentHashMap<>();

    public MsClientImpl(String url, MessageSystem messageSystem) {
        this.url = url;
        this.messageSystem = messageSystem;
    }

    @Override
    public void addHandler(MessageType type, MSMessageHandler handler) {
        handlers.put(type.getValue(), handler);
    }

    @Override
    public boolean sendMessage(Message message) {
        boolean result = messageSystem.newMessage(message);
        if (!result){
            logger.error("Last message was reject : {}", message);
        }
        return result;
    }

    @Override
    public void handle(Message message) {
        logger.info("New message : {}", message);
        try{
            MSMessageHandler handler = handlers.get(message.getType());
            if (handler != null){
                handler.handle(message);
            } else {
                logger.error("Handler not found for the message type : {}; url : {}", message.getType(), url);
            }
        } catch(Exception ex){
            logger.error("Message : {}, {}", message, ex);
        }
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public <T> Message produceMessage(String toUrl, T data, MessageType type) {
        return new Message(url, toUrl, type.getValue(), Serializers.serialize(data));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MsClientImpl msClient = (MsClientImpl) o;
        return Objects.equals(url, msClient.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
