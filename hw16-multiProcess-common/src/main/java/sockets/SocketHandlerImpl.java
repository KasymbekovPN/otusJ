package sockets;

import com.google.gson.JsonObject;
import json.JsonCheckerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketHandlerImpl implements SocketHandler {

    private static Logger logger = LoggerFactory.getLogger(SocketHandler.class);
//    private static Set<Integer> usedPorts = new HashSet<>();
    //<
    private static Set<String> usedUrls = new HashSet<>();

    private final String host;
    private final int port;
    private final Map<String, SocketInputHandler> handlers = new ConcurrentHashMap<>();

    private final ExecutorService inProcessor = Executors.newSingleThreadExecutor(
            runnable -> {
                Thread thread = new Thread(runnable);
                thread.setName("in-processor-thread");
                return thread;
            }
    );

    public static SocketHandlerImpl newInstance(String host, int port){
        String url = host + String.valueOf(port);
        if (!usedUrls.contains(url)){
            usedUrls.add(url);
            return new SocketHandlerImpl(host, port);
        } else {
            //< ??? null
            logger.error("Not equal URL");
            return null;
        }
        //<
//        if (!usedPorts.contains(port)){
//            usedPorts.add(port);
//            return new SocketHandlerImpl(port);
//        } else {
//            //< ??? null
//            logger.error("Not unique port");
//            return null;
//        }
    }



    private SocketHandlerImpl(String host, int port) {
        this.host = host;
        this.port = port;
        inProcessor.submit(this::handleInProcessor);
    }

    private void handleInProcessor(){
        try(ServerSocket serverSocket = new ServerSocket(port)){
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
//            String input = in.readLine();
//            //<<
//            logger.info("from server : {}", input);
//            JsonObject jo = (JsonObject) new JsonParser().parse(input);
//            logger.info("as json : {}", jo);
//            //<<

//            JsonObject jsonObject = (JsonObject) new JsonParser().parse(in);
//            String type = ReqRespType.WRONG_TYPE.getValue();
//            if (jsonObject.has("type")){
//                String predictType = jsonObject.get("type").getAsString();
//                if (handlers.containsKey(predictType)){
//                    type = predictType;
//                }
//            }
            //<
            JsonCheckerImpl checker = new JsonCheckerImpl(in.readLine(), handlers.keySet());
            handlers.get(checker.getType()).handle(checker.getJsonObject());

        } catch (Exception ex){
            //<
            logger.error("error 222", ex);
        }
    }

    @Override
    public void send(JsonObject jsonObject, String targetHost, int targetPort) {
        try(Socket clientSocket = new Socket(targetHost, targetPort)){
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            //<
            logger.info("send to server : {}", jsonObject);
            //<

            JsonObject from = new JsonObject();
            from.addProperty("host", this.host);
            from.addProperty("port", this.port);

            //<
//            JsonObject to = new JsonObject();
//            to.addProperty("host", host);
//            to.addProperty("port", port);

            jsonObject.add("from", from);
            //<
//            jsonObject.add("to", to);

            out.println(jsonObject);
        } catch (Exception ex){
            //<
            logger.error("error 111", ex);
        }
    }

    @Override
    public void addHandler(String name, SocketInputHandler handler) {
        handlers.put(name, handler);
    }
}
