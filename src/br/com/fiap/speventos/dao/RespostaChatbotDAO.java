package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.RespostaChatbot;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para construir respostas para o chatbot
 * Possui metodo para: abrir conexao, consultar realizacoes de evento
 * que atendem as solicitacoes dos usuarios, fechar conexao.
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see RespostaChatbot
 * @see RealizacaoEventoDAO
 */
public class RespostaChatbotDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	  * Metodo construtor que estabelece a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @throws Exception - Chamada da excecao checked SQLException
	  */	
	public RespostaChatbotDAO() throws Exception {
		con = new Conexao().conectar();
	}

	/**
	  * Metodo para consultar via dados informados no chatbot
	  * registros na tabela T_SGE_REALIZACAO_NOTICIA, T_SGE_LOCAL, T_SGE_EVENTO
	  * @author Techbot Solutions
	  * @param intencao recebe uma String
	  * @param subtipo recebe uma String
	  * @param dataHora recebe uma String
	  * @return uma lista com objetod do tipo RespostaChatbot
	  * @throws Exception - Chamada da excecao Exception
	  */
	public List<RespostaChatbot> consultar(String intencao, String subtipo, String dataHora) throws Exception {

		ArrayList<RespostaChatbot> listaEventos = new ArrayList<RespostaChatbot>();
		ArrayList<String> listaHorariosLocalPorFilme = new ArrayList<String>();

		int codEvento = 0;
		int codEventoAnterior = 0;
		String nomeEvento = new String();
		String nomeEventoAnterior = new String();
		String linkImagem = new String();
		String linkImagemAnterior = new String();
		String horarioInicio = new String();
		String horariosLocalPorFilme = new String();

		String nomeLocal = new String();
		String nomeLocalAnterior = new String();

		stmt = con
				.prepareStatement("SELECT T_SGE_EVENTO.CD_EVENTO, T_SGE_EVENTO.NM_EVENTO, T_SGE_EVENTO.DS_LINK_IMAGEM, "
						+ "T_SGE_REALIZACAO_EVENTO.DT_HR_INICIO, "
						+ "T_SGE_LOCAL.NM_LOCAL FROM T_SGE_REALIZACAO_EVENTO INNER JOIN T_SGE_EVENTO ON "
						+ "(T_SGE_REALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) INNER JOIN T_SGE_LOCAL ON "
						+ "(T_SGE_REALIZACAO_EVENTO.CD_LOCAL=T_SGE_LOCAL.CD_LOCAL) "
						+ "WHERE T_SGE_EVENTO.DS_TIPO_EVENTO=? " + "AND T_SGE_EVENTO.DS_SUBTIPO_EVENTO=? "
						+ "AND DT_HR_INICIO BETWEEN (TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') - interval '1' hour) "
						+ "AND (TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') + interval '2' hour) "
						+ "ORDER BY T_SGE_EVENTO.CD_EVENTO, T_SGE_LOCAL.NM_LOCAL, DT_HR_INICIO");

		stmt.setString(1, intencao);
		stmt.setString(2, subtipo);
		stmt.setString(3, dataHora);
		stmt.setString(4, dataHora);
		rs = stmt.executeQuery();

		while (rs.next()) {
			codEvento = rs.getInt("CD_EVENTO");
			nomeEvento = rs.getString("NM_EVENTO");
			linkImagem = rs.getString("DS_LINK_IMAGEM");
			horarioInicio = new SimpleDateFormat("HH:mm").format(rs.getTime("DT_HR_INICIO"));
			nomeLocal = rs.getString("NM_LOCAL");

			if (codEventoAnterior != 0 && codEvento != codEventoAnterior) {
				horariosLocalPorFilme = horariosLocalPorFilme + " - " + nomeLocalAnterior;
				listaHorariosLocalPorFilme.add(horariosLocalPorFilme);
				RespostaChatbot respChatbot = new RespostaChatbot(nomeEventoAnterior, linkImagemAnterior,
						listaHorariosLocalPorFilme);
				listaEventos.add(respChatbot);
				listaHorariosLocalPorFilme = new ArrayList<String>();
				nomeLocalAnterior = "";
			}
			if ((nomeLocal.equals(nomeLocalAnterior)) && (!nomeLocalAnterior.isEmpty())) {
				horariosLocalPorFilme = horariosLocalPorFilme + "&nbsp;&nbsp;" + horarioInicio;
			} else {
				if (!nomeLocalAnterior.isEmpty()) {
					horariosLocalPorFilme = horariosLocalPorFilme + " - " + nomeLocalAnterior;
					listaHorariosLocalPorFilme.add(horariosLocalPorFilme);
				}
				horariosLocalPorFilme = "";
				horariosLocalPorFilme = horarioInicio;
			}
			codEventoAnterior = codEvento;
			nomeLocalAnterior = nomeLocal;
			nomeEventoAnterior = nomeEvento;
			linkImagemAnterior = linkImagem;
		}
		horariosLocalPorFilme = horariosLocalPorFilme + " - " + nomeLocalAnterior;
		System.out.println(horariosLocalPorFilme);
		listaHorariosLocalPorFilme.add(horariosLocalPorFilme);
		RespostaChatbot respChatbot = new RespostaChatbot(nomeEventoAnterior, linkImagemAnterior,
				listaHorariosLocalPorFilme);
		listaEventos.add(respChatbot);
		listaHorariosLocalPorFilme = new ArrayList<String>();

		System.out.println(listaEventos.size() + " elementos em listaEventos");

		return listaEventos;
	}
	
	/**
	  * Metodo que fecha a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @throws Exception - Chamada da excecao Exception
	  */	
	public void fechar() throws Exception{
		con.close();
	}	
}
