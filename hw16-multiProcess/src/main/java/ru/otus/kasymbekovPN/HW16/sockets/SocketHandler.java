package ru.otus.kasymbekovPN.HW16.sockets;

import com.google.gson.JsonObject;
import ru.otus.kasymbekovPN.HW16.sockets.inputHandler.SocketInputHandler;

public interface SocketHandler {
    void send(JsonObject jsonObject, String host, int port);
    void addHandler(String name, SocketInputHandler handler);
}
