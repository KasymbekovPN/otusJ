package sockets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Entity;
import json.JsonChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketHandlerImpl implements SocketHandler {

    private static Logger logger = LoggerFactory.getLogger(SocketHandler.class);

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

    private int selfPort;
    private boolean isValidUrlData;

    public SocketHandlerImpl(JsonChecker jsonChecker, SocketSendingHandler socketSendingHandler) {
        this.jsonChecker = jsonChecker;
        this.socketSendingHandler = socketSendingHandler;
        this.isValidUrlData = false;
    }

    private void handleInProcessor(){
        try(ServerSocket serverSocket = new ServerSocket(selfPort)){
            while(!Thread.currentThread().isInterrupted()){
                logger.info("SocketHandlerImpl : Waiting for client connection");
                try(Socket clientSocket = serverSocket.accept()){
                    handleClientSocket(clientSocket);
                }
            }
        } catch (Exception ex){
            logger.error("SocketHandlerImpl::handleInProcessor : Error", ex);
        }
    }

    private void handleClientSocket(Socket clientSocket){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            jsonChecker.setJsonObject(
                    (JsonObject) new JsonParser().parse(in.readLine()),
                    handlers.keySet()
            );
            handlers.get(jsonChecker.getType()).handle(jsonChecker.getJsonObject());

        } catch (Exception ex){
            logger.error("SocketHandlerImpl::handleClientSocket : Error", ex);
        }
    }

    @Override
    public void send(JsonObject jsonObject) {
        if (isValidUrlData) {
            socketSendingHandler.send(jsonObject);
        }
    }

    @Override
    public void addHandler(String name, SocketInputHandler handler) {
        handlers.put(name, handler);
    }

    @Override
    public void init(Entity entity, List<String> hosts, List<Integer> ports) {
        this.isValidUrlData = true;
        this.selfPort = ports.get(0);
        this.socketSendingHandler.init(hosts, ports);
        this.inProcessor.submit(this::handleInProcessor);
    }
}
