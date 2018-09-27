package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_LOCAL
 * Possui metodos para: abrir conexao, cadastrar, consultarPorCodigo, consultarPorNome,
 * editar, remover, fechar conexao.
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see Local
 *
 */
public class LocalDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	  * Metodo construtor que estabelece a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @throws Exception - Chamada da excecao Exception
	  */
	public LocalDAO() throws Exception {
		con = new Conexao().conectar();
	}
	
	/**
	  * Metodo para adicionar um registro na tabela T_SGE_LOCAL
	  * @author Techbot Solutions
	  * @param local recebe um objeto do tipo Local (Beans)
	  * @return um int com a quantidade de registros inseridos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int cadastrar(Local local) throws Exception {

		stmt = con.prepareStatement("INSERT INTO T_SGE_LOCAL " + 
				"(CD_LOCAL, NM_LOCAL, DS_ENDERECO) VALUES (?, ?, ?)");

		stmt.setInt(1, local.getCodigoLocal());
		stmt.setString(2, local.getNomeLocal());
		stmt.setString(3, local.getEnderecoLocal());

		return stmt.executeUpdate();
	}
	
	/**
	  * Metodo para consultar por codigo do local
	  * um registro na tabela T_SGE_LOCAL
	  * @author Techbot Solutions
	  * @param codLocal recebe um int
	  * @return um objeto do tipo Local
	  * @throws Exception - Chamada da excecao Exception
	  */
	public Local consultarPorCodigo(int codLocal) throws Exception {
		
		stmt = con.prepareStatement("SELECT CD_LOCAL, NM_LOCAL, DS_ENDERECO FROM T_SGE_LOCAL "
				+ "WHERE CD_LOCAL=?");
		
		stmt.setInt(1, codLocal);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Local(
				rs.getInt("CD_LOCAL"),
				rs.getString("NM_LOCAL"),
				rs.getString("DS_ENDERECO")
			);
		}
		
		return new Local();
	}
	
	/**
	  * Metodo para consultar por nome do local
	  * um registro na tabela T_SGE_LOCAL
	  * @author Techbot Solutions
	  * @param nomeLocal recebe um objeto do tipo String
	  * @return uma lista com objetos do tipo Local
	  * @throws Exception - Chamada da excecao Exception
	  */
	public List<Local> consultarPorNome(String nomeLocal) throws Exception {
		List<Local> listaLocal = new ArrayList<Local>();
		
		stmt = con.prepareStatement("SELECT CD_LOCAL, NM_LOCAL, DS_ENDERECO FROM T_SGE_LOCAL "
				+ "WHERE UPPER(NM_LOCAL) LIKE ?");
		stmt.setString(1, "%" + nomeLocal + "%");
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			listaLocal.add(
				new Local(
					rs.getInt("CD_LOCAL"),
					rs.getString("NM_LOCAL"),
					rs.getString("DS_ENDERECO")
				)
			);
		}
		
		return listaLocal;
	}
	
	/**
	  * Metodo para editar um registro na tabela T_SGE_LOCAL
	  * @author Techbot Solutions
	  * @param local recebe um objeto do tipo Local
	  * @return um int com a quantidade de registros editados
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int editar(Local local) throws Exception {
		stmt = con.prepareStatement("UPDATE T_SGE_LOCAL "
				+ "SET CD_LOCAL=?, NM_LOCAL=?, DS_ENDERECO=? "
				+ "WHERE CD_LOCAL=?");
		
		stmt.setInt(1, local.getCodigoLocal());
		stmt.setString(2, local.getNomeLocal());
		stmt.setString(3, local.getEnderecoLocal());
		stmt.setInt(4, local.getCodigoLocal());
		
		return stmt.executeUpdate();
	}
	
	/**
	  * Metodo para remover um registro na tabela T_SGE_LOCAL
	  * @author Techbot Solutions
	  * @param codLocal recebe um objeto do tipo int
	  * @return um int com o numero de itens removidos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int remover(int codLocal) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_LOCAL "
				+ "WHERE CD_LOCAL=?");
		stmt.setInt(1, codLocal);
		
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
	  * Metodo para calcular o proximo codigo de local a ser inserido,
	  * a solucao foi proposta pela Profa. Rita como alternativa (temporaria) ao auto-increment
	  * @author Techbot Solutions
	  * @return um int com o numero do proximo codigo de local a ser inserido
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int calcularCodLocal() throws Exception {
		int proxCodigo = 0;
		int qtCodLocal = 0;
		
		stmt = con.prepareStatement("SELECT COUNT(CD_LOCAL) FROM T_SGE_LOCAL");

		rs = stmt.executeQuery();
		if (rs.next()) {
			qtCodLocal= rs.getInt("COUNT(CD_LOCAL)");
			if (qtCodLocal == 0) {
				proxCodigo = 1;
			} else {			
				stmt = con.prepareStatement("SELECT MAX(CD_LOCAL) FROM T_SGE_LOCAL");
				rs = stmt.executeQuery();
				if (rs.next()) {
					proxCodigo = rs.getInt("MAX(CD_LOCAL)") + 1;
				}
			}
		}
		return proxCodigo;
	}
}
