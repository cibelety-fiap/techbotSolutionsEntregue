window.onload = function() {
	callBot("");
}

var btnSend = document.querySelector("#sendQuestion");

btnSend.addEventListener("click", function(event) {
	event.preventDefault();
	
	var question = document.querySelector("#question");
	createMessage(question.value, "user");
	
	callBot(question.value); 
	
	question.value = "";
});

function createMessage(message, type, pulse = false) {
	var chat = document.querySelector("#textchat");
	var div = createDiv(message, type);
	chat.appendChild(div);
	scrollDivDown(chat);
	if (pulse) makePulse(chat);
}

function makePulse(element) {
	element.classList.add("pulse-animation");
	setTimeout(function() {
		element.classList.remove("pulse-animation");
	}, 1200);
}

function callBot(message) {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "chat", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.addEventListener("load", function() {

		if (xhr.status == 200) {
			var respostas = JSON.parse(xhr.responseText);
			respostas.forEach(function (resposta) { 
				createMessage(resposta, "bot", true);
			});
		} else {
			console.log(xhr.status);
			console.log(xhr.responseText);
		}
	});

	data = "question=" + message;
	xhr.send(data);
}

function createDiv(text, type) {
	var div = document.createElement("div");
	var img = createImage(type);
	var p = createText(text);
	div.classList.add("chat" + type);
	div.appendChild(img);
	div.appendChild(p);
	return div;
}

function createImage(type) {
	var img = document.createElement("img");
	img.setAttribute("src", "img/" + type + ".png");
	img.classList.add("icon" + type);
	return img;
}

function createText(text) {
	var p = document.createElement("p");
//	p.textContent = text;
	p.innerHTML = text; //alterei para aceitar codigo html
	return p;
}

function scrollDivDown(div) {
	for (var i = 0; i < div.offsetHeight; i++) {
		div.scrollTop++;
	}
}