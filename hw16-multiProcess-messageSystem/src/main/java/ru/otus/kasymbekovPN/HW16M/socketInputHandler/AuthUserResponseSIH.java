package ru.otus.kasymbekovPN.HW16M.socketInputHandler;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
import sockets.SocketHandler;
import sockets.SocketInputHandler;

public class AuthUserResponseSIH implements SocketInputHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserResponseSIH.class);

    private final MessageSystem messageSystem;
    private final SocketHandler socketHandler;

    public AuthUserResponseSIH(MessageSystem messageSystem, SocketHandler socketHandler) {
        this.messageSystem = messageSystem;
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(JsonObject jsonObject) {
        logger.info("AuthUserResponseSIH : {}", jsonObject);


        //<
//        AuthUserResponseSIH : {
//        "type":"authUserResponse",
//        "to":{"host":"localhost","port":8081,"entity":"frontend"},
//        "from":{"host":"localhost","port":8101,"entity":"database"},
//        "data":{
//        "login":"admin",
//        "password":"qwerty",
//        "status":"admin",
//        "users":[{"id":1,"login":"admin","password":"qwerty","isAdmin":true}]}}
    }


    //<
//    package ru.otus.kasymbekovPN.HW16M.socketInputHandler;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import ru.otus.kasymbekovPN.HW16M.messageSystem.Message;
//import ru.otus.kasymbekovPN.HW16M.messageSystem.MessageSystem;
//import ru.otus.kasymbekovPN.HW16M.messageSystem.MsClient;
//import sockets.Entity;
//import sockets.ReqRespType;
//import sockets.SocketHandler;
//import sockets.SocketInputHandler;
//
//    public class AuthUserRequestSIH implements SocketInputHandler {
//
//        private static final Logger logger = LoggerFactory.getLogger(AuthUserRequestSIH.class);
//
//        private final MessageSystem messageSystem;
//        private final SocketHandler socketHandler;
//
//        public AuthUserRequestSIH(MessageSystem messageSystem, SocketHandler socketHandler) {
//            this.messageSystem = messageSystem;
//            this.socketHandler = socketHandler;
//        }
//
//        @Override
//        public void handle(JsonObject jsonObject) {
//            logger.info("AuthUserRequestSIH : " + jsonObject);
//
//            JsonObject data = jsonObject.get("data").getAsJsonObject();
//            JsonObject from = jsonObject.get("from").getAsJsonObject();
//
//            //< how check !!!
//            String fromHost = from.get("host").getAsString();
//            int fromPort = from.get("port").getAsInt();
//            String fromEntity = from.get("entity").getAsString();
//            String fromUrl = fromHost + ":" + String.valueOf(fromPort) + "/" + fromEntity;
//
//            JsonObject to = jsonObject.get("to").getAsJsonObject();
//            String toEntity = Entity.check(to.get("entity").getAsString());
//
//            String status = "";
//            if (!toEntity.equals(Entity.UNKNOWN.getValue())){
//                String toHost = to.get("host").getAsString();
//                Integer toPort = to.get("port").getAsInt();
//                String url = toHost + ":" + String.valueOf(toPort) + "/" + toEntity;
//                MsClient msClient = messageSystem.getClient(url);
//                if (msClient != null){
//                    String str = jsonObject.toString();
//                    Message message = msClient.produceMessage(fromUrl, str, ReqRespType.AUTH_USER_REQUEST);
//                    msClient.sendMessage(message);
//                } else {
//                    status = "Target '" + url + "' doesn't exist.";
//                }
//            } else {
//                status = "to:entity is unknown.";
//            }
//
//            if (!status.equals("")){
//                JsonArray users = new JsonArray();
//                data.addProperty("status", status);
//                data.add("users", users);
//
//                JsonObject resp = new JsonObject();
//                resp.addProperty("type", ReqRespType.AUTH_USER_RESPONSE.getValue());
//                resp.add("data", data);
//
//                String targetHost = from.get("host").getAsString();
//                int targetPort = from.get("port").getAsInt();
//                socketHandler.send(resp, targetHost, targetPort, Entity.MESSAGE_SYSTEM.getValue());
//            }
//        }
//    }


}
