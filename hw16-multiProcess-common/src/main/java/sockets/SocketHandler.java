package sockets;

import com.google.gson.JsonObject;
import entity.Entity;

import java.util.List;

/**
 * Интерфейс, служащий для реализации класса-обработчика сокетов.<br><br>
 *
 * {@link SocketHandler#send(JsonObject)} - отправка json-сообщений<br>
 *
 * {@link SocketHandler#addHandler(String, SocketInputHandler)} - добавление обработчиков принятых сообщений<br>
 *
 * {@link SocketHandler#init(Entity, List, List)} - инициализация<br>
 */
public interface SocketHandler {
    void send(JsonObject jsonObject);
    void addHandler(String name, SocketInputHandler handler);
    void init(Entity entity, List<String> hosts, List<Integer> ports);
}
