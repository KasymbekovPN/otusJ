package sockets;

import com.google.gson.JsonObject;

public interface SocketInputHandler {
    void handle(JsonObject jsonObject);
}
