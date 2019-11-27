package ru.otus.kasymbekovPN.HW16M.messageSystem;

import common.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.ReqRespType;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MsClientImpl implements MsClient{

    private static final Logger logger = LoggerFactory.getLogger(MsClientImpl.class);

    //< ??? type ???
    private static Set<String> usedUrls = new HashSet<>();

    private final String host;
    private final int port;
    private final MessageSystem messageSystem;
    private final Map<String, ReqRespHandler> handlers = new ConcurrentHashMap<>();

    public MsClientImpl newInstance(String host, int port, MessageSystem messageSystem){
        String url = host + String.valueOf(port);
        if (!usedUrls.contains(url)){
            usedUrls.add(url);
            return new MsClientImpl(host, port, messageSystem);
        } else {
            logger.warn("Not unique url");
            //< what return ???
            return null;
        }
    }

    private MsClientImpl(String host, int port, MessageSystem messageSystem) {
        this.host = host;
        this.port = port;
        this.messageSystem = messageSystem;
    }

    @Override
    public void addHandler(ReqRespType type, ReqRespHandler handler) {
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
            ReqRespHandler handler = handlers.get(message.getType());
            if (handler != null){
                handler.handle(message).ifPresent(this::sendMessage);
            } else {
                logger.error("Handler not found for the message type : {}", message.getType());
            }
        } catch(Exception ex){
            logger.error("Message : {}, {}", message, ex);
        }
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public <T> Message produceMessage(String toHost, int toPort, T data, ReqRespType type) {
        return new Message(host, port, toHost, toPort, null, type.getValue(), Serializers.serialize(data));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MsClientImpl msClient = (MsClientImpl) o;
        return port == msClient.port &&
                Objects.equals(host, msClient.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
