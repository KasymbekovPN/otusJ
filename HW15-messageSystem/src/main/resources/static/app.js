let stompClient = null;

const setConnected = connected => {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected)
        $("#chatLine").show();
    else
        $("#chatLine").hide();
    $("#message").html("");
};

const connect = () => {
    stompClient = Stomp.over(new SockJS('/gs-guide-websocket'));
    stompClient.connect({}, frame => {
        setConnected(true);
        console.log(`Connected: ${frame}`);
        stompClient.subscribe('/topic/response', greeting =>
            showGreeting(JSON.parse(greeting.body).messageStr));
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

const showGreeting = messageStr =>
    $("#chatLine").append(`<tr><td>${messageStr}</td></tr>`);

$(() => {
    $("form").on('submit', event => event.preventDefault());
    $("#connect").click(connect);
    $("#disconnect").click(disconnect);
    $("#send").click(sendName);
});
