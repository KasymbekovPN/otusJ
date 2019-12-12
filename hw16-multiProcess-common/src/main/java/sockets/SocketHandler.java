package sockets;

import com.google.gson.JsonObject;
import entity.Entity;

import java.util.List;

public interface SocketHandler {
//    void sendD(JsonObject jsonObject);
//    void sendF(JsonObject jsonObject);
//    void sendM(JsonObject jsonObject);
//    void send(JsonObject jsonObject, String targetHost, int targetPort, String fromEntity);
    //<
    void send(JsonObject jsonObject);
    void addHandler(String name, SocketInputHandler handler);
    void init(Entity entity, List<String> hosts, List<Integer> ports);
}
