package sockets;

import com.google.gson.JsonObject;

import java.util.List;

public interface SocketSendingHandler {
    void init(List<String> hosts, List<Integer> ports);
    void send(JsonObject jsonObject);
}
