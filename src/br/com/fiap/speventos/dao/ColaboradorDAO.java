package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Colaborador;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_COLABORADOR
 * Possui metodos para: abrir conexao, cadastrar, consultarPorCodigo, consultarPorNome, 
 * editar, remover, fechar conexao.
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see Colaborador
 */
public class ColaboradorDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	 * Metodo construtor que estabelece a comunicacao com o banco de dados
	 * @author Techbot Solutions
	 * @throws Exception - Chamada da excecao Exception
	 */
	public ColaboradorDAO() throws Exception {
		con = new Conexao().conectar();
	}

	/**
	 * Metodo para adicionar um registro na tabela T_SGE_COLABORADOR
	 * @author Techbot Solutions
	 * @param colaborador recebe um objeto do tipo Colaborador (Beans)
	 * @return um int com a quantidade de registros inseridos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int cadastrar(Colaborador colaborador) throws Exception {

		stmt = con.prepareStatement("INSERT INTO T_SGE_COLABORADOR "
				+ "(DS_NIVEL_ACESSO, DS_DEPARTAMENTO) VALUES (?, ?)");

		stmt.setString(1, colaborador.getNivelAcesso());
		stmt.setString(2, colaborador.getDepartamento());

		return stmt.executeUpdate();
	}

	/**
	 * Metodo para consultar por codigo de usuario 
	 * um registro na tabela T_SGE_USUARIO, T_SGE_COLABORADOR
	 * @author Techbot Solutions
	 * @param codigoUsuario recebe um int
	 * @return um objeto Colaborador
	 * @throws Exception - Chamada da excecao Exception
	 */
	public Colaborador consultarPorCodigo(int codigoUsuario) throws Exception {

		stmt = con.prepareStatement(
				"SELECT * FROM T_SGE_USUARIO "
						+ "INNER JOIN T_SGE_COLABORADOR ON "
						+ "(T_SGE_USUARIO.CD_USUARIO=T_SGE_COLABORADOR.CD_USUARIO) "
						+ "WHERE T_SGE_COLABORADOR.CD_USUARIO=?");

		stmt.setInt(1, codigoUsuario);

		rs = stmt.executeQuery();

		if(rs.next()) {
			return new Colaborador(
					rs.getInt("CD_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_USUARIO"),
					rs.getString("DS_NIVEL_ACESSO"),
					rs.getString("DS_DEPARTAMENTO")
				);
		} else {
			return new Colaborador();
		}
	}

	/**
	 * Metodo para consultar por nome de usuario 
	 * registros na tabela T_SGE_USUARIO, T_SGE_COLABORADOR
	 * @author Techbot Solutions
	 * @param nomeUsuario recebe um objeto do tipo String
	 * @return uma lista com objetos do tipo Colaborador
	 * @throws Exception - Chamada da excecao Exception
	 */
	public List<Colaborador> consultarPorNome(String nomeUsuario) throws Exception {

		List<Colaborador> listaColaborador = new ArrayList<Colaborador>();

		stmt = con.prepareStatement("SELECT * T_SGE_USUARIO "
				+ "INNER JOIN T_SGE_COLABORADOR ON "
				+ "(T_SGE_USUARIO.CD_USUARIO = T_SGE_COLABORADOR.CD_USUARIO) "
				+ "WHERE T_SGE_COLABORADOR.NM_USUARIO LIKE ?");

		stmt.setString(1, "%" + nomeUsuario + "%");

		while (rs.next()) {
			listaColaborador.add(new Colaborador(
					rs.getInt("CD_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_USUARIO"),
					rs.getString("DS_NIVEL_ACESSO"),
					rs.getString("DS_DEPARTAMENTO")
				)
			);
		}
		return listaColaborador;
	}

	/**
	 * Metodo para editar um registro na tabela T_SGE_COLABORADOR
	 * @author Techbot Solutions
	 * @param colaborador recebe um objeto do tipo Colaborador
	 * @return um int com a quantidade de registros editados
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int editar(Colaborador colaborador) throws Exception {

		stmt = con.prepareStatement("UPDATE T_SGE_COLABORADOR "
				+ "SET DS_NIVEL_ACESSO=?, DS_DEPARTAMENTO=?"
				+ "WHERE CD_USUARIO=?");

		stmt.setString(1, colaborador.getNivelAcesso());
		stmt.setString(2, colaborador.getDepartamento());
		stmt.setInt(3, colaborador.getCodigoUsuario());

		return stmt.executeUpdate();
	}
	
	/**
	 * Metodo para remover um registro na tabela T_SGE_COLABORADOR
	 * @author Techbot Solutions
	 * @param codigoUsuario recebe um int
	 * @return um int com o numero de itens removidos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int remover(int codigoUsuario) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_COLABORADOR "
				+ "WHERE CD_USUARIO = ?");
		stmt.setInt(1, codigoUsuario);

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