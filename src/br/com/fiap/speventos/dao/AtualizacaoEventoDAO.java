package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.AtualizacaoEvento;
import br.com.fiap.speventos.beans.Evento;
import br.com.fiap.speventos.beans.Usuario;
import br.com.fiap.speventos.bo.AtualizacaoEventoBO;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_ATUALIZACAO_EVENTO
 * Possui metodos para: abrir conexao, cadastrar, consultarPorCodigo, consultarPorCodigoEvento, 
 * editar, remover, fechar conexao.
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see AtualizacaoEvento
 * @see Evento
 * @see Usuario
 *
 */

public class AtualizacaoEventoDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	

	/**
	  * Metodo construtor que estabelece a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @throws Exception - Chamada da excecao Exception
	  */
	public AtualizacaoEventoDAO() throws Exception {
		con = new Conexao().conectar();
	}
	
	/**
	  * Metodo para adicionar um registro na tabela T_SGE_ATUALIZACAO_EVENTO
	  * @author Techbot Solutions
	  * @param atualizacaoEvento recebe um objeto do tipo AtualizacaoEvento (Beans)
	  * @return um int com a quantidade de registros inseridos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int cadastrar(AtualizacaoEvento atualizacaoEvento)
			throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SGE_ATUALIZACAO_EVENTO "
				+ "(CD_ATUALIZ_EVENTO, CD_USUARIO, CD_EVENTO, DT_HR_ATUALIZACAO, DS_TIPO_ATUALIZACAO) "
				+ "VALUES (?,?,?,?,?)");
		stmt.setInt(1, atualizacaoEvento.getCodigoAtualizacaoEvento());
		stmt.setInt(2, atualizacaoEvento.getUsuario().getCodigoUsuario());
		stmt.setInt(3, atualizacaoEvento.getEvento().getCodigoEvento());
		stmt.setString(4, atualizacaoEvento.getDataHoraAtualizacao());
		stmt.setString(5, atualizacaoEvento.getTipoAtualizacao());

		return stmt.executeUpdate();
	}
	
	/**
	  * Metodo para consultar por codigo da atualizacao do evento 
	  * um registro na tabela T_SGE_ATUALIZACAO_EVENTO, T_SGE_EVENTO, T_SGE_USUARIO
	  * @author Techbot Solutions
	  * @param codigoAtualizacaoEvento recebe um int
	  * @return um objeto do tipo AtualizacaoEvento
	  * @throws Exception - Chamada da excecao Exception
	  */
	public AtualizacaoEvento consultar(int codigoAtualizacaoEvento) throws Exception {
		stmt = con.prepareStatement(
				"SELECT * FROM T_SGE_ATUALIZACAO_EVENTO "
				+ "INNER JOIN T_SGE_EVENTO ON "
				+ "(T_SGE_ATUALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) "
				+ "INNER JOIN T_SGE_USUARIO ON "
				+ "(T_SGE_ATUALIZACAO_EVENTO.CD_USUARIO=T_SGE_USUARIO.CD_USUARIO)  "
				+ "WHERE T_SGE_ATUALIZACAO_EVENTO.CD_ATUALIZ_EVENTO=?");

		stmt.setInt(1, codigoAtualizacaoEvento);

		rs = stmt.executeQuery();
		if (rs.next()) {
			return new AtualizacaoEvento(
					rs.getInt("CD_ATUALIZ_EVENTO"),
					new Evento(rs.getInt("CD_EVENTO"), 
							rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_EVENTO"),
							rs.getString("DS_TIPO_EVENTO"), rs.getString("DS_SUBTIPO_EVENTO"),
							rs.getString("DS_EVENTO"), rs.getString("DS_CONTATO_MAIS_INFO")),
					new Usuario(rs.getInt("CD_USUARIO"), rs.getString("DS_EMAIL"), rs.getString("DS_SENHA"),
							rs.getString("NM_USUARIO")),
					rs.getString("DT_HR_ATUALIZACAO"), rs.getString("DS_TIPO_ATUALIZACAO"));
		} else {
			return new AtualizacaoEvento();
		}
	}
	
	/**
	  * Metodo para consultar por codigo de evento 
	  * registros na tabela T_SGE_ATUALIZACAO_EVENTO, T_SGE_EVENTO, T_SGE_USUARIO
	  * @author Techbot Solutions
	  * @param codigoEvento recebe um int
	  * @return uma lista com objetos do tipo atualizacao de evento
	  * @throws Exception - Chamada da excecao Exception
	  */
	public List<AtualizacaoEvento> consultarPorCodigoEvento(int codigoEvento) throws Exception {
		List<AtualizacaoEvento> listaAtualizacaoEvento = new ArrayList<AtualizacaoEvento>();
		
		stmt = con.prepareStatement(
				"SELECT * FROM T_SGE_ATUALIZACAO_EVENTO INNER JOIN T_SGE_EVENTO ON "
				+ "(T_SGE_ATUALIZACAO_EVENTO.CD_EVENTO=T_SGE_EVENTO.CD_EVENTO) "
				+ "INNER JOIN T_SGE_USUARIO ON "
				+ "(T_SGE_ATUALIZACAO_EVENTO.CD_USUARIO=T_SGE_USUARIO.CD_USUARIO) "
				+ "WHERE T_SGE_ATUALIZACAO_EVENTO.CD_EVENTO=?");
		stmt.setInt(1, codigoEvento);

		rs = stmt.executeQuery();

		while (rs.next()) {
			listaAtualizacaoEvento.add(new AtualizacaoEvento(
					rs.getInt("CD_ATUALIZ_EVENTO"),
					new Evento(rs.getInt("CD_EVENTO"), rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_EVENTO"),
							rs.getString("DS_TIPO_EVENTO"), rs.getString("DS_SUBTIPO_EVENTO"),
							rs.getString("DS_EVENTO"), rs.getString("DS_CONTATO_MAIS_INFO")),
					new Usuario(rs.getInt("CD_USUARIO"), rs.getString("DS_EMAIL"), rs.getString("DS_SENHA"),
							rs.getString("NM_USUARIO")),
					rs.getString("DT_HR_ATUALIZACAO"), 
					rs.getString("DS_TIPO_ATUALIZACAO")
				)
			);
		}
		return listaAtualizacaoEvento;
	}
	
	/**
	  * Metodo para editar um registro na tabela T_SGE_ATUALIZACAO_EVENTO
	  * @author Techbot Solutions
	  * @param atualizacaoEvento recebe um objeto do tipo AtualizacaoEvento
	  * @return um int com a quantidade de registros editados
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int editar(AtualizacaoEvento atualizacaoEvento) throws Exception{
		stmt = con.prepareStatement("UPDATE T_SGE_ATUALIZACAO_EVENTO "
				+ "SET CD_ATUALIZ_EVENTO=?, "
				+ "CD_EVENTO=?, "
				+ "CD_USUARIO=?, "
				+ "DT_HR_ATUALIZACAO=?, "
				+ "DS_TIPO_ATUALIZACAO=? "
				+ "WHERE CD_ATUALIZ_EVENTO = ?");
		stmt.setInt(1, atualizacaoEvento.getCodigoAtualizacaoEvento());
		stmt.setInt(2, atualizacaoEvento.getUsuario().getCodigoUsuario());
		stmt.setInt(3, atualizacaoEvento.getEvento().getCodigoEvento());
		stmt.setString(4, atualizacaoEvento.getDataHoraAtualizacao());
		stmt.setString(5, atualizacaoEvento.getTipoAtualizacao());
		stmt.setInt(6, atualizacaoEvento.getCodigoAtualizacaoEvento());

		return stmt.executeUpdate();
	}
	
	
	/**
	  * Metodo para remover um registro na tabela T_SGE_ATUALIZACAO_EVENTO
	  * @author Techbot Solutions
	  * @param codigoAtualizacaoEvento recebe um objeto do tipo int
	  * @return um int com o numero de itens removidos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int remover(int codigoAtualizacaoEvento) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_ATUALIZACAO_EVENTO "
				+ "WHERE CD_ATUALIZACAO_EVENTO=?");
		stmt.setInt(1, codigoAtualizacaoEvento);

		return stmt.executeUpdate();
	}
	
	/**
	  * Metodo que fecha a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @throws Exception - Chamada da excecao Exception
	  */
	public void fechar() throws Exception {
		con.close();
	}
}