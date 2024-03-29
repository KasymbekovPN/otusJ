package ru.otus.kasymbekovPN.HW16D.socket.inputHandler;

import com.google.gson.JsonObject;
import introduce.Notifier;
import message.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketInputHandler;

/**
 * Обработчик входящего сообщения типа {@link MessageType#I_AM_RESPONSE} <br><br>
 *
 * {@link #handle(JsonObject)} - при получении ответа на запрос {@link MessageType#I_AM_REQUEST} останавливает периодическое
 * уведомление системы сообщений о своем запуске.
 */
public class IAmResponseSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(IAmResponseSIH.class);

    private final Notifier notifier;

    public IAmResponseSIH(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("IAmResponseSIH : {}", jsonObject);
        notifier.stop();
    }
}
