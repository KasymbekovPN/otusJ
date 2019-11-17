package ru.otus.kasymbekovPN.HW16.sockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerImpl implements Server {

    private static Logger logger = LoggerFactory.getLogger(ServerImpl.class);
    private static Set<Integer> ports = new HashSet<>();

    private final int port;

    public static ServerImpl newInstance(int port){
        if (!ports.contains(port)){
            ports.add(port);
            return new ServerImpl(port);
        } else {
            //< ??? null
            logger.error("Not unique port");
            return null;
        }
    }

    private ServerImpl(int port) {
        this.port = port;
    }

    @Override
    public void start() {
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
    //<
//        private void go() {
//            //DatagramSocket - UDP
//            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//                while (!Thread.currentThread().isInterrupted()) {
//                    logger.info("waiting for client connection");
//                    try (Socket clientSocket = serverSocket.accept()) {
//                        clientHandler(clientSocket);
//                    }
//                }
//            } catch (Exception ex) {
//                logger.error("error", ex);
//            }
//        }
//

    private void handleClientSocket(Socket clientSocket){
        try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String input = null;
            while(!"stop".equals(input)){
                input = in.readLine();
                if (input != null) {
                    logger.info("from client : {}", input);
                    out.println("echo : " + input);
                }
            }
        } catch (Exception ex){
            logger.error("error", ex);
        }
    }
    //<
//        private void clientHandler(Socket clientSocket) {
//            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
//            ) {
//                String input = null;
//                while (!"stop".equals(input)) {
//                    input = in.readLine();
//                    if (input != null) {
//                        logger.info("from client: {} ", input);
//                        out.println("echo:" + input);
//                    }
//                }
//            } catch (Exception ex) {
//                logger.error("error", ex);
//            }
//        }
//
//
//    }


}
