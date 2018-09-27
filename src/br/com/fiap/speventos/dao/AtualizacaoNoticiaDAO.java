package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.AtualizacaoNoticia;
import br.com.fiap.speventos.beans.Colaborador;
import br.com.fiap.speventos.beans.Noticia;
import br.com.fiap.speventos.conexao.Conexao;


/**
 * Classe para manipular a tabela T_SGE_ATUALIZACAO_NOTICIA
 * Possui metodos para: abrir conexao, cadastrar, consultarPorCodigo, consultarPorCodigoNoticia, 
 * editar, remover, fechar conexao.
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see AtualizacaoNoticia
 * @see Colaborador
 * @see Noticia
 * 
 */
public class AtualizacaoNoticiaDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	  * Metodo construtor que estabelece a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @throws Exception - Chamada da excecao Exception
	  */	
	public AtualizacaoNoticiaDAO() throws Exception {
		con = new Conexao().conectar();
	}
	
	/**
	  * Metodo para adicionar um registro na tabela T_SGE_ATUALIZACAO_NOTICIA
	  * @author Techbot Solutions
	  * @param atualizacaoNoticia recebe um objeto do tipo AtualizacaoNoticia (Beans)
	  * @return um int com a quantidade de registros inseridos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int cadastrar(AtualizacaoNoticia atualizacaoNoticia)
			throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SGE_ATUALIZACAO_NOTICIA "
				+ "(CD_ATUALIZACAO_NOTICIA, CD_NOTICIA, CD_USUARIO, DT_HR_ATUALIZACAO, DS_TIPO_ATUALIZACAO) "
				+ "VALUES (?,?,?,?,?)");
		stmt.setInt(1, atualizacaoNoticia.getCodigoAtualizacaoNoticia());
		stmt.setInt(2, atualizacaoNoticia.getNoticia().getCodigoNoticia());
		stmt.setInt(3, atualizacaoNoticia.getColaborador().getCodigoUsuario());
		stmt.setString(4, atualizacaoNoticia.getDataHoraAtualizacao());
		stmt.setString(5, atualizacaoNoticia.getTipoAtualizacao());

		return stmt.executeUpdate();
	}
	
	/**
	  * Metodo para consultar por codigo da atualizacao da noticia
	  * um registro na tabela T_SGE_ATUALIZACAO_NOTICIA
	  * @author Techbot Solutions
	  * @param codigoAtualizacaoNoticia recebe um int
	  * @return um objeto do tipo AtualizacaoNoticia
	  * @throws Exception - Chamada da excecao Exception
	  */
	public AtualizacaoNoticia consultar(int codigoAtualizacaoNoticia) throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SGE_ATUALIZACAO_NOTICIA "
				+ "INNER JOIN T_SGE_NOTICIA ON "
				+ "(T_SGE_ATUALIZACAO_NOTICIA.CD_NOTICIA=T_SGE_NOTICIA.CD_NOTICIA) "
				+ "INNER JOIN T_SGE_COLABORADOR ON "
				+ "(T_SGE_ATUALIZACAO_NOTICIA.CD_USUARIO=T_SGE_COLABORADOR.CD_USUARIO) "
				+ "WHERE T_SGE_ATUALIZACAO_NOTICIA.CD_ATUALIZACAO_NOTICIA=?");

		stmt.setInt(1, codigoAtualizacaoNoticia);

		rs = stmt.executeQuery();
		
		if (rs.next()) {
			return new AtualizacaoNoticia(rs.getInt("CD_ATUALIZACAO_NOTICA"),
					new Noticia(
							rs.getInt("CD_NOTICIA"), rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_NOTICIA"),
							rs.getString("DS_CATEGORIA_NOTICIA"), rs.getString("DT_HR_NOTICIA"),
							rs.getString("DS_NOTICIA")),
					new Colaborador(rs.getInt("CD_USUARIO"), rs.getString("DS_EMAIL"), rs.getString("DS_SENHA"),
							rs.getString("NM_USUARIO"), rs.getString("DS_NIVEL_ACESSO"),
							rs.getString("DS_DEPARTAMENTO")),
					rs.getString("DT_HR_ATUALIZACAO"), rs.getString("DS_TIPO_ATUALIZACAO"));

		} else {
			return new AtualizacaoNoticia();
		}

	}
	
	/**
	  * Metodo para consultar por codigo de noticia registros na tabela T_SGE_ATUALIZACAO_NOTICIA
	  * @author Techbot Solutions
	  * @param codigoNoticia recebe um int
	  * @return uma lista com objetos do tipo atualizacao de noticia
	  * @throws Exception - Chamada da excecao Exception
	  */
	public List<AtualizacaoNoticia> consultarPorCodigoNoticia(int codigoNoticia) throws Exception {
		List<AtualizacaoNoticia> listaAtualizacaoNoticia = new ArrayList<AtualizacaoNoticia>();

		stmt = con.prepareStatement("SELECT * FROM T_SGE_ATUALIZACAO_NOTICIA "
				+ "INNER JOIN T_SGE_NOTICIA ON "
				+ "(T_SGE_ATUALIZACAO_NOTICIA.CD_NOTICIA=T_SGE_NOTICIA.CD_NOTICIA) "
				+ "INNER JOIN T_SGE_COLABORADOR ON "
				+ "(T_SGE_ATUALIZACAO_NOTICIA.CD_USUARIO=T_SGE_COLABORADOR.CD_USUARIO) "
				+ "WHERE T_SGE_ATUALIZACAO_NOTICIA.CD_NOTICIA=?");
		stmt.setInt(1, codigoNoticia);

		while (rs.next()) {
			listaAtualizacaoNoticia.add(new AtualizacaoNoticia(
					rs.getInt("CD_ATUALIZACAO_NOTICA"),
					new Noticia(rs.getInt("CD_NOTICIA"), rs.getString("DS_LINK_IMAGEM"), rs.getString("NM_NOTICIA"),
							rs.getString("DS_CATEGORIA_NOTICIA"), rs.getString("DT_HR_NOTICIA"),
							rs.getString("DS_NOTICIA")),
					new Colaborador(rs.getInt("CD_USUARIO"), rs.getString("DS_EMAIL"), rs.getString("DS_SENHA"),
							rs.getString("NM_USUARIO"), rs.getString("DS_NIVEL_ACESSO"),
							rs.getString("DS_DEPARTAMENTO")),
					rs.getString("DT_HR_ATUALIZACAO"), rs.getString("DS_TIPO_ATUALIZACAO")));

		}
		return listaAtualizacaoNoticia;
	}
	
	/**
	  * Metodo para editar um registro na tabela T_SGE_ATUALIZACAO_NOTICIA
	  * @author Techbot Solutions
	  * @param atualizacaoNoticia recebe um objeto do tipo AtualizacaoNoticia
	  * @return um int com a quantidade de registros editados
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int editar(AtualizacaoNoticia atualizacaoNoticia) throws Exception {
		stmt = con.prepareStatement("UPDATE T_SGE_ATUALIZACAO_NOTICIA " 
				+ "SET CD_ATUALIZACAO_NOTICIA=?, "
				+ "CD_NOTICIA=?, " 
				+ "CD_USUARIO=?, " 
				+ "DT_HR_ATUALIZACAO=?, " 
				+ "DS_TIPO_ATUALIZACAO=? "
				+ "WHERE CD_ATUALIZACAO_NOTICIA = ?");
		stmt.setInt(1, atualizacaoNoticia.getCodigoAtualizacaoNoticia());
		stmt.setInt(2, atualizacaoNoticia.getNoticia().getCodigoNoticia());
		stmt.setInt(3, atualizacaoNoticia.getColaborador().getCodigoUsuario());
		stmt.setString(4, atualizacaoNoticia.getDataHoraAtualizacao());
		stmt.setString(5, atualizacaoNoticia.getTipoAtualizacao());

		return stmt.executeUpdate();
	}
	
	/**
	  * Metodo para remover um registro na tabela T_SGE_ATUALIZACAO_NOTICIA
	  * @author Techbot Solutions
	  * @param codAtualizacaoNoticia recebe um objeto do tipo int
	  * @return um int com o numero de itens removidos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int remover(int codAtualizacaoNoticia) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_ATUALIZACAO_NOTICIA "
				+ "WHERE CD_ATUALIZACAO_NOTICIA=?");
		stmt.setInt(1, codAtualizacaoNoticia);

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