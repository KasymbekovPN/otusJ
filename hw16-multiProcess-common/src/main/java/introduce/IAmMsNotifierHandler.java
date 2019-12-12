package introduce;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import message.MessageType;
import sockets.SocketHandler;

public class IAmMsNotifierHandler implements MsNotifierHandler {

    private static final Logger logger = LoggerFactory.getLogger(IAmMsNotifierHandler.class);

    private final SocketHandler socketHandler;
    private final JsonObject message;

    public IAmMsNotifierHandler(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
        this.message = new JsonObject();
        this.message.addProperty("type", MessageType.I_AM_REQUEST.getValue());
    }

    @Override
    public void handle() {
        socketHandler.send(message);
    }
}
