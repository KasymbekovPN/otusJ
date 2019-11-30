package json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.ReqRespType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class JsonCheckerImpl implements JsonChecker {

    private static final Logger logger = LoggerFactory.getLogger(JsonCheckerImpl.class);

    private static Map<String, Consumer<JsonObject>> handlers = new HashMap<>();
    static {
        handlers = Map.of(
                ReqRespType.I_AM_REQUEST.getValue(), JsonCheckerImpl::handleIAmRequest,
                ReqRespType.I_AM_RESPONSE.getValue(), JsonCheckerImpl::handleIAmResponse,
                ReqRespType.WRONG_TYPE.getValue(), JsonCheckerImpl::handleWrongType,
                ReqRespType.AUTH_USER_REQUEST.getValue(), JsonCheckerImpl::handleAuthUserRequest,
                ReqRespType.ADD_USER_REQUEST.getValue(), JsonCheckerImpl::handleAddUserRequest,
                ReqRespType.DEL_USER_REQUEST.getValue(), JsonCheckerImpl::handleDelUserRequest,
                ReqRespType.AUTH_USER_RESPONSE.getValue(), JsonCheckerImpl::handleAuthUserResponse,
                ReqRespType.ADD_USER_RESPONSE.getValue(), JsonCheckerImpl::handleAddUserResponse,
                ReqRespType.DEL_USER_RESPONSE.getValue(), JsonCheckerImpl::handleDelUserResponse
        );
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
                handlers.get(type).accept(jsonObject);
            } else {
                changeByError(jsonObject,"Invalid field 'type' : " + type);
            }
        } else {
            changeByError(jsonObject,"Field 'type' doesn't exist");
        }
    }

    private static void changeByError(JsonObject jsonObject, String errorDescription){
        jsonObject.addProperty("type", ReqRespType.WRONG_TYPE.getValue());
        jsonObject.addProperty("errorDescription", errorDescription);
    }

    private static void handleIAmRequest(JsonObject jsonObject){
        StringBuilder errorDescription = new StringBuilder();
        if (!jsonObject.has("entity")){
            errorDescription.append("Message doesn't contain filed 'entity'; ");
        }
        if (jsonObject.has("from")){
            JsonObject from = jsonObject.get("from").getAsJsonObject();
            if (!from.has("host")){
                errorDescription.append("Field 'from' doesn't contain filed 'host'; ");
            }
            if (!from.has("port")){
                errorDescription.append("Field 'from' doesn't contain filed 'port'; ");
            }
        } else {
            errorDescription.append("Message doesn't contain filed 'from'; ");
        }
        if (jsonObject.has("to")){
            JsonObject to = jsonObject.get("to").getAsJsonObject();
            if (!to.has("host")){
                errorDescription.append("Field 'to' doesn't contain filed 'host'; ");
            }
            if (!to.has("port")){
                errorDescription.append("Field 'to' doesn't contain filed 'port'; ");
            }
        } else {
            errorDescription.append("Message doesn't contain filed 'to'; ");
        }

        if (!errorDescription.toString().equals("")){
            errorDescription.append("Original type '").append(jsonObject.get("type").getAsString()).append("';");
            changeByError(jsonObject, errorDescription.toString());
        }
    }

    private static void handleIAmResponse(JsonObject jsonObject){

    }

    private static void handleWrongType(JsonObject jsonObject){
    }

    private static void handleAuthUserRequest(JsonObject jsonObject) {

    }

    private static void handleAddUserRequest(JsonObject jsonObject){

    }

    private static void handleDelUserRequest(JsonObject jsonObject){

    }

    private static void handleAuthUserResponse(JsonObject jsonObject) {

    }

    private static void handleAddUserResponse(JsonObject jsonObject){

    }

    private static void handleDelUserResponse(JsonObject jsonObject){

    }
}
