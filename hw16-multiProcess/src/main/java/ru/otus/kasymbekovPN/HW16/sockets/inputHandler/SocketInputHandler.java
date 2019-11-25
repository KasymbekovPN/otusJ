package ru.otus.kasymbekovPN.HW16.sockets.inputHandler;

import com.google.gson.JsonObject;

public interface SocketInputHandler {
    void handle(JsonObject jsonObject);
}
