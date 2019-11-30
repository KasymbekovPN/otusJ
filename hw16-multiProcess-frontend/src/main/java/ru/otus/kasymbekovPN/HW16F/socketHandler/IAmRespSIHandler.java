package ru.otus.kasymbekovPN.HW16F.socketHandler;

import com.google.gson.JsonObject;
import introduce.Registrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketInputHandler;

public class IAmRespSIHandler implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(IAmRespSIHandler.class);

    private final Registrar registrar;

    public IAmRespSIHandler(Registrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        //<
        logger.info("IAmRespSIHandler : {}", jsonObject);

        registrar.stop();
    }
}
