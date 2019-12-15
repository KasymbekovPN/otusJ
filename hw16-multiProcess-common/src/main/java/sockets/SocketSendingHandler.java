package sockets;

import com.google.gson.JsonObject;

import java.util.List;

/**
 * Интерфейс, служащий для реализации класса, производящего отпраку сообщений.<br><br>
 *
 * {@link #send(JsonObject)} - отправка сообщения <br>
 *
 * {@link #init(List, List)} - инициализация <br>
 */
public interface SocketSendingHandler {
    void init(List<String> hosts, List<Integer> ports);
    void send(JsonObject jsonObject);
}
