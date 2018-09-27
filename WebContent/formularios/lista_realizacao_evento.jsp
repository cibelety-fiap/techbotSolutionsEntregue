<%@ include file="../imports/cabeca.jsp"%>

<%
	List<RealizacaoEvento> listaRealizacaoEvento = (List<RealizacaoEvento>) request
			.getAttribute("LISTA_REALIZACAO_EVENTO");

	int codigoEvento = Integer.parseInt(request.getParameter("codigoEvento"));
%>

<nav>
	<div class="menu_lateral">
		<div>
			<a class="menu_lateral" href="EventoServlet">
				<button class="menu_lateral">Eventos</button>
			</a>
		</div>
		<button onclick="goBack()" class="menu_lateral">Voltar</button>
	</div>
</nav>

<div class="menu_central">
	<br> 
		<button onclick="window.location.href='formularios/nova_realizacao_evento.jsp?codigoEvento=<%= codigoEvento %>'" class="menu_central">Cadastrar Nova</button>
	<br>
	<table class="tabela">
		<tr>
			<th class="tabelaHead">Realizacao Evento</th>
			<th class="tabelaHead">Data Inicio</th>
			<th class="tabelaHead">Data Termino</th>
			<th class="tabelaHead">Local</th>
			<th class="tabelaHead" colspan="2">Acoes</th>
		</tr>


		<%
			for (RealizacaoEvento realizacaoEventoTemp : listaRealizacaoEvento) {
				int codRealizEvento = realizacaoEventoTemp.getCodigoRealizacaoEvento();
		%>

		<tr>
			<td class="conteudo"><a
				href="RealizacaoEventoServlet?comando=carregar&tipoCarregamento=leitura&codRealizEvento=<%=codRealizEvento%>"><%=realizacaoEventoTemp.getEvento().getNomeEvento()%></a></td>
			<td class="conteudo"><%=realizacaoEventoTemp.getDataHoraInicio()%></td>
			<td class="conteudo"><%=realizacaoEventoTemp.getDataHoraTermino()%></td>
			<td class="conteudo"><%=realizacaoEventoTemp.getLocal().getNomeLocal()%>
			</td>
			<td class="conteudo">
				<button onclick="location.href='/techbotSolutions/RealizacaoEventoServlet?comando=carregar&tipoCarregamento=edicao&codRealizEvento=<%= realizacaoEventoTemp.getCodigoRealizacaoEvento() %>'">
						<img src="img/pen.png" id="icons">
				</button>
			</td>
			<td class="conteudo">							
				<a href="/techbotSolutions/RealizacaoEventoServlet?comando=remover&codRealizEvento=<%=codRealizEvento%>&codigoEvento=<%=codigoEvento%>"
							onclick="if (!(confirm('Voce tem certeza que deseja apagar?'))) return false">
					<button>			
						<img src="img/delete-remove.png" id="icons">
					</button>
				</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</div>


<%@ include file="../imports/rodape.jsp"%>