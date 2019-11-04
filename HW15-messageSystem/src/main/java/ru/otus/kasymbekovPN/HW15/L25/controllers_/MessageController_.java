package ru.otus.kasymbekovPN.HW15.L25.controllers_;

import org.slf4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ru.otus.kasymbekovPN.HW15.L25.domain_.Message_;

@Controller
public class MessageController_ {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MessageController_.class);

    @MessageMapping("/message")
    @SendTo("/topic/response")
    public Message_ gerMessage(Message_ message){
        log.info("got message : \"{}\"", message);
        return new Message_(HtmlUtils.htmlEscape(message.getMessageStr()));
    }
}
