package ru.otus.kasymbekovPN.HW16.sockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW16.common.Message;
import ru.otus.kasymbekovPN.HW16.common.Serializers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SocketHandlerImpl implements SocketHandler {

    private static Logger logger = LoggerFactory.getLogger(SocketHandler.class);
    private static Set<Integer> usedPorts = new HashSet<>();

    private final int port;

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
        start();
    }

    //< in ???
    @Override
    public void send(Message message) {

        String toHost = message.getToHost();
        int toPort = message.getToPort();
        try(Socket clientSocket = new Socket(toHost, toPort)){
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            logger.info("send to server : {}:{}", toHost, toPort);
            byte[] data = Serializers.serialize(message);
            out.println(Arrays.toString(data));
            //<
//            for (int i = 0; i < 3; i++){
//                logger.info("sending to server");
//                out.println("testData : " + i);
//                String resp = in.readLine();
//                logger.info("server response : {}", resp);
//                sleep(3);
//            }
//            logger.info("stop communication");
//            out.println("stop");
        } catch (Exception ex){
            logger.error("error", ex);
        }
    }

    //< temp
    private static void sleep(int sec){
        try{
            Thread.sleep(TimeUnit.SECONDS.toMillis(sec));
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    private void start(){
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

    //< out ???
    private void handleClientSocket(Socket clientSocket){
        try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String input = null;
            while(!"stop".equals(input)){
                input = in.readLine();
                if (input != null) {
                    logger.info("from client : {}", input);
                    //<
//                    out.println("echo : " + input);
                }
            }
        } catch (Exception ex){
            logger.error("error", ex);
        }
    }

}
