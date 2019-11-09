let stompClient = null

const authorization = () => {
    console.log("HHEELLOO");

    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
    stompClient.connect({}, frame => {
        setConnected(true);
        console.log(`Connected: ${frame}`);
        stompClient.subscribe('/topic/response', greeting =>
            showGreeting(JSON.parse(greeting.body).messageStr));
    });

    stompClient.send(
        "/app/authorization",
        {},
        JSON.stringify({'messageStr': $("#login").val()})
    );

    //    stompClient.send(
    //        "/app/message",
    //        {},
    //        JSON.stringify({'messageStr': $("#message").val()})
    //    );

};

$(() => {
    $('#authorization').click(authorization)
});

//$(() => {
//    $("form").on('submit', event => event.preventDefault());
//    $("#connect").click(connect);
//    $("#disconnect").click(disconnect);
//    $("#send").click(sendName);
//    $("#send1").click(sendName1);
//});

//authorization

//const sendName = () => {
//    console.log("+++++++++++++ : " + JSON.stringify({'messageStr': $("#message").val()}));
//    stompClient.send(
//        "/app/message",
//        {},
//        JSON.stringify({'messageStr': $("#message").val()})
//    );
//};

//<
//let stompClient = null;
//
//const setConnected = connected => {
//    $("#connect").prop("disabled", connected);
//    $("#disconnect").prop("disabled", !connected);
//    if (connected)
//        $("#chatLine").show();
//    else
//        $("#chatLine").hide();
//    $("#message").html("");
//};
//
//const connect = () => {
//    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
//    stompClient.connect({}, frame => {
//        setConnected(true);
//        console.log(`Connected: ${frame}`);
//        stompClient.subscribe('/topic/response', greeting =>
//            showGreeting(JSON.parse(greeting.body).messageStr));
//    });
//};
//
//const disconnect = () => {
//    if (stompClient !== null) {
//        stompClient.disconnect();
//    }
//    setConnected(false);
//    console.log("Disconnected");
//};

//const sendName = () => stompClient.send(
//    "/app/message",
//    {},
//    JSON.stringify({'messageStr': $("#message").val()}));
//<
//const sendName = () => {
//    console.log("+++++++++++++ : " + JSON.stringify({'messageStr': $("#message").val()}));
//    stompClient.send(
//        "/app/message",
//        {},
//        JSON.stringify({'messageStr': $("#message").val()})
//    );
//};
//
//const sendName1 = () => stompClient.send(
//    "/app/message1",
//    {},
//    JSON.stringify({'messageStr': $("#message").val()})
//);
//
//const showGreeting = messageStr =>
//    $("#chatLine").append(`<tr><td>${messageStr}</td></tr>`);
//
//$(() => {
//    $("form").on('submit', event => event.preventDefault());
//    $("#connect").click(connect);
//    $("#disconnect").click(disconnect);
//    $("#send").click(sendName);
//    $("#send1").click(sendName1);
//});
