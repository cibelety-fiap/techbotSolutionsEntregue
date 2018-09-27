<%@ include file="../imports/cabeca.jsp"%>

<%
	List<Evento> listaEvento = (List<Evento>) request.getAttribute("LISTA_EVENTO");
%>
<nav>
        <br />
        <div class="menu_lateral">
          <div><button onclick="goBack()" class="menu_lateral">Voltar</button></div>
          <div><a class="menu_lateral" href="Logout"><button class="menu_lateral">Sair</button></a></div>
        </div>
      </nav>
	<div class="menu_central">

		<h1>Eventos</h1>
		<br>

		<br>
		<table class="tabela">
			<tr>
				<th class="tabelaHead">Evento</th>
				<th class="tabelaHead">Tipo</th>
				<th class="tabelaHead">Genero</th>
				<th class="tabelaHead">Descricao</th>
			</tr>


			<%
				for (Evento eventoTemp : listaEvento) {
			%>

			<tr>
				<td class="conteudo"><a href="EventoServlet?comando=carregar&codigoEvento=<%=eventoTemp.getCodigoEvento()%>"><%=eventoTemp.getNomeEvento()%></a></td>
				<td class="conteudo"><%=eventoTemp.getTipoEvento()%></td>
				<td class="conteudo"><%=eventoTemp.getSubtipoEvento()%></td>
				<td class="conteudo"><%=eventoTemp.getDescricaoEvento().substring(0, 40)%>
					(...)</td>
			</tr>
				<%
					}
				%>
			
		</table>
	</div>
	<%@ include file="../imports/rodape.jsp"%>