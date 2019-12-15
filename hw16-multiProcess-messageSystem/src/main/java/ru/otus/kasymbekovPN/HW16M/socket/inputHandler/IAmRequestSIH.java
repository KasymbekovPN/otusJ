package ru.otus.kasymbekovPN.HW16M.socket.inputHandler;

import com.google.gson.JsonObject;
import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.service.MsClientService;
import entity.Entity;
import message.MessageType;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

/**
 * Обработчик сообщений, регистрирующих программы-клиенты. <br><br>
 *
 * {@link #handle(JsonObject)} - в обработчике создаются клиенты {@link MsClient} системы обмена сообщениями. <br><br>
 */
public class IAmRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(IAmRequestSIH.class);

    private final SocketHandler socketHandler;
    private final MessageSystem messageSystem;
    private final MsClientService msClientService;

    public IAmRequestSIH(SocketHandler socketHandler, MessageSystem messageSystem, MsClientService msClientService) {
        this.socketHandler = socketHandler;
        this.messageSystem = messageSystem;
        this.msClientService = msClientService;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("IAmRequestSIH : {}", jsonObject);

        JsonObject from = jsonObject.get("from").getAsJsonObject();
        String url = JsonHelper.extractUrl(from);
        String host = from.get("host").getAsString();
        String entity = from.get("entity").getAsString();
        int port = from.get("port").getAsInt();

        String status;
        MsClient msClient = msClientService.get(url);
        if (msClient == null){
            if (msClientService.createClient(host, port, Entity.valueOf(entity), messageSystem)) {
                status = "Client '" + url + "' was add.";
            } else {
                status = "Client '" + url + "' wasn't add.";
            }
        } else {
            status = "The client '" + url + "' already exists";
        }
        logger.info("IAmRequestSIH : {}", status);

        JsonObject data = new JsonObject();
        data.addProperty("url", url);
        JsonObject respJsonObject = new JsonObject();
        respJsonObject.addProperty("type", MessageType.I_AM_RESPONSE.getValue());
        respJsonObject.add("data", data);
        respJsonObject.add("to", from);

        socketHandler.send(respJsonObject);
    }
}
