package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.fiap.speventos.beans.Pessoa;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_PESSOA 
 * Possui metodos para: abrir conexao, cadastrar, 
 * editar, remover, fechar conexao.
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see Pessoa
 */
public class PessoaDAO {

	private Connection con;
	private PreparedStatement stmt;

	/**
	 * Metodo construtor que estabelece a comunicacao com o banco de dados
	 * @author Techbot Solutions
	 * @throws Exception - Chamada da excecao Exception
	 */
	public PessoaDAO() throws Exception {
		con = new Conexao().conectar();
	}

	/**
	 * Metodo para adicionar um registro na tabela T_SGE_PESSOA
	 * @author Techbot Solutions
	 * @param pessoa recebe um objeto do tipo Pessoa (Beans)
	 * @return um int com a quantidade de registros inseridos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int cadastrar(Pessoa pessoa) throws Exception {

		stmt = con.prepareStatement("INSERT INTO T_SGE_PESSOA "
				+ "(CD_USUARIO, NR_TELEFONE, DS_ENDERECO) VALUES (?, ?, ?)");

		stmt.setLong(1, pessoa.getCodigoUsuario());
		stmt.setLong(2, pessoa.getTelefone());
		stmt.setString(3, pessoa.getEndereco());

		return stmt.executeUpdate();
	}

	/**
	 * Metodo para editar um registro na tabela T_SGE_PESSOA
	 * @author Techbot Solutions
	 * @param pessoa recebe um objeto do tipo Pessoa
	 * @return um int com a quantidade de registros editados
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int editar(Pessoa pessoa) throws Exception {

		stmt = con.prepareStatement("UPDATE T_SGE_PESSOA "
				+ "SET CD_USUARIO=?, NR_TELEFONE=?, DS_ENDERECO=? "
				+ "WHERE CD_PESSOA=?");

		stmt.setLong(1, pessoa.getCodigoUsuario());
		stmt.setLong(2, pessoa.getTelefone());
		stmt.setString(3, pessoa.getEndereco());

		return stmt.executeUpdate();
	}

	/**
	 * Metodo para remover um registro na tabela T_SGE_PESSOA
	 * @author Techbot Solutions
	 * @param codigoUsuario recebe um int
	 * @return um int com o numero de itens removidos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int remover(int codigoUsuario) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_PESSOA "
				+ "WHERE CD_USUARIO=?");
		
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

