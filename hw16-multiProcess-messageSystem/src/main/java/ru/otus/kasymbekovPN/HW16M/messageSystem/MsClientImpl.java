package ru.otus.kasymbekovPN.HW16M.messageSystem;

import common.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.ReqRespType;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MsClientImpl implements MsClient{

    private static final Logger logger = LoggerFactory.getLogger(MsClientImpl.class);

    //< ??? type ???
//    private static Set<String> usedUrls = new HashSet<>();

    private final String url;
    private final MessageSystem messageSystem;
    private final Map<String, MSMessageHandler> handlers = new ConcurrentHashMap<>();

    //<
//    public static MsClientImpl newInstance(String url, MessageSystem messageSystem){
//        if (!usedUrls.contains(url)){
//            usedUrls.add(url);
//            return new MsClientImpl(url, messageSystem);
//        } else {
//            logger.warn("Not unique url");
//            //< what return ???
//            return null;
//        }
//    }

    public MsClientImpl(String url, MessageSystem messageSystem) {
        this.url = url;
        this.messageSystem = messageSystem;
    }

    @Override
    public void addHandler(ReqRespType type, MSMessageHandler handler) {
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
            //<
//            ReqRespHandler handler = handlers.get(message.getType());
//            if (handler != null){
//                handler.handle(message).ifPresent(this::sendMessage);
//            } else {
//                logger.error("Handler not found for the message type : {}; url : {}", message.getType(), url);
//            }
        } catch(Exception ex){
            logger.error("Message : {}, {}", message, ex);
        }
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public <T> Message produceMessage(String toUrl, T data, ReqRespType type) {
//        return new Message(url, toUrl, null, type.getValue(), Serializers.serialize(data));
        //<
        return new Message(url, toUrl, type.getValue(), Serializers.serialize(data));
    }
    //<
    //    @Override
//    public <T> Message produceMessage(String fromUrl, T data, ReqRespType type) {
//        return new Message(fromUrl, url, null, type.getValue(), Serializers.serialize(data));
//    }

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
