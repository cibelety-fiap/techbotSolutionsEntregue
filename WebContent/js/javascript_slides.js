var slideIndex = 0;
showSlides();

function showSlides() {
	var i;
	var slides = document.getElementsByClassName("slides");
	var bolinhas = document.getElementsByClassName("bolinha_slides");
	for (i = 0; i < slides.length; i++) {
		slides[i].style.display = "none";
	}
	slideIndex++;
	if (slideIndex > slides.length) {
		slideIndex = 1
	}
	for (i = 0; i < bolinhas.length; i++) {
		bolinhas[i].className = bolinhas[i].className.replace(" bolinha_ativa", "");
	}
	slides[slideIndex - 1].style.display = "block";
	bolinhas[slideIndex - 1].className += " bolinha_ativa";
	setTimeout(showSlides, 2000); // Tempo para alteracao do slide
}

function myFunction() {
	var x = document.getElementById("myTopnav");
	if (x.className === "topnav") {
		x.className += " responsive";
	} else {
		x.className = "topnav";
	}
}

function mudarCor(x) {

	var imagem = x.getAttribute("src");
	if (imagem == "img/coracao_vazio.png") {
		x.setAttribute("src", "img/coracao_cheio.png")
	} else {

		x.setAttribute("src", "img/coracao_vazio.png")
	}
}

function goBack() {
	window.history.back();
}

function Login() {
	var done = 0;
	var usuario = document.getElementsByName('usuario')[0].value;
	usuario = usuario.toLowerCase();
	var senha = document.getElementsByName('senha')[0].value;
	senha = senha.toLowerCase();
	if (usuario == 'admin' && senha == 'admin') {
		window.location = 'tela_adm.jsp';
		done = 1;
	}

	if (usuario == 'user' && senha == 'user') {
		window.location = 'tela_usuario.jsp';
		done = 1;
	}

	if (done == 0) {
		alert('Dados incorretos, tente novamente');
	}
}

function validar(form) {
	var email = form.email.value;
	var emailx = form.emailNovamente.value;
	var senha = form.pass.value;
	var senhax = form.pass2.value;
	var aceito = form.aceito.checked;
	var nome = form.nome.value;
	var rg = form.rg.value;
	var cpf = form.cpf.value;


	if (email == "" || email.indexOf('@') == -1) {
		alert('EMAIL INVALIDO');
		form.email.focus();
		return false;
	}
	if (emailx != email) {
		alert('OS EMAILS ESTÃƒO DIFERENTES');
		form.emailNovamente.focus();
		return false;
	}
	if (nome == "") {
		alert('DIGITE SEU NOME');
		form.nome.focus();
		return false;
	}
	if (cpf == "") {
		alert('DIGITE SEU CPF');
		form.cpf.focus();
		return false;
	}
	if (rg == "") {
		alert('DIGITE SEU RG');
		form.rg.focus();
		return false;
	}
	if (senha == "") {
		alert('DIGITE UMA SENHA')
		form.pass.focus();
		return false;
	}
	if (senhax != senha) {
		alert('AS SENHAS ESTAO DIFERENTES');
		form.pass2.focus();
		return false;
	}
	if (aceito == "checked") {
		alert('MARQUE QUE ACEITA OS TERMO DE USO')
		form.aceito.focus();
		return false;
	}
	return true;
}

