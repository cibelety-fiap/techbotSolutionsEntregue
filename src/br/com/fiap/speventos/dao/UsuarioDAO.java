package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.speventos.beans.Usuario;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_USUARIO 
 * Possui metodos para: abrir conexao, cadastrar, 
 * editar, logar usuario e remover, fechar conexao.
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see Usuario
 */
public class UsuarioDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	 * Metodo construtor que estabelece a comunicacao com o banco de dados
	 * @author Techbot Solutions
	 * @throws Exception - Chamada da excecao Exception
	 */
	public UsuarioDAO() throws Exception {
		con = new Conexao().conectar();
	}

	/**
	 * Metodo para adicionar um registro na tabela T_SGE_USUARIO
	 * @author Techbot Solutions
	 * @param usuario recebe um objeto do tipo Usuario (Beans)
	 * @return um int com a quantidade de registros inseridos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int cadastrar(Usuario usuario) throws Exception {

		stmt = con.prepareStatement("INSERT INTO T_SGE_USUARIO "
				+ "(CD_USUARIO, DS_EMAIL, DS_SENHA, NM_USUARIO)"
				+ " VALUES (?, ?, ?, ?)");

		stmt.setInt(1, usuario.getCodigoUsuario());
		stmt.setString(2, usuario.getEmail());
		stmt.setString(3, usuario.getSenha());
		stmt.setString(4,usuario.getNome());

		return stmt.executeUpdate();
	}

	/**
	 * Metodo para editar um registro na tabela T_SGE_USUARIO
	 * @author Techbot Solutions
	 * @param usuario recebe um objeto do tipo Usuario
	 * @return um int com a quantidade de registros editados
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int editar(Usuario usuario) throws Exception {

		stmt = con.prepareStatement("UPDATE T_SGE_USUARIO SET "
				+ "CD_USUARIO=?, DS_EMAIL=?, "
				+ "DS_SENHA=?, NM_USUARIO=? "
				+ "WHERE CD_USUARIO=?");

		stmt.setInt(1, usuario.getCodigoUsuario());
		stmt.setString(2, usuario.getEmail());
		stmt.setString(3, usuario.getSenha());
		stmt.setString(4,usuario.getNome());
		stmt.setInt(5, usuario.getCodigoUsuario());
		
		return stmt.executeUpdate();
	}



	/**
	 * Metodo para remover um registro na tabela T_SGE_USUARIO
	 * @author Techbot Solutions
	 * @param codigoUsuario recebe um int
	 * @return um int com o numero de itens removidos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int remover(int codigoUsuario) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_USUARIO "
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
	
	/**
	 * Metodo para logar o usuario, consulta por email e senha
	 * um registro na tabela T_SGE_USUARIO 
	 * @author Techbot Solutions
	 * @param email recebe uma String
	 * @param senha recebe uma String
	 * @return um objeto do tipo Usuario
	 * @throws Exception - Chamada da excecao Exception
	 */
	public Usuario logar(String email, String senha) throws Exception {
//		System.out.println(email + " " + senha);
		stmt = con.prepareStatement(
				"SELECT CD_USUARIO, DS_EMAIL, DS_SENHA, NM_USUARIO "
				+ "FROM T_SGE_USUARIO "
				+ "WHERE DS_EMAIL=? AND DS_SENHA=?");

		stmt.setString(1, email);
		stmt.setString(2, senha);

		rs = stmt.executeQuery();

		if(rs.next()) {
			return new Usuario(
					rs.getInt("CD_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_USUARIO")
					);
		} else {
			return new Usuario();
		}
	}
}
