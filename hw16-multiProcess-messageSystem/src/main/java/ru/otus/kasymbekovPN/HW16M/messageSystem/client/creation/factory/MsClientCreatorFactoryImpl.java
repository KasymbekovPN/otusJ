package ru.otus.kasymbekovPN.HW16M.messageSystem.client.creation.factory;

import entity.Entity;
import message.MessageType;
import org.springframework.stereotype.Service;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.creation.creator.CmnMsClientCreator;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.creation.creator.MsClientCreator;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.creation.creator.WrongMsClientCreator;

import java.util.HashMap;
import java.util.Map;

@Service
public class MsClientCreatorFactoryImpl implements MsClientCreatorFactory{

    private final Map<Entity, MsClientCreator> creators = new HashMap<Entity, MsClientCreator>(){{
        put(Entity.DATABASE, new CmnMsClientCreator(MessageType.AUTH_USER_REQUEST, MessageType.ADD_USER_REQUEST, MessageType.DEL_USER_REQUEST));
        put(Entity.FRONTEND, new CmnMsClientCreator(MessageType.AUTH_USER_RESPONSE, MessageType.ADD_USER_RESPONSE, MessageType.DEL_USER_RESPONSE));
        put(Entity.MESSAGE_SYSTEM, new WrongMsClientCreator());
        put(Entity.UNKNOWN, new WrongMsClientCreator());
    }};

    public MsClientCreatorFactoryImpl() throws Exception {
        StringBuilder status = new StringBuilder();
        for (Entity entity : Entity.values()) {
            if (!creators.containsKey(entity)){
                status.append(" ").append(entity.getValue()).append(";");
            }
        }

        if (!status.toString().isEmpty()){
            throw new Exception("Creators is not complete : " + status);
        }
    }

    @Override
    public MsClientCreator get(Entity entity) {
        return creators.get(entity);
    }
}
