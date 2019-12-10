package sockets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import json.JsonChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketHandlerImpl implements SocketHandler {

    private static Logger logger = LoggerFactory.getLogger(SocketHandler.class);
//    private static Set<Integer> usedPorts = new HashSet<>();
    //<
//    private static Set<String> usedUrls = new HashSet<>();

    //<
//    private final String host;
//    private final int port;

    private Entity entity;
    private String selfHost;
    private String targetHost;
    private String msHost;
    private int selfPort;
    private int targetPort;
    private int msPort;
    private boolean isValidUrlData;

    private final Map<String, SocketInputHandler> handlers = new ConcurrentHashMap<>();
    private final JsonChecker jsonChecker;
    private final SocketSendingHandler socketSendingHandler;

    private final ExecutorService inProcessor = Executors.newSingleThreadExecutor(
            runnable -> {
                Thread thread = new Thread(runnable);
                thread.setName("in-processor-thread");
                return thread;
            }
    );

    //<
//    public static SocketHandlerImpl newInstance(String host, int port, JsonChecker jsonChecker){
//        String url = host + String.valueOf(port);
//        if (!usedUrls.contains(url)){
//            usedUrls.add(url);
//            return new SocketHandlerImpl(host, port, jsonChecker);
//        } else {
//            //< ??? null
//            logger.error("Not equal URL");
//            return null;
//        }
//        //<
////        if (!usedPorts.contains(port)){
////            usedPorts.add(port);
////            return new SocketHandlerImpl(port);
////        } else {
////            //< ??? null
////            logger.error("Not unique port");
////            return null;
////        }
//    }

    public SocketHandlerImpl(JsonChecker jsonChecker, SocketSendingHandler socketSendingHandler) {
        this.jsonChecker = jsonChecker;
        this.socketSendingHandler = socketSendingHandler;
        this.isValidUrlData = false;
    }


    //<
//    private SocketHandlerImpl(String host, int port, JsonChecker jsonChecker) {
//
//        this.isValidUrlData = false;
//
//        this.host = host;
//        this.port = port;
//        this.jsonChecker = jsonChecker;
//
//        //<
////        inProcessor.submit(this::handleInProcessor);
//    }

    private void handleInProcessor(){
        try(ServerSocket serverSocket = new ServerSocket(selfPort)){
            while(!Thread.currentThread().isInterrupted()){
                logger.info("Waiting for client connection");
                try(Socket clientSocket = serverSocket.accept()){
                    handleClientSocket(clientSocket);
                }
            }
        } catch (Exception ex){
            //<
            logger.error("error -------------", ex);
        }
    }

    private void handleClientSocket(Socket clientSocket){
        try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            jsonChecker.setJsonObject(
                    (JsonObject) new JsonParser().parse(in.readLine()),
                    handlers.keySet()
            );
            handlers.get(jsonChecker.getType()).handle(jsonChecker.getJsonObject());

        } catch (Exception ex){
            //<
            logger.error("error 222", ex);
        }
    }

    @Override
    public void send(JsonObject jsonObject) {
        if (isValidUrlData)
        {
            socketSendingHandler.send(jsonObject);
            //<
//            //< вынести функционал send в handler класса
//            String toHost = entity.equals(Entity.MESSAGE_SYSTEM)
//                    ? jsonObject.get("to").getAsJsonObject().get("host").getAsString()
//                    : msHost;
//            int toPort = entity.equals(Entity.MESSAGE_SYSTEM)
//                    ? jsonObject.get("to").getAsJsonObject().get("port").getAsInt()
//                    : msPort;
//
//            try(Socket clientSocket = new Socket(toHost, toPort)){
//                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//                //< вынести
//                if (entity.equals(Entity.FRONTEND) || entity.equals(Entity.DATABASE))
//                {
//                    Entity targetEntity = entity.equals(Entity.FRONTEND) ? Entity.DATABASE : Entity.FRONTEND;
//                    jsonObject.add("from", JsonHelper.makeUrl(selfHost, selfPort, entity));
//                    jsonObject.add("to", JsonHelper.makeUrl(targetHost, targetPort, targetEntity));
//                }
//
//                //<
//                logger.info("send to server : {}", jsonObject);
//                //<
//                out.println(jsonObject);
//            } catch (Exception ex){
//                logger.error("error", ex);
//            }
        }
    }

    @Override
    public void addHandler(String name, SocketInputHandler handler) {
        handlers.put(name, handler);
    }

    //< check args size
    @Override
    public void init(Entity entity, List<String> hosts, List<Integer> ports) {
        this.entity = entity;
        this.isValidUrlData = true;

        this.selfHost = hosts.get(0);
        this.msHost = hosts.get(1);
        this.targetHost = hosts.get(2);

        this.selfPort = ports.get(0);
        this.msPort = ports.get(1);
        this.targetPort = ports.get(2);

        this.socketSendingHandler.init(hosts, ports);

        this.inProcessor.submit(this::handleInProcessor);
    }
}
