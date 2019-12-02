package json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.ReqRespType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonCheckerImpl implements JsonChecker {

    private static final Logger logger = LoggerFactory.getLogger(JsonCheckerImpl.class);

    private static Map<String, JsonObject> standardJsonObjects = new HashMap<>();

//    private static Map<String, Consumer<JsonObject>> handlers = new HashMap<>();
    //<
    static {
//        handlers = Map.of(
//                ReqRespType.I_AM_REQUEST.getValue(), JsonCheckerImpl::handleIAmRequest,
//                ReqRespType.I_AM_RESPONSE.getValue(), JsonCheckerImpl::handleIAmResponse,
//                ReqRespType.WRONG_TYPE.getValue(), JsonCheckerImpl::handleWrongType,
//                ReqRespType.AUTH_USER_REQUEST.getValue(), JsonCheckerImpl::handleAuthUserRequest,
//                ReqRespType.ADD_USER_REQUEST.getValue(), JsonCheckerImpl::handleAddUserRequest,
//                ReqRespType.DEL_USER_REQUEST.getValue(), JsonCheckerImpl::handleDelUserRequest,
//                ReqRespType.AUTH_USER_RESPONSE.getValue(), JsonCheckerImpl::handleAuthUserResponse,
//                ReqRespType.ADD_USER_RESPONSE.getValue(), JsonCheckerImpl::handleAddUserResponse,
//                ReqRespType.DEL_USER_RESPONSE.getValue(), JsonCheckerImpl::handleDelUserResponse
//        );
    //<

        JsonObject ft = new JsonObject();
        ft.addProperty("host", "String");
        ft.addProperty("port", "Integer");
        ft.addProperty("entity", "String");

        JsonObject iAmReqStdJsonObject = new JsonObject();
        iAmReqStdJsonObject.addProperty("type", "String");
        iAmReqStdJsonObject.add("from", ft);

        JsonObject data = new JsonObject();
        data.addProperty("url", "String");
        JsonObject iAmRespJsonObject = new JsonObject();
        iAmRespJsonObject.addProperty("type", "String");
        iAmRespJsonObject.add("data", data);
        iAmRespJsonObject.add("from", ft);

        data = new JsonObject();
        data.addProperty("login", "String");
        data.addProperty("password", "String");
        JsonObject authUserReqJsonObject = new JsonObject();
        authUserReqJsonObject.addProperty("type", "String");
        authUserReqJsonObject.add("data", data);
        authUserReqJsonObject.add("from", ft);
        authUserReqJsonObject.add("to", ft);

        Map<String, JsonObject> tmp = new HashMap<>();
        tmp.put(ReqRespType.I_AM_REQUEST.getValue(), iAmReqStdJsonObject);
        tmp.put(ReqRespType.I_AM_RESPONSE.getValue(), iAmRespJsonObject);
        tmp.put(ReqRespType.AUTH_USER_REQUEST.getValue(), authUserReqJsonObject);

        standardJsonObjects = Collections.unmodifiableMap(tmp);
    }

    private JsonObject jsonObject;
    private Set<String> validTypes;

    @Override
    public JsonObject getJsonObject() {
        return jsonObject;
    }

    @Override
    public String getType() {
        return jsonObject.get("type").getAsString();
    }

    public JsonCheckerImpl(String line, Set<String> validTypes) {
        this.jsonObject = (JsonObject) new JsonParser().parse(line);
        this.validTypes = validTypes;
        parse();
    }

    private void parse(){
        if (jsonObject.has("type")){
            String type = jsonObject.get("type").getAsString();
            if (validTypes.contains(type)){
                StringBuilder errorDescription = new StringBuilder();
                String path = "";
                traverse(jsonObject, standardJsonObjects.get(type), errorDescription, path);

                if (!errorDescription.toString().equals("")){
                    errorDescription.append(" Original Type : ").append(type).append(";");
                    changeByError(jsonObject, errorDescription.toString());
                }

            } else {
                changeByError(jsonObject,"Invalid field 'type' : " + type);
            }
        } else {
            changeByError(jsonObject,"Field 'type' doesn't exist");
        }
    }

    private static void traverse(JsonObject jsonObject, JsonObject std, StringBuilder errorDescription, String path){
        Set<String> keys = std.keySet();
        for (String key : keys) {
            String currentPath = path + ":" + key;
            JsonElement stdElement = std.get(key);
            JsonElement element = null;
            if (jsonObject.has(key)){
                element = jsonObject.get(key);
            } else {
                errorDescription.append(" Field '").append(currentPath).append("' doesn't exist;");
            }

            if (stdElement.isJsonObject()){
                if (element != null){
                    if (element.isJsonObject()){
                        traverse(element.getAsJsonObject(), stdElement.getAsJsonObject(), errorDescription, currentPath);
                    } else {
                        errorDescription.append(" Field '").append(currentPath).append("' isn't object;");
                    }
                }
            } else if (stdElement.isJsonPrimitive()){
                if (element != null){
                    switch (stdElement.getAsString()){
                        case "String":
                            try{
                                element.getAsString();
                            } catch (NumberFormatException ex){
                                errorDescription.append(" Field '").append(currentPath).append("' isn't String;");
                            }
                            break;
                        case "Integer":
                            try {
                                element.getAsInt();
                            } catch (NumberFormatException ex){
                                errorDescription.append(" Field '").append(currentPath).append("' isn't Integer;");
                            }
                            break;
                        default:
                            errorDescription.append(" Field '").append(currentPath).append("' has unknown type;");
                            break;
                    }
                }
            }
        }
    }

    private static void changeByError(JsonObject jsonObject, String errorDescription){
        jsonObject.addProperty("type", ReqRespType.WRONG_TYPE.getValue());
        jsonObject.addProperty("errorDescription", errorDescription);
    }

    //<
//    private static void handleIAmRequest(JsonObject jsonObject){
//
//        logger.info("{}", jsonObject);
//
////        StringBuilder errorDescription = new StringBuilder();
////        if (!jsonObject.has("entity")){
////            errorDescription.append("Message doesn't contain field 'entity'; ");
////        }
////        if (jsonObject.has("from")){
////            JsonObject from = jsonObject.get("from").getAsJsonObject();
////            if (!from.has("host")){
////                errorDescription.append("Field 'from' doesn't contain filed 'host'; ");
////            }
////            if (!from.has("port")){
////                errorDescription.append("Field 'from' doesn't contain filed 'port'; ");
////            }
////        } else {
////            errorDescription.append("Message doesn't contain filed 'from'; ");
////        }
//
//        //<
////        if (jsonObject.has("to")){
////            JsonObject to = jsonObject.get("to").getAsJsonObject();
////            if (!to.has("host")){
////                errorDescription.append("Field 'to' doesn't contain filed 'host'; ");
////            }
////            if (!to.has("port")){
////                errorDescription.append("Field 'to' doesn't contain filed 'port'; ");
////            }
////        } else {
////            errorDescription.append("Message doesn't contain filed 'to'; ");
////        }
//
////        if (!errorDescription.toString().equals("")){
////            errorDescription.append("Original type '").append(jsonObject.get("type").getAsString()).append("';");
////            changeByError(jsonObject, errorDescription.toString());
////        }
//    }
//
//    private static void handleIAmResponse(JsonObject jsonObject){
//        logger.info("{}", jsonObject);
//    }
//
//    private static void handleWrongType(JsonObject jsonObject){
//        logger.info("{}", jsonObject);
//    }
//
//    private static void handleAuthUserRequest(JsonObject jsonObject) {
//        logger.info("{}", jsonObject);
//
////        StringBuilder errorDescription;
////
////        Set<String> keys = jsonObject.keySet();
////        for (String key : keys) {
////            logger.info("--- : {} : {} {}", key, jsonObject.get(key), jsonObject.get(key).getClass());
////        }
////
////        logger.info("{}", authUserRequestStandard);
////{"type":"authUserRequest","login":"admin","password":"qwerty","to":{"host":"localhost","port":8101,"entity":"frontend"},"from":{"host":"localhost","port":8081}}
//    }
//
//    private static void handleAddUserRequest(JsonObject jsonObject){
//
//    }
//
//    private static void handleDelUserRequest(JsonObject jsonObject){
//
//    }
//
//    private static void handleAuthUserResponse(JsonObject jsonObject) {
//
//    }
//
//    private static void handleAddUserResponse(JsonObject jsonObject){
//
//    }
//
//    private static void handleDelUserResponse(JsonObject jsonObject){
//
//    }
}
