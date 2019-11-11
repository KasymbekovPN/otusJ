
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
        stompClient.subscribe(
            "/topic/adminAuthResponse",
            rawUserData => handleAdminUserResponse(rawUserData)
        );
        //<
//        stompClient.subscribe('/topic/response', greeting =>{
//            showGreeting(greeting)
//        });
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

const handleAdminUserResponse = rawUserData => {

//    console.log("? :" + rawUserData);

    userData = JSON.parse(rawUserData.body);

    const userInfoNode = document.getElementById("userInformation");
    userInfoNode.innerHTML = "";

    for (var i in userData){
        id = userData[i].id;
        login = userData[i].login;
        password = userData[i].password;
        admin = userData[i].admin
        $("#userInformation").append(
            `<tr><td>${id}</td><td>${login}</td><td>${password}</td><td>${admin}</td></tr>`
        );
    }

//    console.log("??? : " + userData);

    //      const myNode = document.getElementById("userInformation");
    //      myNode.innerHTML = '';
    //    } else {
    //        $("#userInformation").append(`<tr><td>${messageStr}</td></tr>`);
    //    }


//    console.log("raw user data : " + userData);
//    console.log("type : " + typeof(userData));
//    console.log("body : " + userData.body);
//
//    console.log("len : " + userData.length);
//
//    hUserData = JSON.parse(userData.body)
//
//
//    for(var k in hUserData)
//    {
//        console.log(k + ")");
//        console.log("login : " + hUserData[k].login);
////        console.log("?? : " + k + ", ?? : " + userData[k]);
//    }

//    for(var k in result) {
//       console.log(k, result[k]);
//    }

};
//<
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
