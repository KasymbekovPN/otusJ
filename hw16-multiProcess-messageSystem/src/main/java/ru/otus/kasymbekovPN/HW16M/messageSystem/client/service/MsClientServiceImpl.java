package ru.otus.kasymbekovPN.HW16M.messageSystem.client.service;

import entity.Entity;
import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.creation.factory.MsClientCreatorFactory;
import sockets.SocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Сервис клиентов {@link MsClient} системы сообщений {@link MessageSystem} <br><br>
 *
 * {@link #createClient(String, int, Entity, MessageSystem)} - создание нового клиента.
 *
 * {@link #deleteClient(String)} - удаление клиента <br>
 *
 * {@link #setSocketHandler(SocketHandler)} - сеттер обработчика сокета <br>
 *
 * {@link #get(String)} - геттер клиента <br>
 */
@Service
public class MsClientServiceImpl implements MsClientService {

    private static final Logger logger = LoggerFactory.getLogger(MsClientServiceImpl.class);

    private final MsClientCreatorFactory msClientCreatorFactory;
    //<
//    private static Map<Entity, MsClientCreator> msClientCreators = new HashMap<>();
//    static {
//        Map<Entity, MsClientCreator> buffer = new HashMap<>();
//        buffer.put(Entity.DATABASE, new DbMsClientCreator());
//        buffer.put(Entity.FRONTEND, new FeMsClientCreator());
//        buffer.put(Entity.MESSAGE_SYSTEM, new WrongMsClientCreator());
//        buffer.put(Entity.UNKNOWN, new WrongMsClientCreator());
//        msClientCreators = Collections.unmodifiableMap(buffer);
//
//        System.out.println("--------------- " + 123);
//    }

    private final Map<String, MsClient> clients = new HashMap<>();

    private SocketHandler socketHandler;

    public MsClientServiceImpl(MsClientCreatorFactory msClientCreatorFactory) {
        this.msClientCreatorFactory = msClientCreatorFactory;
    }

    @Override
    public synchronized boolean createClient(String host, int port, Entity entity, MessageSystem messageSystem) {
        String url = JsonHelper.extractUrl(JsonHelper.makeUrl(host, port, entity));
        if (!clients.containsKey(url)){
//            MsClient client = msClientCreators.get(entity).create(url, socketHandler, messageSystem);
            //<
            final MsClient client = msClientCreatorFactory.get(entity).create(url, socketHandler, messageSystem);
            if (client != null){
                clients.put(url, client);
                return true;
            } else {
                return false;
            }
        }

        return true;
    }

    @Override
    public synchronized void deleteClient(String url) {
        MsClient removedClient = clients.remove(url);
        if (removedClient == null){
            logger.warn("MsClientServiceImpl::deleteClient : client '{}' not found", url);
        } else {
            logger.info("MsClientServiceImpl::deleteClient : client '{}' was delete", url);
        }
    }

    @Override
    public synchronized void setSocketHandler(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public synchronized Optional<MsClient> get(String url) {
        return Optional.ofNullable(clients.getOrDefault(url, null));
    }
}
