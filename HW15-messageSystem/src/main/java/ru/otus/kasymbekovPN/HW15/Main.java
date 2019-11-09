package ru.otus.kasymbekovPN.HW15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.*;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.db.DBServiceImpl_;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.db.DBService_;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.db.handlers.GetUserDataRequestHandler_;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.front.FrontendServiceImpl_;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.front.FrontendService_;
import ru.otus.kasymbekovPN.HW15.messageSystem.server.front.handlers.GetUserDataResponseHandler_;

@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    public static void main(String[] args) {


//        package ru.otus.kasymbekovPN.HW15.L29;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//        public class MSMain_ {
//
//            private static final Logger logger = LoggerFactory.getLogger(MSMain_.class);
//
//            private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
//            private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";
//
//            //<
////    public static void main(String[] args) throws InterruptedException {

        MessageSystem_ messageSystem = new MessageSystemImpl_();

        MsClient_ databaseMsClient = new MsClientImpl_(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
        DBService_ dbService_ = new DBServiceImpl_();
        databaseMsClient.addHandler(MessageType_.USER_DATA, new GetUserDataRequestHandler_(dbService_));
        messageSystem.addClient(databaseMsClient);

        MsClient_ frontendMsClient = new MsClientImpl_(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
        FrontendService_ frontendService = new FrontendServiceImpl_(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
        frontendMsClient.addHandler(MessageType_.USER_DATA, new GetUserDataResponseHandler_(frontendService));
        messageSystem.addClient(frontendMsClient);

        frontendService.getUserData(1, data -> logger.info("got data : {}", data));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            messageSystem.dispose();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("done");

        //<
        System.out.println("111111111111111111111");

        SpringApplication.run(Main.class, args);

        //<
        System.out.println("22222222222222222222222222");
    }
}
