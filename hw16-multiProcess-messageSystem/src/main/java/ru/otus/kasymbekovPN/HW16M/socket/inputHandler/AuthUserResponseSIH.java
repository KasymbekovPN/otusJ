package ru.otus.kasymbekovPN.HW16M.socket.inputHandler;

import com.google.gson.JsonObject;
import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MsClient;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MsClientService;
import sockets.ReqRespType;
import sockets.SocketInputHandler;

public class AuthUserResponseSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserResponseSIH.class);

    private final MsClientService msClientService;
    //<
//    private final MessageSystem messageSystem;
//    private final SocketHandler socketHandler;

    public AuthUserResponseSIH(MsClientService msClientService) {
        this.msClientService = msClientService;
    }


    //<
//    public AuthUserResponseSIH(MessageSystem messageSystem, SocketHandler socketHandler) {
//        this.messageSystem = messageSystem;
//        this.socketHandler = socketHandler;
//    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AuthUserResponseSIH : {}", jsonObject);

        String fromUrl = JsonHelper.extractUrl(jsonObject.get("from").getAsJsonObject());
        String toUrl = JsonHelper.extractUrl(jsonObject.get("to").getAsJsonObject());

        MsClient fromClient = msClientService.get(fromUrl);
        MsClient toClient = msClientService.get(toUrl);

        if (fromClient != null && toClient != null) {
            String str = jsonObject.toString();
            Message message = fromClient.produceMessage(toUrl, str, ReqRespType.AUTH_USER_RESPONSE);
            fromClient.sendMessage(message);
        }
        else {
            logger.error("AuthUserResponseSIH : client not found.");
        }
    }
}
