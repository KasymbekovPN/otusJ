package ru.otus.kasymbekovPN.HW16M.socket.sendingHandler;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.SocketSendingHandler;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class MSSocketSendingHandler implements SocketSendingHandler {

    private static final Logger logger = LoggerFactory.getLogger(MSSocketSendingHandler.class);

    @Override
    public void init(List<String> hosts, List<Integer> ports) {
    }

    @Override
    public void send(JsonObject jsonObject) {
        JsonObject to = jsonObject.get("to").getAsJsonObject();
        String toHost = to.get("host").getAsString();
        int toPort = to.get("port").getAsInt();

        try(Socket clientSocket = new Socket(toHost, toPort)){
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            logger.info("MSSocketSendingHandler send : {}", jsonObject);
            out.println(jsonObject);
        } catch (Exception ex){
            logger.error("MSSocketSendingHandler Error; ", ex);
        }
    }
}
