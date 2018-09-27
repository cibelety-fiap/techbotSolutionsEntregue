<%@ include file="../imports/cabeca.jsp"%>

	<nav>
        <br />
        <div class="menu_lateral">
          <div><a class="menu_lateral" href="EventoServlet"><button class="menu_lateral">Eventos</button></a></div>
          <div><a class="menu_lateral" href="index.jsp"><button class="menu_lateral">Sair</button></a></div>
        </div>
      </nav>
      
      <article class="menu_central">
          Seja bem-vindo! Nessa se&ccedil;&atilde;o voc&ecirc; poder&aacute; cadastrar eventos e alterar os seus dados cadastrais.
      </article>
      
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
<%@ include file="../imports/rodape.jsp"%>