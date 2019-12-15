package sockets;

import entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, проверяющий агрументы командной строки и в случае успешной проверки применяющий
 * их к {@link SocketHandler} <br><br>
 *
 * {@link SocketHandlerArgApplierImpl#apply(String...)} - метод проверяющий с учетом сущности ({@link Entity}) и
 * применяющий аргументы командной строки к имплементации {@link SocketHandler}<br>
 */
public class SocketHandlerArgApplierImpl implements SocketHandlerArgApplier{

    private static final Logger logger = LoggerFactory.getLogger(SocketHandlerArgApplierImpl.class);

    private final Entity entity;
    private final SocketHandler socketHandler;

    public SocketHandlerArgApplierImpl(Entity entity, SocketHandler socketHandler) {
        this.entity = entity;
        this.socketHandler = socketHandler;
    }

    @Override
    public void apply(String... args) throws Exception {

        List<String> hosts = new ArrayList<>();
        List<Integer> ports = new ArrayList<>();

        String msg = "";
        if (entity.equals(Entity.MESSAGE_SYSTEM)){
            if (args.length == 2){
                hosts.add(args[0]);
                hosts.add("");
                hosts.add("");
                ports.add(Integer.valueOf(args[1]));
                ports.add(0);
                ports.add(0);
            } else {
                msg = "Wrong arguments number";
            }
        } else if (entity.equals(Entity.DATABASE) || entity.equals(Entity.FRONTEND)){
            if ((args.length == 6) || args.length == 7){
                for(int i = 0; i < 6; ++i){
                    if (i % 2 == 0){
                        hosts.add(args[i]);
                    } else {
                        ports.add(Integer.valueOf(args[i]));
                    }
                }
            } else {
                msg = "Wrong arguments number";
            }
        } else {
            msg = "Invalid entity type : " + entity.getValue();
        }

        if (msg.equals("")){
            socketHandler.init(entity, hosts, ports);
        } else {
            throw new Exception(msg);
        }
    }
}
