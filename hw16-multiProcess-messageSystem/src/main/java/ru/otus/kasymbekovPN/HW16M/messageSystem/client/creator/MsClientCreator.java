package ru.otus.kasymbekovPN.HW16M.messageSystem.client.creator;

import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import sockets.SocketHandler;

/**
 * Интерфейс, служащий для реализации классов, создающих клиенты {@link MsClient} системы сообщений {@link MessageSystem} <br><br>
 */
@FunctionalInterface
public interface MsClientCreator {
    MsClient create(String url, SocketHandler socketHandler, MessageSystem messageSystem);
}
