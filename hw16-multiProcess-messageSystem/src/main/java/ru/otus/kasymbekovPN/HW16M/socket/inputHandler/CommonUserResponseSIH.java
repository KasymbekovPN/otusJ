package ru.otus.kasymbekovPN.HW16M.socket.inputHandler;

import com.google.gson.JsonObject;
import json.JsonHelper;
import message.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.service.MsClientService;
import sockets.SocketInputHandler;

/**
 * Обработчик входящего request-сообщения <br><br>
 *
 * {@link #handle(JsonObject)} - проверяет наличие клиентов : приемника и источника, в случае успеха отправляет сообщение
 * в систему<br><br>.
 */
public class CommonUserResponseSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommonUserResponseSIH.class);

    private final MsClientService msClientService;

    public CommonUserResponseSIH(MsClientService msClientService) {
        this.msClientService = msClientService;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        String type = jsonObject.get("type").getAsString();
        logger.info("CommonUserResponseSIH type : {}, data : {}", type, jsonObject);

        String fromUrl = JsonHelper.extractUrl(jsonObject.get("from").getAsJsonObject());
        String toUrl = JsonHelper.extractUrl(jsonObject.get("to").getAsJsonObject());

        MsClient fromClient = msClientService.get(fromUrl);
        MsClient toClient = msClientService.get(toUrl);

        if (fromClient != null && toClient != null) {
            String str = jsonObject.toString();
            Message message = fromClient.produceMessage(toUrl, str, MessageType.valueOf(type));
            fromClient.sendMessage(message);
        }  else {
            logger.error("DelUserResponseSIH : client not found.");
        }
    }
}
