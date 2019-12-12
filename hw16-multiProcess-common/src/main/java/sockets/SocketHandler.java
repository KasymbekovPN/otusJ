package sockets;

import com.google.gson.JsonObject;
import entity.Entity;

import java.util.List;

public interface SocketHandler {
    void send(JsonObject jsonObject);
    void addHandler(String name, SocketInputHandler handler);
    void init(Entity entity, List<String> hosts, List<Integer> ports);
}
