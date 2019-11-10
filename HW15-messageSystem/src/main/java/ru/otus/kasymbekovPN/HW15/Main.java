package ru.otus.kasymbekovPN.HW15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.MessageSystemImpl;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.MessageType;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.MsClient;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.MsClientImpl;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.db.DBService;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.db.DBServiceImpl;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.db.handlers.GetUserDataRequestHandler;

@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    public static void main(String[] args) {

//        MessageSystem messageSystem = new MessageSystemImpl();
        //<
        MessageSystemImpl messageSystem = MessageSystemImpl.getInstance();

        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
        DBService dbService = new DBServiceImpl();
        databaseMsClient.addHandler(MessageType.USER_DATA, new GetUserDataRequestHandler(dbService));
        messageSystem.addClient(databaseMsClient);

//        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
//        FrontendService frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
//        frontendMsClient.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler(frontendService));
//        messageSystem.addClient(frontendMsClient);
//
//        frontendService.getUserData(1, data -> logger.info("got data : {}", data));

        //<
//        System.out.println("-----------------------------");
        //<

        SpringApplication.run(Main.class, args);

        //<<<
//        System.out.println("------------------------------------");

        //<
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            messageSystem.dispose();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        logger.info("done");


    }
}
