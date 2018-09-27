<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>ChatBot SP Eventos</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
</head>
<body>
	<div class="center">
		<h2>Chabot SP Eventos</h2>
		<div id="textchat" class="pulse"></div>
		<form method="post">
			<input type="text" id="question" name="question" class="field"
				placeholder="Digite a sua pergunta" />
			<button id="sendQuestion">Enviar</button>
		</form>
	</div>
	<script type="text/javascript" src="js/chatbot.js"></script>
</body>
</html>