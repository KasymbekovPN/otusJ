package ru.otus.kasymbekovPN.HW16M.socket.sendingHandler;

import com.google.gson.JsonObject;
import entity.Entity;
import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketSendingHandler;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * Обработчик отправки сообщения. <br><br>
 *
 * {@link MSSocketSendingHandler#send(JsonObject)} - отправка сообщения. <br>
 */
public class MSSocketSendingHandler implements SocketSendingHandler {

    private static final Logger logger = LoggerFactory.getLogger(MSSocketSendingHandler.class);
    private static final Entity SELF_ENTITY = Entity.MESSAGE_SYSTEM;

    private final String selfHost;
    private final int selfPort;

    public MSSocketSendingHandler(String selfHost, int selfPort) {
        this.selfHost = selfHost;
        this.selfPort = selfPort;
    }

    @Override
    public void send(JsonObject jsonObject) {
        JsonObject to = jsonObject.get("to").getAsJsonObject();
        String toHost = to.get("host").getAsString();
        int toPort = to.get("port").getAsInt();

        if (!jsonObject.has("from")){
            jsonObject.add("from", JsonHelper.makeUrl(selfHost, selfPort, SELF_ENTITY));
        }

        try(Socket clientSocket = new Socket(toHost, toPort)){
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            logger.info("MSSocketSendingHandler send : {}", jsonObject);
            out.println(jsonObject);
        } catch (Exception ex){
            logger.error("MSSocketSendingHandler Error : '{}:{}' is unreachable", toHost, toPort);
        }
    }
}
