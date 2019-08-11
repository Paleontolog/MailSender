var message = {
    id: null,
    subject: "",
    email: ""
};

function fillForm() {
    message.id = null;
    message.subject = $("#messageSubject").text();
    message.email = $("#messageText").text();
}

function onloadPage() {
    var url = "http://localhost:8080/api/addresses";
    var client = new XMLHttpRequest();
    client.open("GET", url, true);
    client.onreadystatechange = function () {
        if (client.readyState === 4) {
            if (client.status === 200) {
                var addresseesList = {
                    addresseesList: JSON.parse(client.response)
                };
                var addr = $("#addr").html();
                var res = Mustache.to_html(addr, addresseesList);
                $("#addressees").append(res);
            }
        }
    };
    client.send();
}

function saveMessage() {
    var url = "http://localhost:8080/api/messages";
    var client = new XMLHttpRequest();

    var method;
    if (message.id === null) {
        method = "PUT";
    } else {
        method = "POST";
    }

    client.open(method, url, true);
    client.onreadystatechange = function () {
        if (client.readyState === 4) {
            if (client.response === 200) {
            }
        }
    };

    client.send(JSON.stringify(message));
}


function getMessage(id) {
    var url = "http://localhost:8080/api/messages/" + id;
    var client = new XMLHttpRequest();
    client.open("GET", url, true);
    client.onreadystatechange = function () {
        if (client.readyState === 4) {
            if (client.response === 200) {
                message = JSON.parse(client.response);
            }
        }
    };
    client.send();
}

function formLoad() {
    var url = "http://localhost:8080/api/messages";
    var client = new XMLHttpRequest();

    var message = {
        id: null,
        subject: $("#messageSubject").text(),
        email: $("#messageText").text()
    };

    client.open("PUT", url, true);
    client.onreadystatechange = function () {
        if (client.readyState === 4) {
            if (client.response === 200) {
            }
        }
    };

    client.send(JSON.stringify(message));
}
