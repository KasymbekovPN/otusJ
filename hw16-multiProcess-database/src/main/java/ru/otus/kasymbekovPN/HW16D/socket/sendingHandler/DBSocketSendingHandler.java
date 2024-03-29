package ru.otus.kasymbekovPN.HW16D.socket.sendingHandler;

import com.google.gson.JsonObject;
import entity.Entity;
import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketSendingHandler;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Обработчик отправки сообщения. <br><br>
 *
 * {@link DBSocketSendingHandler#send(JsonObject)} - отправка сообщения. Сообщение отправляется в систему сообщений
 * {@link #msHost}:{@link #msPort}; в сообщение добавляются поля "from", содержащие данные об источнике сообщении и
 * "to", содержащее данные о приемнике. <br>
 */
public class DBSocketSendingHandler implements SocketSendingHandler {

    private static final Logger logger = LoggerFactory.getLogger(DBSocketSendingHandler.class);

    private static final Entity selfEntity = Entity.DATABASE;
    private static final Entity targetEntity = Entity.FRONTEND;

    private final String msHost;
    private final String selfHost;
    private final String targetHost;

    private final int msPort;
    private final int selfPort;
    private final int targetPort;

    public DBSocketSendingHandler(String msHost, String targetHost, int msPort, int selfPort, int targetPort) throws UnknownHostException {
        this.msHost = msHost;
        this.selfHost = InetAddress.getLocalHost().getHostAddress();
        this.targetHost = targetHost;
        this.msPort = msPort;
        this.selfPort = selfPort;
        this.targetPort = targetPort;
    }

    @Override
    public void send(JsonObject jsonObject) {
        try(Socket clientSocket = new Socket(msHost, msPort)){
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            jsonObject.add("from", JsonHelper.makeUrl(selfHost, selfPort, selfEntity));
            jsonObject.add("to", JsonHelper.makeUrl(targetHost, targetPort, targetEntity));

            logger.info("DBSocketSendingHandler send : {}", jsonObject);
            out.println(jsonObject);
        } catch (Exception ex){
            logger.error("DBSocketSendingHandler Error : '{}:{}' is unreachable", msHost, msPort);
        }
    }
}
