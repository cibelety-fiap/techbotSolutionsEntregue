<%@ include file="../imports/cabecaDentroPasta.jsp" %>

<article class="texto_menu_lateral">
				<form id="form1" autocomplete="off">
					<br>
					<h1>Cadastro de Evento</h1>
					<br>
					<label>T&iacute;tulo:</label>
					<br>
					<input type="text" name="titulo" value="" maxlength="30">
					<br><br>
					<label>Data de in&iacute;cio:</label>
					<br>
					<input id="dataInicio" type="text" name="dataInicio" value="">
					<br><br>
					<label>Data de t&eacute;rmino:</label>
					<br>
					<input id="dataTermino" type="text" name="dataTermino" value="">
					<br><br>
					<label>Hora de in&iacute;cio:</label>
					<br>
					<input id="horaInicio" type="text" name="horaInicio" value="">
					<br><br>
					<label>Hora de t&eacute;rmino:</label>
					<br>
					<input id="horaTermino" type="text" name="horaTermino" value="">
					<br><br>
					<label>Endere&ccedil;o:</label>
					<br>
					<input type="text" name="endereco" value="" maxlength="80">
					<br><br>
					<label>Contato:</label>
					<br>
					<input type="text" name="contato" value="" maxlength="30">
					<br>
					<br>
					<h4>Descri&ccedil;&atilde;o do Evento:</h4>
					<textarea name="message" rows="10" cols="80" maxlength="3500"></textarea>
					<br><br>
					<label>Qual a classifica&ccedil;&atilde;o do evento?</label>
					<select>
						<option></option>
					<option value="show">Show</option>
                    <option value="cinema">Cinema</option>
                    <option value="teatro">Teatro</option>
                </select>
					<br>
					<br><br>
					<input id="enviar" type="submit" name="enviar" value="Enviar" class="tamanho_botao">
					<input type="reset" name="reset" value="Reset" class="tamanho_botao">
				</form>
				</article>
				
			<footer>Copyright &copy; 2018 SP Eventos </footer>
		</div>
		<script src="../js/javascript_slides.js"></script>
		<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="../js/jquery.mask.js"></script>
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
	</body>

</html>