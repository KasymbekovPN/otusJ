package sockets;

import com.google.gson.JsonObject;

public interface SocketHandler {
    void send(JsonObject jsonObject, String host, int port);
    void addHandler(String name, SocketInputHandler handler);
}
