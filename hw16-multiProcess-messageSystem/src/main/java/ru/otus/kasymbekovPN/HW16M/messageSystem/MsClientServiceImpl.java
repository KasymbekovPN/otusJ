package ru.otus.kasymbekovPN.HW16M.messageSystem;

import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sockets.Entity;
import sockets.SocketHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MsClientServiceImpl implements MsClientService {

    private static final Logger logger = LoggerFactory.getLogger(MsClientServiceImpl.class);

    private static Map<Entity, MsClientCreator> msClientCreators = new HashMap<>();
    static {
        Map<Entity, MsClientCreator> buffer = new HashMap<>();
        buffer.put(Entity.DATABASE, new DbMsClientCreator());
        buffer.put(Entity.FRONTEND, new FeMsClientCreator());
        buffer.put(Entity.MESSAGE_SYSTEM, new WrongMsClientCreator());
        buffer.put(Entity.UNKNOWN, new WrongMsClientCreator());
        msClientCreators = Collections.unmodifiableMap(buffer);
    }

    private final Map<String, MsClient> clientMap = new ConcurrentHashMap<>();
    private final MessageSystem messageSystem;

    private SocketHandler socketHandler;

    public MsClientServiceImpl(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }

    @Override
    public void setSocketHandler(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public boolean createClient(String host, int port, Entity entity) {
        String url = JsonHelper.extractUrl(JsonHelper.makeUrl(host, port, entity));
        if (!clientMap.containsKey(url)){
            MsClient client = msClientCreators.get(entity).create(url, socketHandler, messageSystem);
            if (client != null){
                clientMap.put(url, client);
                messageSystem.addClient(client);
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    @Override
    public MsClient get(String url) {
        return clientMap.getOrDefault(url, null);
    }
}
