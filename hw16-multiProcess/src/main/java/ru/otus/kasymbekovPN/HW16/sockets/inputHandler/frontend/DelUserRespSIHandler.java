package ru.otus.kasymbekovPN.HW16.sockets.inputHandler.frontend;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16.frontend.clientMessageController.GuiMessageTransmitter;
import ru.otus.kasymbekovPN.HW16.sockets.inputHandler.SocketInputHandler;

public class DelUserRespSIHandler implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(DelUserRespSIHandler.class);

    private GuiMessageTransmitter guiMessageTransmitter;

    public DelUserRespSIHandler(GuiMessageTransmitter guiMessageTransmitter) {
        this.guiMessageTransmitter = guiMessageTransmitter;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        //<
        logger.info("DelUserRespSIHandler");
        //<
    }
}
