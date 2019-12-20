package ru.otus.kasymbekovPN.HW16M.socket.inputHandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import json.JsonHelper;
import message.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.service.MsClientService;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

import java.util.Optional;

/**
 * Обработчик входящего request-сообщения <br><br>
 *
 * {@link #handle(JsonObject)} - проверяет наличие клиентов : приемника и источника, в случае успеха отправляет сообщение
 * в систему, иначе шлет сообщение с описание ошибкию <br><br>.
 */
public class CommonUserRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonUserRequestSIH.class);

    private final MsClientService msClientService;
    private final SocketHandler socketHandler;

    public CommonUserRequestSIH(MsClientService msClientService, SocketHandler socketHandler) {
        this.msClientService = msClientService;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        String type = jsonObject.get("type").getAsString();
        logger.info("CommonUserRequest type : {}, data : {}", type, jsonObject);

        String fromUrl = JsonHelper.extractUrl(jsonObject.get("from").getAsJsonObject());
        String toUrl = JsonHelper.extractUrl(jsonObject.get("to").getAsJsonObject());

        Optional<MsClient> optFromMsClient = msClientService.get(fromUrl);
        Optional<MsClient> optToMsClient = msClientService.get(toUrl);

        if (optFromMsClient.isPresent() && optToMsClient.isPresent()){
            String str = jsonObject.toString();
            MsClient fromMsClient = optFromMsClient.get();
            Message message = fromMsClient.produceMessage(toUrl, str, MessageType.valueOf(type));
            fromMsClient.sendMessage(message);
        } else {
            String status = "";
            if (!optFromMsClient.isPresent()){
                status += "Client '" + fromUrl + "' doesn't exist; ";
            }
            if (!optToMsClient.isPresent()){
                status += "Client '" + toUrl + "' doesn't exist; ";
            }

            JsonObject data = jsonObject.get("data").getAsJsonObject();
            data.addProperty("status", status);
            data.add("users", new JsonArray());

            JsonObject responseJsonObject = new JsonObject();
            responseJsonObject.addProperty("type", MessageType.getOpposite(MessageType.valueOf(type)).getValue());
            responseJsonObject.add("data", data);
            responseJsonObject.add("to", jsonObject.get("from").getAsJsonObject());

            socketHandler.send(responseJsonObject);
        }
    }
}
