<%@ include file="../imports/cabecaDentroPasta.jsp"%>

      <article class="texto_menu_lateral">
        <h1>Cadastro Pessoa Jur&iacute;dica</h1>
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
          <label>Nome (Nome Fantasia):</label>
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
          <label>CNPJ:</label>
          <br>
          <input id="cnpj" type="text" name="cnpj" maxlength="18">
          <br><br>
          <label>Raz&atilde;o Social:</label>
          <br>
          <input type="text" name="razaoSocial" maxlength="50">
          <br><br>
          <label>Senha:</label>
          <br>
          <input type="password" name="pass" maxlength="14">
          <br><br>
          <label>Digite a senha novamente:</label>
          <br>
          <input type="password" name="pass2" maxlength="14">
          <br><br>
          <h4>Declaro que estou ciente e concordo com os termos de uso
              <input type="checkbox" name="aceito" value="sim"></h4>
          <br>
          <h5>Desejo receber proximos eventos da SP-Eventos ou Parceiros
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
  var cnpj = form.cnpj.value;
  var razaoSocial = form.razaoSocial.value;

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
    alert('DIGITE SEU NOME FANTASIA');
    form.nome.focus();
    return false;
  }
  if (cnpj == "") {
    alert('DIGITE SEU CNPJ');
    form.cnpj.focus();
    return false;
  }
  if (razaoSocial == "") {
    alert('DIGITE RAZAO SOCIAL');
    form.razaoSocial.focus();
    return false;
  }

  if (senha == "") {
    alert('DIGITE UMA SENHA')
    form.pass.focus();
    return false;
  }
  if (senhax != senha) {
    alert('AS SENHAS ESTÃƒO DIFERENTES');
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
  var $seuCampoCnpj = $("#cnpj");
  $seuCampoCnpj.mask('00.000.000/0000-00', {
    reverse: true
  });
});

$(document).ready(function() {
  var $seuCampoTelefone = $("#TELEFONE");
  $seuCampoTelefone.mask('(00)0000-0000', {
    reserve: true
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