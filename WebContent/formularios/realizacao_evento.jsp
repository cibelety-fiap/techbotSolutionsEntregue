<%@ include file="../imports/cabeca.jsp" %>

<%
	RealizacaoEvento realizacaoEvento = (RealizacaoEvento) request.getAttribute("REALIZACAO_EVENTO");
%>
      <nav>
		<div class="menu_lateral">			
          <button onclick="goBack()" class="menu_lateral">Voltar</button>
		</div>
	</nav>
	
<div class="menu_central">

	<h1>Realizacao do Evento</h1>
		<br><br>
		<label>Evento: <%= realizacaoEvento.getEvento().getNomeEvento() %></label>
		<br /><br>
		<label>Data de in&iacute;cio: <%= realizacaoEvento.getDataHoraInicio().substring(0,10) %></label>
		<br><br>
		<label>Hora de in&iacute;cio: <%= realizacaoEvento.getDataHoraInicio().substring(10,16) %></label>
		<br><br>
		<label>Data de t&eacute;rmino: <%= realizacaoEvento.getDataHoraTermino().substring(0,10) %></label>
		<br><br>
		<label>Hora de t&eacute;rmino: <%= realizacaoEvento.getDataHoraTermino().substring(10,16) %></label>
		<br><br>
		<label>Endere&ccedil;o:</label>
		<br>
		<%= realizacaoEvento.getLocal().getNomeLocal() %><br />
		<%= realizacaoEvento.getLocal().getEnderecoLocal() %>
		<br><br>
	</div>
	
<%@ include file="../imports/rodape.jsp" %>





