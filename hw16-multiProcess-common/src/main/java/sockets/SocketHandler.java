package sockets;

import com.google.gson.JsonObject;

import java.util.List;

public interface SocketHandler {
    void sendF(JsonObject jsonObject);
    void send(JsonObject jsonObject, String targetHost, int targetPort, String fromEntity);
    void addHandler(String name, SocketInputHandler handler);
    void setArgs(Entity entity, List<String> hosts, List<Integer> ports);
}
