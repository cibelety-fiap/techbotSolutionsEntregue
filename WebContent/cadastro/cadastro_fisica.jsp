<%@ include file="../imports/cabecaDentroPasta.jsp"%>

      <article class="texto_menu_lateral">
        <br>
        <h1>Cadastro Pessoa F&iacute;sica</h1>
        <br>
        <form id="form1">
          <label>Login (E-mail):</label>
          <br>
          <input type="text" name="email" maxlength="40">
          <br><br>
          <label>Digite o e-mail novamente:</label>
          <br>
          <input type="text" name="emailNovamente" value="" maxlength="40">
          <br><br>
          <label>Nome Completo:</label>
          <br>
          <input type="text" name="nome" maxlength="50">
          <br><br>
          <label>Telefone:</label>
          <br>
          <input id="TELEFONE" type="text" name="telefone" maxlength="14">
          <br><br>
          <label>Endere&ccedil;o:</label>
          <br>
          <input type="text" name="endereco" maxlength="80">
          <br><br>
          <label>CPF:</label>
          <br>
          <input id="CPF" type="text" name="cpf" maxlength="14">
          <br><br>
          <label>RG:</label>
          <br>
          <input id="RG" type="text" name="rg" maxlength="14">
          <br><br>
          <label>Data de Nascimento:</label>
          <br>
          <input id="NASCIMENTO" type="text" name="dataNascimento" maxlength="8">
          <br><br>
          <label>Senha:</label>
          <br>
          <input type="password" name="pass" maxlength="14">
          <br><br>
          <label>Digite a senha novamente:</label>
          <br>
          <input type="password" name="pass2" maxlength="14">
          <br><br>
          <h4>G&ecirc;nero</h4>
          <input type="radio" name="genero" value="masculino"> Masculino<br>
          <input type="radio" name="genero" value="feminino"> Feminino<br>
          <input type="radio" name="genero" value="outro"> Outro<br>         
          <br><br>
          <h4>Declaro que estou ciente e concordo com os termos de uso
              <input type="checkbox" name="aceito" value="sim"></h4>
          <br>
          <h5>Desejo receber pr&oacute;ximos eventos da SP-Eventos ou Parceiros
            <input type="checkbox" name="opcao" value="sim"></h5>
          <br>
          <input id="enviar" type="submit" name="enviar" value="Enviar" class="tamanho_botao">
          <input type="reset" name="reset" value="Reset" class="tamanho_botao">
        </form>
      </article>

      <footer>Copyright &copy; 2018 SP Eventos </footer>
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
    </script>
    <script type="text/javascript" src="../js/javascript_slides.js"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.mask.js"></script>
    <script>
$(document).ready(function() {
  var $seuCampoCpf = $("#CPF");
  $seuCampoCpf.mask('000.000.000-00', {
    reverse: true
  });
});

$(document).ready(function() {
  var $seuCampoRg = $("#RG");
  $seuCampoRg.mask('00.000.000-0', {
    reverse: true
  });
});

$(document).ready(function() {
	var $seuCampoNascimento = $("#NASCIMENTO");
	$seuCampoNascimento.mask('00/00/0000', {
		reverse: true
	});
});

$(document).ready(function() {
  var $seuCampoTelefone = $("#TELEFONE");
  $seuCampoTelefone.mask('(00)0000-0000', {
    reverse: true
  });
});
    </script>
    <script>
function myFunction() {
  var x = document.getElementById("myTopnav");
  if (x.className === "topnav") {
    x.className += " responsive";
  } else {
    x.className = "topnav";
  }
}
    </script>
  </body>

</html>