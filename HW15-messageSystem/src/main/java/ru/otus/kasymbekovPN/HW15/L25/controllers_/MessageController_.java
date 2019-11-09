package ru.otus.kasymbekovPN.HW15.L25.controllers_;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

//< need del
@Controller
public class MessageController_ {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MessageController_.class);

//    @MessageMapping("/message")
//    @SendTo("/topic/response")
//    public Message_ gerMessage(Message_ message){
//        log.info("got message : \"{}\"", message);
//
//        //<
//        log.info("1 : {}", message.getMessageStr());
//        log.info("2 : {}", HtmlUtils.htmlEscape(message.getMessageStr()));
//        final Message_ message_ = new Message_(HtmlUtils.htmlEscape(message.getMessageStr()));
//        log.info("3 : {}", message_);
//        //<
//        return new Message_(HtmlUtils.htmlEscape(message.getMessageStr()));
//    }
    //<

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


//    @MessageMapping("/message")
//    public void getMessage(Message_ message){
//        log.info("got message : \"{}\"", message);
//
//        sendMessage(message);
//    }

//    @SendTo("/topic/response")
//    public void sendMessage(Message_ message){
//        log.info("sendMessage");
//        this.simpMessagingTemplate.convertAndSend(
//                "/topic/response",
//                new Message_(message.getMessageStr())
//        );
//    }
//    @MessageMapping("/message")
//    @SendTo("/topic/response")
//    public Message_ gerMessage(Message_ message){
//        log.info("got message : \"{}\"", message);
//
//        //<
//        log.info("1 : {}", message.getMessageStr());
//        log.info("2 : {}", HtmlUtils.htmlEscape(message.getMessageStr()));
//        final Message_ message_ = new Message_(HtmlUtils.htmlEscape(message.getMessageStr()));
//        log.info("3 : {}", message_);
//        //<
//        return new Message_(HtmlUtils.htmlEscape(message.getMessageStr()));
//    }

//    @MessageMapping("/authorization")
//    public void getUser(UserMassage userMassage){
//        log.info("user message : {}", userMassage);
//    }



//    @MessageMapping("/authorization")
//    @SendTo("/topic/response")
//    public Message_ getget(Message_ message_){
//        log.info("got message : \"{}\"", message_);
//
//        return new Message_(HtmlUtils.htmlEscape(message_.getMessageStr()));
//    }
//
//    @MessageMapping("/message1")
//    @SendTo("/topic/response")
//    public Message_ getMessage1(Message_ message_){
//        log.info("got message1 : \"{}\"", message_);
//        return new Message_("!!! HELLO !!!");
//    }
}
