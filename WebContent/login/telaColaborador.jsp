<%@ include file="../imports/cabeca.jsp"%>

<nav>
        <br />
        <div class="menu_lateral">
          <div><a class="menu_lateral" href="EventoServlet"><button class="menu_lateral">Eventos</button></a></div>
          <div><button class="menu_lateral">Not&iacute;cias</button></div>
          <div><button class="menu_lateral">Gerenciar Usu&aacute;rios</button></div>
          <div><a class="menu_lateral" href="Logout"><button class="menu_lateral">Sair</button></a></div>
        </div>
      </nav>
     <article class="menu_central">
		Olá, ${usuario.nome}. Bem vindo(a) ao nosso site! <br /> 
		Na sua tela você pode acessar os seus eventos, cadastrar e gerenciar as suas notícias!
     </article>
<%@ include file="../imports/rodape.jsp"%>