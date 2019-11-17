package ru.otus.kasymbekovPN.HW16.sockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ClientImpl implements Client {

    private static Logger logger = LoggerFactory.getLogger(ClientImpl.class);
    private static Set<String> urls = new HashSet<>();

    private final String host;
    private final int port;

    public static ClientImpl newInstance(String host, int port){
        String url = host + String.valueOf(port);
        if (!urls.contains(url)){
            urls.add(url);
            return new ClientImpl(host, port);
        } else {
            //< ??? null
            logger.error("Not unique url");
            return  null;
        }
    }

    private ClientImpl(String host, int port) {
        this.host = host;
        this.port = port;
    }

    //< rename with 'send' ???
    @Override
    public void start() {

        sleep(10);

        try(Socket clientSocket = new Socket(host, port)){
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            for (int i = 0; i < 3; i++){
                logger.info("sending to server");
                out.println("testData : " + i);
                String resp = in.readLine();
                logger.info("server response : {}", resp);
                sleep(3);
            }

            logger.info("stop communication");
            out.println("stop");
        } catch (Exception ex){
            logger.error("error", ex);
        }
    }
//                try (Socket clientSocket = new Socket(HOST, PORT)) {
//                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//                    for (int idx = 0; idx < 3; idx++) {
//                        logger.info("sending to server");
//                        out.println("testData:" + idx);
//                        String resp = in.readLine();
//                        logger.info("server response: {}", resp);
//                        sleep();
//                    }
//
//                    logger.info("stop communication");
//                    out.println("stop");
//                }
//
//            } catch (Exception ex) {
//                logger.error("error", ex);
//            }
//        }
//

    //< temp
    private static void sleep(int sec){
        try{
            Thread.sleep(TimeUnit.SECONDS.toMillis(sec));
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
    //<
//        private static void sleep() {
//            try {
//                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//    }


}
