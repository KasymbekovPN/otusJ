package ru.otus.kasymbekovPN.HW16.sockets;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketHandlerImpl implements SocketHandler {

    private static Logger logger = LoggerFactory.getLogger(SocketHandler.class);
    private static Set<Integer> usedPorts = new HashSet<>();

    private final int port;

    private final ExecutorService inProcessor = Executors.newSingleThreadExecutor(
            runnable -> {
                Thread thread = new Thread(runnable);
                thread.setName("in-processor-thread");
                return thread;
            }
    );

    public static SocketHandlerImpl newInstance(int port){
        if (!usedPorts.contains(port)){
            usedPorts.add(port);
            return new SocketHandlerImpl(port);
        } else {
            //< ??? null
            logger.error("Not unique port");
            return null;
        }
    }

    private SocketHandlerImpl(int port) {
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
            logger.error("error", ex);
        }
    }

    private void handleClientSocket(Socket clientSocket){
        try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String input = in.readLine();
            //<<
            logger.info("from server : {}", input);
            //<<
        } catch (Exception ex){
            logger.error("error", ex);
        }
    }

    @Override
    public void send(JsonObject message, String host, int port) {
        try(Socket clientSocket = new Socket(host, port)){
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            //<
            logger.info("send to server : {}", message);
            //<

            out.println(message);
        } catch (Exception ex){
            logger.error("error", ex);
        }
    }
}
