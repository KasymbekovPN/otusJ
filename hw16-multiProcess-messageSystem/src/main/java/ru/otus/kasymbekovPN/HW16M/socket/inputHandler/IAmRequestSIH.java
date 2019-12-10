package ru.otus.kasymbekovPN.HW16M.socket.inputHandler;

import com.google.gson.JsonObject;
import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MsClient;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MsClientImpl;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.CommonMSMessageHandler;
import ru.otus.kasymbekovPN.HW16M.messageSystem.handler.WrongMSMessageHandler;
import sockets.Entity;
import sockets.ReqRespType;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class IAmRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(IAmRequestSIH.class);

    private static Map<String, Consumer<Args>> handlers = new HashMap<>();
    static {
        Map<String, Consumer<Args>> buffer = new HashMap<>();
        buffer.put(Entity.UNKNOWN.getValue(), IAmRequestSIH::createUnknownMsClient);
        buffer.put(Entity.DATABASE.getValue(), IAmRequestSIH::createDatabaseMsClient);
        buffer.put(Entity.FRONTEND.getValue(), IAmRequestSIH::createFrontendMsClient);
        handlers = Collections.unmodifiableMap(buffer);
    }

    private final SocketHandler socketHandler;
    private final MessageSystem messageSystem;

    public IAmRequestSIH(MessageSystem messageSystem, SocketHandler socketHandler) {
        this.messageSystem = messageSystem;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("IAmRequestSIH : {}", jsonObject);

        JsonObject from = jsonObject.get("from").getAsJsonObject();
        String url = JsonHelper.extractUrl(from);
        String entity = from.get("entity").getAsString();

        String status;
        MsClient client = messageSystem.getClient(url);
        Args args = new Args(url, messageSystem, socketHandler);
        if (client == null){
            handlers.get(entity).accept(args);
            logger.info("IAmRequestSIH : {}", args.status);
        } else {
            args.status = "The client '" + url + "' already exists";
            logger.warn("IAmRequestSIH : {}", args.status);
        }

        JsonObject data = new JsonObject();
        data.addProperty("url", url);
        JsonObject respJsonObject = new JsonObject();
        respJsonObject.addProperty("type", ReqRespType.I_AM_RESPONSE.getValue());
        respJsonObject.add("data", data);
        respJsonObject.add("to", from);

        //< replace
        respJsonObject.add("from", from);

        socketHandler.send(respJsonObject);
    }

    private static void createFrontendMsClient(Args args){
        MsClientImpl msClient = MsClientImpl.newInstance(args.url, args.ms);

        msClient.addHandler(ReqRespType.WRONG_TYPE, new WrongMSMessageHandler());
        msClient.addHandler(ReqRespType.AUTH_USER_RESPONSE, new CommonMSMessageHandler(args.socketHandler));
        msClient.addHandler(ReqRespType.ADD_USER_RESPONSE, new CommonMSMessageHandler(args.socketHandler));
        msClient.addHandler(ReqRespType.DEL_USER_RESPONSE, new CommonMSMessageHandler(args.socketHandler));

        args.ms.addClient(msClient);

        args.status = "Client '" + args.url + "' was add.";
    }

    private static void createDatabaseMsClient(Args args){
        MsClientImpl msClient = MsClientImpl.newInstance(args.url, args.ms);

        msClient.addHandler(ReqRespType.WRONG_TYPE, new WrongMSMessageHandler());
        msClient.addHandler(ReqRespType.AUTH_USER_REQUEST, new CommonMSMessageHandler(args.socketHandler));
        msClient.addHandler(ReqRespType.ADD_USER_REQUEST, new CommonMSMessageHandler(args.socketHandler));
        msClient.addHandler(ReqRespType.DEL_USER_REQUEST, new CommonMSMessageHandler(args.socketHandler));

        args.ms.addClient(msClient);

        args.status = "Client '" + args.url + "' was add.";
    }

    private static void createUnknownMsClient(Args args){
        args.status = "The attempt of unknown entity client addition";
    }

    private class Args{
        String status;
        String url;
        MessageSystem ms;
        SocketHandler socketHandler;

        Args(String url, MessageSystem ms, SocketHandler socketHandler) {
            this.url = url;
            this.ms = ms;
            this.socketHandler = socketHandler;
            this.status = "";
        }
    }
}
