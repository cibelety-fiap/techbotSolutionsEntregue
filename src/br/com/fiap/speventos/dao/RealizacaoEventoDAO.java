package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Evento;
import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.beans.RealizacaoEvento;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_REALIZACAO_EVENTO
 * Possui metodos para: abrir conexao, cadastrar, consultarPorCodigo, consultarPorNomeEvento, 
 * editar, remover, fechar conexao.
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see RealizacaoEvento
 */
public class RealizacaoEventoDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	  * Metodo construtor que estabelece a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @throws Exception - Chamada da excecao Exception
	  */	
	public RealizacaoEventoDAO() throws Exception {
		con = new Conexao().conectar();
	}
	
	/**
	  * Metodo para adicionar um registro na tabela T_SGE_REALIZACAO_EVENTO
	  * @author Techbot Solutions
	  * @param realizacaoEvento recebe um objeto do tipo RealizacaoEvento (Beans)
	  * @return um int com a quantidade de registros inseridos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int cadastrar(RealizacaoEvento realizacaoEvento) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SGE_REALIZACAO_EVENTO "
				+ "(CD_REALIZ_EVENTO, CD_EVENTO, CD_LOCAL, DT_HR_INICIO, DT_HR_TERMINO) " 
				+ "VALUES (?,?,?,TO_DATE(?,\'DD/MM/YYYY HH24:MI\'), TO_DATE(?,\'DD/MM/YYYY HH24:MI\'))");

		stmt.setInt(1, realizacaoEvento.getCodigoRealizacaoEvento());
		stmt.setInt(2, realizacaoEvento.getEvento().getCodigoEvento());
		stmt.setInt(3, realizacaoEvento.getLocal().getCodigoLocal());
		stmt.setString(4, realizacaoEvento.getDataHoraInicio());
		stmt.setString(5, realizacaoEvento.getDataHoraTermino());

		return stmt.executeUpdate();
	}

	/**
	  * Metodo para consultar por codigo da realizacao do evento
	  * um registro na tabela T_SGE_REALIZACAO_EVENTO, T_SGE_EVENTO, T_SGE_LOCAL
	  * @author Techbot Solutions
	  * @param codRealizEvento recebe um int
	  * @return um objeto do tipo RealizacaoEvento
	  * @throws Exception - Chamada da excecao Exception
	  */
	public RealizacaoEvento consultarPorCodigo(int codRealizEvento) throws Exception {
		stmt = con.prepareStatement("SELECT T_SGE_REALIZACAO_EVENTO.CD_REALIZ_EVENTO, "
				+ "T_SGE_REALIZACAO_EVENTO.CD_EVENTO, T_SGE_REALIZACAO_EVENTO.CD_LOCAL, "
				+ "TO_CHAR(T_SGE_REALIZACAO_EVENTO.DT_HR_INICIO,'DD/MM/YYYY HH24:MI') \"DT_HR_INICIO\", "
				+ "TO_CHAR(T_SGE_REALIZACAO_EVENTO.DT_HR_TERMINO,'DD/MM/YYYY HH24:MI') \"DT_HR_TERMINO\", "
				+ "T_SGE_EVENTO.DS_LINK_IMAGEM, T_SGE_EVENTO.NM_EVENTO, T_SGE_EVENTO.DS_TIPO_EVENTO, "
				+ "T_SGE_EVENTO.DS_SUBTIPO_EVENTO, T_SGE_EVENTO.DS_EVENTO, " 
				+ "T_SGE_EVENTO.DS_CONTATO_MAIS_INFO, T_SGE_LOCAL.NM_LOCAL, T_SGE_LOCAL.DS_ENDERECO "
				+ "FROM T_SGE_REALIZACAO_EVENTO " 
				+ "INNER JOIN T_SGE_EVENTO ON "
				+ "(T_SGE_REALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) " 
				+ "INNER JOIN T_SGE_LOCAL ON "
				+ "(T_SGE_REALIZACAO_EVENTO.CD_LOCAL=T_SGE_LOCAL.CD_LOCAL) " 
				+ "WHERE CD_REALIZ_EVENTO=?");

		stmt.setInt(1, codRealizEvento);

		rs = stmt.executeQuery();

		if (rs.next()) {
			return new RealizacaoEvento(
					rs.getInt("CD_REALIZ_EVENTO"),
					new Evento(rs.getInt("CD_EVENTO"), rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_EVENTO"),
							rs.getString("DS_TIPO_EVENTO"), rs.getString("DS_SUBTIPO_EVENTO"),
							rs.getString("DS_EVENTO"), rs.getString("DS_CONTATO_MAIS_INFO")),
					new Local(
							rs.getInt("CD_LOCAL"), rs.getString("NM_LOCAL"), rs.getString("DS_ENDERECO")),
					rs.getString("DT_HR_INICIO"), rs.getString("DT_HR_TERMINO"));
		} else {
			return new RealizacaoEvento();
		}
	}

	/**
	  * Metodo para consultar por nome do evento
	  * um registro na tabela T_SGE_REALIZACAO_EVENTO, T_SGE_EVENTO, T_SGE_LOCAL
	  * @author Techbot Solutions
	  * @param nomeEvento recebe um objeto do tipo String
	  * @return uma lista com objetos do tipo RealizacaoEvento
	  * @throws Exception - Chamada da excecao Exception
	  */
	public List<RealizacaoEvento> consultarPorNomeEvento(String nomeEvento) throws Exception {
		List<RealizacaoEvento> listaRealizEvento = new ArrayList<RealizacaoEvento>();
		
		stmt = con.prepareStatement("SELECT T_SGE_REALIZACAO_EVENTO.CD_REALIZ_EVENTO, "
								+ "T_SGE_REALIZACAO_EVENTO.CD_EVENTO, T_SGE_REALIZACAO_EVENTO.CD_LOCAL, " 
								+ "TO_CHAR(T_SGE_REALIZACAO_EVENTO.DT_HR_INICIO,'DD/MM/YYYY HH24:MI') \"DT_HR_INICIO\", "  
								+ "TO_CHAR(T_SGE_REALIZACAO_EVENTO.DT_HR_TERMINO,'DD/MM/YYYY HH24:MI') \"DT_HR_TERMINO\", "  
								+ "T_SGE_EVENTO.DS_LINK_IMAGEM, T_SGE_EVENTO.NM_EVENTO, T_SGE_EVENTO.DS_TIPO_EVENTO, " 
								+ "T_SGE_EVENTO.DS_SUBTIPO_EVENTO, T_SGE_EVENTO.DS_EVENTO, " 
								+ "T_SGE_EVENTO.DS_CONTATO_MAIS_INFO, T_SGE_LOCAL.NM_LOCAL, T_SGE_LOCAL.DS_ENDERECO "
								+ "FROM T_SGE_REALIZACAO_EVENTO " 
								+ "INNER JOIN T_SGE_EVENTO ON "
								+ "(T_SGE_REALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) " 
								+ "INNER JOIN T_SGE_LOCAL ON "
								+ "(T_SGE_REALIZACAO_EVENTO.CD_LOCAL=T_SGE_LOCAL.CD_LOCAL) " 
								+ "WHERE UPPER(T_SGE_EVENTO.NM_EVENTO) LIKE ?");

		stmt.setString(1, "%" + nomeEvento + "%");

		rs = stmt.executeQuery();

		while (rs.next()) {
			listaRealizEvento.add(new RealizacaoEvento(
					rs.getInt("CD_REALIZ_EVENTO"),
					new Evento(rs.getInt("CD_EVENTO"), rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_EVENTO"),
							rs.getString("DS_TIPO_EVENTO"), rs.getString("DS_SUBTIPO_EVENTO"),
							rs.getString("DS_EVENTO"), rs.getString("DS_CONTATO_MAIS_INFO")),
					new Local(
							rs.getInt("CD_LOCAL"), rs.getString("NM_LOCAL"), rs.getString("DS_ENDERECO")),
					rs.getString("DT_HR_INICIO"), rs.getString("DT_HR_TERMINO")));
		}

		return listaRealizEvento;
	}

	/**
	  * Metodo para consultar por codigo do evento
	  * registros na tabela T_SGE_REALIZACAO_EVENTO, T_SGE_EVENTO, T_SGE_LOCAL
	  * @author Techbot Solutions
	  * @param codEvento recebe um int
	  * @return uma lista com objetos do tipo RealizacaoEvento
	  * @throws Exception - Chamada da excecao Exception
	  */
	public List<RealizacaoEvento> consultarPorCodEvento(int codEvento) throws Exception {
		List<RealizacaoEvento> listaRealizEvento = new ArrayList<RealizacaoEvento>();
		
		stmt = con.prepareStatement("SELECT T_SGE_REALIZACAO_EVENTO.CD_REALIZ_EVENTO, "
								+ "T_SGE_REALIZACAO_EVENTO.CD_EVENTO, T_SGE_REALIZACAO_EVENTO.CD_LOCAL, " 
								+ "TO_CHAR(T_SGE_REALIZACAO_EVENTO.DT_HR_INICIO,'DD/MM/YYYY HH24:MI') \"DT_HR_INICIO\", "  
								+ "TO_CHAR(T_SGE_REALIZACAO_EVENTO.DT_HR_TERMINO,'DD/MM/YYYY HH24:MI') \"DT_HR_TERMINO\", "  
								+ "T_SGE_EVENTO.DS_LINK_IMAGEM, T_SGE_EVENTO.NM_EVENTO, T_SGE_EVENTO.DS_TIPO_EVENTO, " 
								+ "T_SGE_EVENTO.DS_SUBTIPO_EVENTO, T_SGE_EVENTO.DS_EVENTO, " 
								+ "T_SGE_EVENTO.DS_CONTATO_MAIS_INFO, T_SGE_LOCAL.NM_LOCAL, T_SGE_LOCAL.DS_ENDERECO "
								+ "FROM T_SGE_REALIZACAO_EVENTO " 
								+ "INNER JOIN T_SGE_EVENTO ON "
								+ "(T_SGE_REALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) " 
								+ "INNER JOIN T_SGE_LOCAL ON "
								+ "(T_SGE_REALIZACAO_EVENTO.CD_LOCAL=T_SGE_LOCAL.CD_LOCAL) " 
								+ "WHERE T_SGE_EVENTO.CD_EVENTO=?");

		stmt.setInt(1, codEvento);

		rs = stmt.executeQuery();

		while (rs.next()) {
			listaRealizEvento.add(new RealizacaoEvento(
					rs.getInt("CD_REALIZ_EVENTO"),
					new Evento(rs.getInt("CD_EVENTO"), rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_EVENTO"),
							rs.getString("DS_TIPO_EVENTO"), rs.getString("DS_SUBTIPO_EVENTO"),
							rs.getString("DS_EVENTO"), rs.getString("DS_CONTATO_MAIS_INFO")),
					new Local(
							rs.getInt("CD_LOCAL"), rs.getString("NM_LOCAL"), rs.getString("DS_ENDERECO")),
					rs.getString("DT_HR_INICIO"), rs.getString("DT_HR_TERMINO")));
		}

		return listaRealizEvento;
	}
	
	/**
	  * Metodo para editar um registro na tabela T_SGE_REALIZACAO_EVENTO
	  * @author Techbot Solutions
	  * @param realizacaoEvento recebe um objeto do tipo RealizacaoEvento
	  * @return um int com a quantidade de registros editados
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int editar(RealizacaoEvento realizacaoEvento) throws Exception {
		stmt = con.prepareStatement("UPDATE T_SGE_REALIZACAO_EVENTO "
				+ "SET CD_REALIZ_EVENTO=?, CD_EVENTO=?, CD_LOCAL=?, "
				+ "DT_HR_INICIO=TO_DATE(?,\'DD/MM/YYYY HH24:MI\'), "
				+ "DT_HR_TERMINO=TO_DATE(?,\'DD/MM/YYYY HH24:MI\') " 
				+ "WHERE CD_REALIZ_EVENTO = ?");

		stmt.setInt(1, realizacaoEvento.getCodigoRealizacaoEvento());
		stmt.setInt(2, realizacaoEvento.getEvento().getCodigoEvento());
		stmt.setInt(3, realizacaoEvento.getLocal().getCodigoLocal());
		stmt.setString(4, realizacaoEvento.getDataHoraInicio());
		stmt.setString(5, realizacaoEvento.getDataHoraTermino());
		stmt.setInt(6, realizacaoEvento.getCodigoRealizacaoEvento());

		return stmt.executeUpdate();
	}

	/**
	  * Metodo para remover um registro na tabela T_SGE_REALIZACAO_EVENTO
	  * @author Techbot Solutions
	  * @param codRealizEvento recebe um objeto do tipo int
	  * @return um int com o numero de itens removidos
	  * @throws Exception - Chamada da excecao Exception
	  */	
	public int remover(int codRealizEvento) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_REALIZACAO_EVENTO " 
				+ "WHERE CD_REALIZ_EVENTO=?");
		stmt.setInt(1, codRealizEvento);

		return stmt.executeUpdate();
	}
	
	/**
	  * Metodo que fecha a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @throws Exception - Chamada da excecao Exception
	  */	
	public void fechar() throws Exception{
		con.close();
	}
	
	/**
	  * Metodo para calcular o proximo codigo de realizacao de evento a ser inserido,
	  * a solucao foi proposta pela Profa. Rita como alternativa (temporaria) ao auto-increment
	  * @author Techbot Solutions
	  * @return um int com o numero do proximo codigo a ser inseridos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int calcularCodRealizEvento() throws Exception {
		int proxCodigo = 0;
		int qtCodRealizEvento = 0;
		
		stmt = con.prepareStatement("SELECT COUNT(CD_REALIZ_EVENTO) FROM T_SGE_REALIZACAO_EVENTO");

		rs = stmt.executeQuery();
		if (rs.next()) {
			qtCodRealizEvento= rs.getInt("COUNT(CD_REALIZ_EVENTO)");
			if (qtCodRealizEvento == 0) {
				proxCodigo = 1;
			} else {			
				stmt = con.prepareStatement("SELECT MAX(CD_REALIZ_EVENTO) FROM T_SGE_REALIZACAO_EVENTO");
				rs = stmt.executeQuery();
				if (rs.next()) {
					proxCodigo = rs.getInt("MAX(CD_REALIZ_EVENTO)") + 1;
				}
			}
		}
		return proxCodigo;
	}
}
