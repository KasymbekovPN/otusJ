package ru.otus.kasymbekovPN.HW15.messageSystem.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW15.common.Serializers;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MsClientImpl_ implements MsClient_ {

    private static final Logger logger = LoggerFactory.getLogger(MsClientImpl_.class);

    private final String name;
    private final MessageSystem_ messageSystem;
    private final Map<String, RequestHandler_> handlers = new ConcurrentHashMap<>();

    public MsClientImpl_(String name, MessageSystem_ messageSystem) {
        this.name = name;
        this.messageSystem = messageSystem;
    }

    @Override
    public void addHandler(MessageType_ type, RequestHandler_ requestHandler) {
        handlers.put(type.getValue(), requestHandler);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean sendMessage(Message__ msg) {
        boolean result = messageSystem.newMessage(msg);
        if (!result){
            logger.error("The last message was reject : {}", msg);
        }
        return result;
    }

    @Override
    public void handle(Message__ msg) {
        logger.info("new message : {}", msg);
        try{
            RequestHandler_ requestHandler = handlers.get(msg.getType());
            if (requestHandler != null){
                requestHandler.handle(msg).ifPresent(this::sendMessage);
            } else {
                logger.error("handler not found for the message type : {}", msg.getType());
            }
        } catch (Exception ex){
            logger.error("msg : {}, {}", msg, ex);
        }
    }

    @Override
    public <T> Message__ produceMessage(String to, T data, MessageType_ msgType) {
        return new Message__(name, to, null, msgType.getValue(), Serializers.serialize(data));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MsClientImpl_ that = (MsClientImpl_) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
