
//< renaming entity inner file

let stompClient = null;

const setConnected = connected => {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected)
        $("#userInformation").show();
    else
        $("#userInformation").hide();
    $("#message").html("");
};

const connect = () => {
    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
    stompClient.connect({}, frame => {
        setConnected(true);
        console.log(`Connected: ${frame}`);
        stompClient.subscribe('/topic/response', greeting =>{
            showGreeting(greeting)
        });
    });
};

const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
};

const sendName = () => stompClient.send(
    "/app/message",
    {},
    JSON.stringify({'messageStr': $("#message").val()}));

const authorization = () => stompClient.send(
    "/app/authorization",
    {},
    JSON.stringify(
        {
            'login' : $("#login").val(),
            'password' : $('#password').val()
        }
    )
);

//const showGreeting = messageStr => {
const showGreeting = greeting => {

    console.log('+++ ' + greeting + ' +++');
//    console.log('+++ ' + greeting.body + ' +++');
//
//    messageStr = JSON.parse(greeting.body).messageStr;
//
//    console.log('+++ ' + messageStr + ' +++');
//
//    if (messageStr == "clear"){
//      const myNode = document.getElementById("userInformation");
//      myNode.innerHTML = '';
//    } else {
//        $("#userInformation").append(`<tr><td>${messageStr}</td></tr>`);
//    }
};
//<
//const showGreeting = messageStr =>
//    $("#chatLine").append(`<tr><td>${messageStr}</td></tr>`);

$(() => {
    $("form").on('submit', event => event.preventDefault());
    $("#connect").click(connect);
    $("#disconnect").click(disconnect);
    $("#send").click(sendName);
    $("#authorization").click(authorization);
});
