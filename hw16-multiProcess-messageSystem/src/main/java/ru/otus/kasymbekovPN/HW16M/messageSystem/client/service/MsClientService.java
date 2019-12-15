package ru.otus.kasymbekovPN.HW16M.messageSystem.client.service;

import entity.Entity;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import sockets.SocketHandler;

/**
 * Интерфейс, служащйи для создания сервиса клиентов {@link MsClient} системы сообщений {@link MessageSystem} <br><br>
 *
 * {@link #createClient(String, int, Entity, MessageSystem)} - создание нового клиента <br>
 *
 * {@link #deleteClient(String)} - удаление клиента <br>
 *
 * {@link #setSocketHandler(SocketHandler)} - сеттер обработчика сокета <br>
 *
 * {@link #get(String)} - геттер клиента <br>
 */
public interface MsClientService {
    boolean createClient(String host, int port, Entity entity, MessageSystem messageSystem);
    void deleteClient(String url);
    void setSocketHandler(SocketHandler socketHandler);
    MsClient get(String url);
}
