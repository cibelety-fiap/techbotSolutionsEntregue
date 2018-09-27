<%@ include file="../imports/cabecaDentroPasta.jsp" %>
<%

	int codigoEvento = Integer.parseInt(request.getParameter("codigoEvento"));

%>
<nav>
	<div class="menu_lateral">
		<button onclick="goBack()" class="menu_lateral">Voltar</button>
	</div>
</nav>
<div class="menu_central">

	<h1>Nova Realiza&ccedil;&atilde;o de Evento</h1>
		<br><br>
	
	<form action="../RealizacaoEventoServlet" method="GET">
		<input type="hidden" name="comando" value="adicionar" /> 
		<input type="hidden" name="codigoEvento" value="<%=codigoEvento%>" />
		<input type="hidden" name="codigoLocal" value="0" />
		<label>Data de in&iacute;cio:</label>
		<br>
		<input id="dataInicio" type="text" name="dataInicio" value="">
		<br><br>
		<label>Hora de in&iacute;cio:</label>
		<br>
		<input id="horaInicio" type="text" name="horaInicio" value="">
		<br><br>
		<label>Data de t&eacute;rmino:</label>
		<br>
		<input id="dataTermino" type="text" name="dataTermino" value="">
		<br><br>
		<label>Hora de t&eacute;rmino:</label>
		<br>
		<input id="horaTermino" type="text" name="horaTermino" value=""><br><br>
		<br /><hr><br />
		<label>Nome local:</label><br />
		<input type="text" name="nomeLocal" value="" maxlength="100"><br /><br />
		
		<label>Endere&ccedil;o:</label><br />

		<textarea rows="4" name="enderecoLocal"></textarea><br /><br /><br />
		<button class="tamanho_botao">Enviar</button>
		<input type="reset" name="reset" value="Reset" class="tamanho_botao">
	</form>

	</div>
	
<%@ include file="../imports/rodapeDentroPasta.jsp" %>

	<script>
$(document).ready(function() {
	var $seuCampoDataInicio = $("#dataInicio");
	$seuCampoDataInicio.mask('00/00/0000', {
		reverse: true
	});
});

$(document).ready(function() {
	var $seuCampoDataTermino = $("#dataTermino");
	$seuCampoDataTermino.mask('00/00/0000', {
		reverse: true
	});
});

$(document).ready(function() {
	var $seuCampoHoraInicio = $("#horaInicio");
	$seuCampoHoraInicio.mask('00:00', {
		reverse: true
	});
});

$(document).ready(function() {
	var $seuCampoHoraTermino = $("#horaTermino");
	$seuCampoHoraTermino.mask('00:00', {
		reverse: true
	});
});
		</script>
		<script>
var botao = document.querySelector("#enviar");
botao.addEventListener("click", function(event) {
	event.preventDefault();

	var form = document.querySelector("#form1");

	if (validar(form)) {
		form.submit();
	}
});

function validar(form) {
	var titulo = form.titulo.value;
	var dataInicio = form.dataInicio.value;
	var dataTermino = form.dataTermino.value;
	var horaInicio = form.horaInicio.value;
	var horaTermino = form.horaTermino.value;
	var endereco = form.endereco.value;

	if (titulo == "") {
		alert('DIGITE O TITULO DO TEXTO');
		form.titulo.focus();
		return false
	}
	if (dataInicio == "") {
		alert('DIGITE A DATA DE INICIO');
		form.dataInicio.focus();
		return false;
	}
	if (dataTermino == "") {
		alert('DIGITE A DATA DE TERMINO');
		form.dataTermino.focus();
		return false;
	}
	if (horaInicio == "") {
		alert('DIGITE O HORARIO DE INICIO');
		form.horaInicio.focus();
		return false;
	}
	if (horaTermino == "") {
		alert('DIGITE O HORARIO DE TERMINO');
		form.horaTermino.focus();
		return false;
	}
	if (endereco == "") {
		alert('DIGITE O ENDERECO');
		form.endereco.focus();
		return false;
	}
	return true;
}
		</script>







