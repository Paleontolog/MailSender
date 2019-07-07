var url = "http://localhost:8089/api/addresses";

function createTr(obj) {
	var td = document.createElement('td');
	td.innerHTML = obj;
	return td;
}

function Hello() {

	var client = new XMLHttpRequest();
	client.open("get", url, true);
	client.onreadystatechange = function() {
		if (client.readyState === 4 && client.status === 200) {
			var list = client.response;
			list = JSON.parse(list);
			var el = document.getElementById('addressess list');
			var len = el.rows.length;

			for (var t = len - 1; t > 0; t--) {
				el.deleteRow(t);
			}

			for (var a = 0; a < list.length; a += 1) {
				var tr = document.createElement('tr');
				tr.appendChild(createTr(list[a].id));
				tr.appendChild(createTr(list[a].email));
				document.getElementById('addressess list').appendChild(tr);
			}
		}
	};
	client.send();
}

function add() {

	var textE_MAIL = document.getElementById('inputE-MAIL').value;
	if (textE_MAIL !== "") {

		var body = {
			id: 5,
			email: textE_MAIL
		};

		var client = new XMLHttpRequest();
		client.open("PUT", url, true);
		client.setRequestHeader('Content-Type', 'application/json');
		client.onreadystatechange  = function() {
			if (client.readyState === 4) {
				if (client.status === 200) {
					alert(client.responseText);
				}
			}
		};
			client.send(JSON.stringify(body));
		}
		else
			alert("Рукожоп");
}

function changeOnId() {
	var textID = document.getElementById('inputID').value;

	var textE_MAIL = document.getElementById('inputE-MAIL').value;

	if (textID !== "" && textE_MAIL !== "") {

		var body = {
			id: textID,
			email: textE_MAIL
		};

		var client = new XMLHttpRequest();

		client.open("POST", url, true);
		client.setRequestHeader('Content-Type', 'application/json');

		client.onreadystatechange  = function() {
			if (client.readyState === 4) {
				if (client.status === 200) {
					alert(client.responseText);
				}
			}
		};
		client.send(JSON.stringify(body));
	}
	else
		alert("Рукожоп");
}
