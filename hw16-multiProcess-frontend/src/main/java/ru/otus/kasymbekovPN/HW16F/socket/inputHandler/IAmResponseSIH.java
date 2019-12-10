package ru.otus.kasymbekovPN.HW16F.socket.inputHandler;

import com.google.gson.JsonObject;
import introduce.Registrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketInputHandler;

public class IAmResponseSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(IAmResponseSIH.class);

    private final Registrar registrar;

    public IAmResponseSIH(Registrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("IAmResponseSIH : {}", jsonObject);
        registrar.stop();
    }
}
