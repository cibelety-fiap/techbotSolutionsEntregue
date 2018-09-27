<%@ include file="../imports/cabecaDentroPasta.jsp" %>

<article class="texto_menu_lateral">
				<form id="form1" autocomplete="off">
					<br>
					<h1>Cadastro de Not&iacute;cias</h1>
					<br>
					<label>T&iacute;tulo:</label>
					<br>
					<input type="text" name="titulo" value="" maxlength="30">
					<br>
					<br>
					<label>Imagens:</label>
					<input type="file" name="pic" accept="image/*">
					<br>
					<br>
					<h4>Digite a not&iacute;cia:</h4>
					<textarea name="message" rows="10" cols="80" maxlength="3500"></textarea>
					<br><br>
					<label>Qual a Classifica&ccedil;&atilde;o da not&iacute;cia?</label>
					<select>
						<option></option>
          	<option value="show">Show</option>
          	<option value="cinema">Cinema</option>
          	<option value="teatro">Teatro</option>
         </select>
					<br><br>
					<input type="submit" id="enviar" name="enviar" value="Enviar" class="tamanho_botao">
					<input type="reset" name="reset" value="Reset" class="tamanho_botao">
				</form>
			</article>

<footer>Copyright &copy; 2018 SP Eventos </footer>
		</div>
		<script src="../js/javascript_slides.js"></script>
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
	var noticia = form.message.value;

	if (titulo == "") {
		alert('DIGITE O NOME DA NOTÍCIA');
		form.nome.focus();
		return false;
	}
	if (noticia == "") {
		alert('DIGITE O CONTE&Uacute;DO DA NOTÍCIA');
		form.sobrenome.focus();
		return false;
	}
}
		</script>
	</body>

</html>