package ru.otus.kasymbekovPN.HW16F.socket.sendingHandler;

import com.google.gson.JsonObject;
import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import entity.Entity;
import sockets.SocketSendingHandler;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class FESocketSendingHandler implements SocketSendingHandler {

    private static final Logger logger = LoggerFactory.getLogger(FESocketSendingHandler.class);

    private static final Entity selfEntity = Entity.FRONTEND;
    private static final Entity targetEntity = Entity.DATABASE;

    private String msHost;
    private String selfHost;
    private String targetHost;

    private int msPort;
    private int selfPort;
    private int targetPort;

    @Override
    public void init(List<String> hosts, List<Integer> ports) {
        this.selfHost = hosts.get(0);
        this.msHost = hosts.get(1);
        this.targetHost = hosts.get(2);

        this.selfPort = ports.get(0);
        this.msPort = ports.get(1);
        this.targetPort = ports.get(2);
    }

    @Override
    public void send(JsonObject jsonObject) {
        try(Socket clientSocket = new Socket(msHost, msPort)){
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            jsonObject.add("from", JsonHelper.makeUrl(selfHost, selfPort, selfEntity));
            jsonObject.add("to", JsonHelper.makeUrl(targetHost, targetPort, targetEntity));

            logger.info("FESocketSendingHandler send : {}", jsonObject);
            out.println(jsonObject);
        } catch (Exception ex){
            logger.error("FESocketSendingHandler Error : '{}:{}' is unreachable", msHost, msPort);
        }
    }
}
