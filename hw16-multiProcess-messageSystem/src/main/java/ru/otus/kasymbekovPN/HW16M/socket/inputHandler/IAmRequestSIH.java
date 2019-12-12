package ru.otus.kasymbekovPN.HW16M.socket.inputHandler;

import com.google.gson.JsonObject;
import json.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.MsClient;
import ru.otus.kasymbekovPN.HW16M.messageSystem.client.service.MsClientService;
import entity.Entity;
import message.MessageType;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

public class IAmRequestSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(IAmRequestSIH.class);

    //<
//    private static Map<String, Consumer<Args>> handlers = new HashMap<>();
//    static {
//        Map<String, Consumer<Args>> buffer = new HashMap<>();
//        buffer.put(Entity.UNKNOWN.getValue(), IAmRequestSIH::createUnknownMsClient);
//        buffer.put(Entity.DATABASE.getValue(), IAmRequestSIH::createDatabaseMsClient);
//        buffer.put(Entity.FRONTEND.getValue(), IAmRequestSIH::createFrontendMsClient);
//        handlers = Collections.unmodifiableMap(buffer);
//    }

    private final SocketHandler socketHandler;
    //<
    private final MessageSystem messageSystem;
    //<
    private final MsClientService msClientService;

    public IAmRequestSIH(SocketHandler socketHandler, MessageSystem messageSystem, MsClientService msClientService) {
        this.socketHandler = socketHandler;
        this.messageSystem = messageSystem;
        this.msClientService = msClientService;
    }

    //    public IAmRequestSIH(MessageSystem messageSystem, SocketHandler socketHandler, MsClientService msClientService) {
//        this.messageSystem = messageSystem;
//        this.socketHandler = socketHandler;
//        this.msClientService = msClientService;
//    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("IAmRequestSIH : {}", jsonObject);

        JsonObject from = jsonObject.get("from").getAsJsonObject();
        String url = JsonHelper.extractUrl(from);
        String host = from.get("host").getAsString();
        String entity = from.get("entity").getAsString();
        int port = from.get("port").getAsInt();

        String status;
        MsClient msClient = msClientService.get(url);
        if (msClient == null){
            if (msClientService.createClient(host, port, Entity.valueOf(entity), messageSystem)) {
                status = "Client '" + url + "' was add.";
            } else {
                status = "Client '" + url + "' wasn't add.";
            }
        } else {
            status = "The client '" + url + "' already exists";
        }
        logger.info("IAmRequestSIH : {}", status);

//        MsClient client = messageSystem.getClient(url);
//        Args args = new Args(url, messageSystem, socketHandler);
//        if (client == null){
//            handlers.get(entity).accept(args);
//            logger.info("IAmRequestSIH : {}", args.status);
//        } else {
//            args.status = "The client '" + url + "' already exists";
//            logger.warn("IAmRequestSIH : {}", args.status);
//        }

        JsonObject data = new JsonObject();
        data.addProperty("url", url);
        JsonObject respJsonObject = new JsonObject();
        respJsonObject.addProperty("type", MessageType.I_AM_RESPONSE.getValue());
        respJsonObject.add("data", data);
        respJsonObject.add("to", from);

        //< replace
//        respJsonObject.add("from", from);

        socketHandler.send(respJsonObject);

        //<
//        JsonObject from = jsonObject.get("from").getAsJsonObject();
//        String url = JsonHelper.extractUrl(from);
//        String entity = from.get("entity").getAsString();
//
//        String status;
//        MsClient client = messageSystem.getClient(url);
//        Args args = new Args(url, messageSystem, socketHandler);
//        if (client == null){
//            handlers.get(entity).accept(args);
//            logger.info("IAmRequestSIH : {}", args.status);
//        } else {
//            args.status = "The client '" + url + "' already exists";
//            logger.warn("IAmRequestSIH : {}", args.status);
//        }
//
//        JsonObject data = new JsonObject();
//        data.addProperty("url", url);
//        JsonObject respJsonObject = new JsonObject();
//        respJsonObject.addProperty("type", ReqRespType.I_AM_RESPONSE.getValue());
//        respJsonObject.add("data", data);
//        respJsonObject.add("to", from);
//
//        //< replace
//        respJsonObject.add("from", from);
//
//        socketHandler.send(respJsonObject);
    }

    //<
//    private static void createFrontendMsClient(Args args){
////        MsClientImpl msClient = MsClientImpl.newInstance(args.url, args.ms);
//        //<
//        MsClientImpl msClient = new MsClientImpl(args.url, args.ms);
//
//        msClient.addHandler(ReqRespType.WRONG_TYPE, new WrongMSMessageHandler());
//        msClient.addHandler(ReqRespType.AUTH_USER_RESPONSE, new CommonMSMessageHandler(args.socketHandler));
//        msClient.addHandler(ReqRespType.ADD_USER_RESPONSE, new CommonMSMessageHandler(args.socketHandler));
//        msClient.addHandler(ReqRespType.DEL_USER_RESPONSE, new CommonMSMessageHandler(args.socketHandler));
//
//        args.ms.addClient(msClient);
//
//        args.status = "Client '" + args.url + "' was add.";
//    }
//
//    private static void createDatabaseMsClient(Args args){
////        MsClientImpl msClient = MsClientImpl.newInstance(args.url, args.ms);
//        //<
//        MsClientImpl msClient = new MsClientImpl(args.url, args.ms);
//
//        msClient.addHandler(ReqRespType.WRONG_TYPE, new WrongMSMessageHandler());
//        msClient.addHandler(ReqRespType.AUTH_USER_REQUEST, new CommonMSMessageHandler(args.socketHandler));
//        msClient.addHandler(ReqRespType.ADD_USER_REQUEST, new CommonMSMessageHandler(args.socketHandler));
//        msClient.addHandler(ReqRespType.DEL_USER_REQUEST, new CommonMSMessageHandler(args.socketHandler));
//
//        args.ms.addClient(msClient);
//
//        args.status = "Client '" + args.url + "' was add.";
//    }
//
//    private static void createUnknownMsClient(Args args){
//        args.status = "The attempt of unknown entity client addition";
//    }
//
//    private class Args{
//        String status;
//        String url;
//        MessageSystem ms;
//        SocketHandler socketHandler;
//
//        Args(String url, MessageSystem ms, SocketHandler socketHandler) {
//            this.url = url;
//            this.ms = ms;
//            this.socketHandler = socketHandler;
//            this.status = "";
//        }
//    }
}
