package ru.otus.kasymbekovPN.HW16M.socketInputHandler;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import sockets.SocketInputHandler;

public class AuthUserRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserRequestSIH.class);

    private final MessageSystem messageSystem;

    public AuthUserRequestSIH(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AuthUserRequestSIH : " + jsonObject);

//        JsonObject data = jsonObject.get("data").get
//        JsonObject to = jsonObject.get("to").getAsJsonObject();



//        AuthUserRequestSIH :
//        {"type":"authUserRequest",
//        "data":{"login":"admin","password":"qwerty"},
//        "to":{"host":"localhost","port":8101,"entity":"database"},
//        "from":{"host":"localhost","port":8081,"entity":"frontend"}}

    }
}
