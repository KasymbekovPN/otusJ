package introduce;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sockets.Entity;
import sockets.ReqRespType;
import sockets.SocketHandler;

//<  Как быть с хостом и портом ???
public class SendIAmToMSHandler implements RegistrarHandler {

    private static final Logger logger = LoggerFactory.getLogger(SendIAmToMSHandler.class);
    //<
    private static final String TO_HOST = "localhost";
    private static final int TO_PORT = 8091;

    private final SocketHandler socketHandler;

    //< ??? rename
    private final Entity entity;

    private JsonObject message;

    public SendIAmToMSHandler(SocketHandler socketHandler, Entity entity) {
        this.socketHandler = socketHandler;
        this.entity = entity;
        this.message = new JsonObject();
        this.message.addProperty("type", ReqRespType.I_AM_REQUEST.getValue());
        //<
//        this.message.addProperty("entity", entity.getValue());
    }

    @Override
    public void handle() {
//        socketHandler.send(message, TO_HOST, TO_PORT, entity.getValue());
        //<
        socketHandler.send(message);
    }
}
