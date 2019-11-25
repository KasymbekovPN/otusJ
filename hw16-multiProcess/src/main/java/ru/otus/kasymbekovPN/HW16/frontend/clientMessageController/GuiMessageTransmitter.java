package ru.otus.kasymbekovPN.HW16.frontend.clientMessageController;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

//@Controller
@Service
//<
//@Component
@RequiredArgsConstructor
public class GuiMessageTransmitter {

//    @Autowired
    //<
    private final SimpMessagingTemplate simpMessagingTemplate;

    //< del !!!
//    @PostConstruct
//    public void init(){
//        System.out.println("333333333333333333333333333333");
//    }

    private void handleAuthUserResponse(OnlineUserPackage data){
        simpMessagingTemplate.convertAndSend(
                "/topic/authResponse",
                data
        );
    }

    private void handleAddUserResponse(OnlineUserPackage data){
        simpMessagingTemplate.convertAndSend(
                "/topic/addUserResponse",
                data
        );
    }

    private void handleDelUserResponse(OnlineUserPackage data){
        simpMessagingTemplate.convertAndSend(
                "/topic/delUserResponse",
                data
        );
    }
}
