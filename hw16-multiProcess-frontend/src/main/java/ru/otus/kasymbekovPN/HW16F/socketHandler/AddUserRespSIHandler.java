package ru.otus.kasymbekovPN.HW16F.socketHandler;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16F.messageController.GuiMessageTransmitter;
import sockets.SocketInputHandler;

public class AddUserRespSIHandler implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AddUserRespSIHandler.class);

    private GuiMessageTransmitter guiMessageTransmitter;

    public AddUserRespSIHandler(GuiMessageTransmitter guiMessageTransmitter) {
        this.guiMessageTransmitter = guiMessageTransmitter;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AddUserRespSIHandler : {}", jsonObject);
    }
}
