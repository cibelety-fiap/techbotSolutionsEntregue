<%@ include file="../imports/cabeca.jsp"%>
<nav>
        <br />
        <div class="menu_lateral">
          <div><a class="menu_lateral" href="EventoServlet"><button class="menu_lateral">Eventos</button></a></div>
          <div><a class="menu_lateral" href="Logout"><button class="menu_lateral">Sair</button></a></div>
        </div>
      </nav>
<article style="float: right; padding-right: 30%;">
	<p>
		Olá, ${usuario.nome}! <br /><br />
		Na sua tela você pode ver os seus Eventos.<br /><br />
		Tamb&eacute;m pode visualizar, editar e remover as suas Realizações de Eventos.
	</p>
</article>
<%@ include file="../imports/rodape.jsp"%>