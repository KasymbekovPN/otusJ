package ru.otus.kasymbekovPN.HW15.L25.controllers_;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TimeController_ {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TimeController_.class);

    @GetMapping("/getTime")
    public Date getTime(){
        log.info("got time response");
        return new Date();
    }
}
