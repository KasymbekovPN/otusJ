
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

    const statusLine = document.getElementById("statusLine");
    statusLine.innerHTML = "<hr><p>User Information was load.</p><hr>";

    const userDataTableContainer = document.getElementById("userDataTableContainer");
    userDataTableContainer.innerHTML = "<table id='conversation' class='table table-striped'><thead><tr><th>User Information</th></tr></thead><tbody id='userInformation'><tr><td>ID</td><td>Login</td><td>Password</td><td>Is Admin</td></tr></tbody></table>";

    userData = JSON.parse(rawUserData.body);

    const userInfoNode = document.getElementById("userInformation");

    for (var i in userData){
        id = userData[i].id;
        login = userData[i].login;
        password = userData[i].password;
        admin = userData[i].admin
        $("#userInformation").append(
            `<tr><td>${id}</td><td>${login}</td><td>${password}</td><td>${admin}</td></tr>`
        );
    }

    const addUser = document.getElementById("addUser");
    addUser.innerHTML = "<hr>"
        + "<h5>User Addition</h5>"
        + "<label for='addLogin'>Login</label>"
        + "<input id='addLogin' type'text' class='form-control'>"
        + "<label for='addPassword'>Password</label>"
        + "<input id='addPassword' type'text' class='form-control'>"
        + "<button id='addUserButton' class='btn btn-default' type='submit'>Add</button";

    const delUser = document.getElementById("delUser");
    delUser.innerHTML = "<hr>"
        + "<h5>User Deleting</h5>"
        + "<label for='delLogin'>Login</label>"
        + "<input id='delLogin' type'text' class='form-control'>"
        + "<button id='delUserButton' class='btn btn-default' type='submit'>Del</button";

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
