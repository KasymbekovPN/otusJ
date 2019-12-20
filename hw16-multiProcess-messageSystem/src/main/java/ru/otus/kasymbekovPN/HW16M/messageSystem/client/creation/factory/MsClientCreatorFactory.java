package ru.otus.kasymbekovPN.HW16M.messageSystem.client.creation.factory;

import entity.Entity;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.creation.creator.MsClientCreator;

public interface MsClientCreatorFactory {
    MsClientCreator get(Entity entity);
}
