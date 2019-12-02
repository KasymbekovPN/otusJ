package sockets;

import com.google.gson.JsonObject;

public interface SocketHandler {
    void send(JsonObject jsonObject, String targetHost, int targetPort, String fromEntity);
    void addHandler(String name, SocketInputHandler handler);
}
