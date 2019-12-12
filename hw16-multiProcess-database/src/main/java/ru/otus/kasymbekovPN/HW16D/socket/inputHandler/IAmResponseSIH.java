package ru.otus.kasymbekovPN.HW16D.socket.inputHandler;

import com.google.gson.JsonObject;
import introduce.MsNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketInputHandler;

public class IAmResponseSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(IAmResponseSIH.class);

    private final MsNotifier msNotifier;

    public IAmResponseSIH(MsNotifier msNotifier) {
        this.msNotifier = msNotifier;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("IAmResponseSIH : {}", jsonObject);
        msNotifier.stop();
    }
}
