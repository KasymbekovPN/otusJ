package ru.otus.kasymbekovPN.HW16.sockets;

import com.google.gson.JsonObject;

public interface SocketHandler {
    void send(JsonObject message, String host, int port);
}
