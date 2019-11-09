package ru.otus.kasymbekovPN.HW15.L29;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW15.L29.db.DBServiceImpl_;
import ru.otus.kasymbekovPN.HW15.L29.db.DBService_;
import ru.otus.kasymbekovPN.HW15.L29.db.handlers.GetUserDataRequestHandler_;
import ru.otus.kasymbekovPN.HW15.L29.front.FrontendServiceImpl_;
import ru.otus.kasymbekovPN.HW15.L29.front.FrontendService_;
import ru.otus.kasymbekovPN.HW15.L29.front.handlers.GetUserDataResponseHandler_;
import ru.otus.kasymbekovPN.HW15.L29.messageSystem.*;

public class MSMain_ {

    private static final Logger logger = LoggerFactory.getLogger(MSMain_.class);

    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    //<
//    public static void main(String[] args) throws InterruptedException {
//        MessageSystem_ messageSystem = new MessageSystemImpl_();
//
//        MsClient_ databaseMsClient = new MsClientImpl_(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
//        DBService_ dbService = new DBServiceImpl_();
//        databaseMsClient.addHandler(MessageType_.USER_DATA, new GetUserDataRequestHandler_(dbService));
//        messageSystem.addClient(databaseMsClient);
//
//
//        MsClient_ frontendMsClient = new MsClientImpl_(FRONTEND_SERVICE_CLIENT_NAME, messageSystem);
//        FrontendService_ frontendService = new FrontendServiceImpl_(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
//        frontendMsClient.addHandler(MessageType_.USER_DATA, new GetUserDataResponseHandler_(frontendService));
//        messageSystem.addClient(frontendMsClient);
//
//        frontendService.getUserData(1, data -> logger.info("got data:{}", data));
//
//        Thread.sleep(100);
//        messageSystem.dispose();
//        logger.info("done");
//    }
}
