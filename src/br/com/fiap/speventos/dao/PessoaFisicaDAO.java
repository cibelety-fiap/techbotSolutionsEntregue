package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.PessoaFisica;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_PESSOA_FISICA
 * Possui metodos para: abrir conexao, cadastrar, consultarPorCodigo, consultarPorNome, 
 * editar, remover, fechar conexao.
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see PessoaFisica
 *
 */
public class PessoaFisicaDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	  * Metodo construtor que estabelece a comunicacao com o banco de dados
	  * @author Techbot Solutions
	  * @throws Exception - Chamada da excecao Exception
	  */
	public PessoaFisicaDAO() throws Exception {
		con = new Conexao().conectar();
	}

	/**
	  * Metodo para adicionar um registro na tabela T_SGE_PESSOA_FISICA
	  * @author Techbot Solutions
	  * @param pf recebe um objeto do tipo PessoaFisica (Beans)
	  * @return um int com a quantidade de registros inseridos
	  * @throws Exception - Chamada da excecao Exception
	  */
	public int cadastrar(PessoaFisica pf) throws Exception {

		stmt = con.prepareStatement("INSERT INTO T_SGE_PESSOA_FISICA "
				+ "(CD_USUARIO, NR_CPF, NR_CPF_DIGITO, DS_GENERO, NR_RG, "
				+ "NR_RG_DIGITO, DT_NASCIMENTO) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)");

		stmt.setInt(1, pf.getCodigoUsuario());
		stmt.setLong(2, pf.getCpf());
		stmt.setInt(3, pf.getCpfDigito());
		stmt.setString(4, String.valueOf(pf.getGenero()));
		stmt.setInt(5, pf.getRg());
		stmt.setString(6, String.valueOf(pf.getRgDigito()));
		stmt.setString(7, pf.getDataNascimento());

		return stmt.executeUpdate();
	}

	/**
	  * Metodo para consultar por codigo de usuario 
	  * um registro na tabela T_SGE_USUARIO, T_SGE_PESSOA, T_SGE_PESSOA_FISICA
	  * @author Techbot Solutions
	  * @param codigoUsuario recebe um int
	  * @return um objeto PessoaFisica
	  * @throws Exception - Chamada da excecao Exception
	  */
	public PessoaFisica consultarPorCodigo(int codigoUsuario) throws Exception {

		stmt = con.prepareStatement(
				"SELECT * FROM T_SGE_USUARIO "
						+ "INNER JOIN T_SGE_PESSOA ON " 
						+ "(T_SGE_USUARIO.CD_USUARIO=T_SGE_PESSOA.CD_USUARIO) " 
						+ "INNER JOIN T_SGE_PESSOA_FISICA ON "
						+ "(T_SGE_PESSOA.CD_USUARIO=T_SGE_PESSOA_FISICA.CD_USUARIO) " 
						+ "WHERE T_SGE_PESSOA_FISICA.CD_USUARIO = ?");

		stmt.setInt(1, codigoUsuario);
		rs = stmt.executeQuery();

		if(rs.next()) {
			return new PessoaFisica(
					rs.getInt("CD_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_USUARIO"),
					rs.getLong("NR_TELEFONE"),
					rs.getString("DS_ENDERECO"),
					rs.getLong("NR_CPF"),
					rs.getInt("NR_CPF_DIGITO"),
					rs.getString("DS_GENERO").charAt(0),
					rs.getInt("NR_RG"),
					rs.getString("NR_RG_DIGITO").charAt(0),
					rs.getString("DT_NASCIMENTO")
					);
		} else {
			return new PessoaFisica();
		}
	}

	/**
	  * Metodo para consultar por nome de usuario 
	  * registros na tabela T_SGE_USUARIO, T_SGE_PESSOA, T_SGE_PESSOA_FISICA
	  * @author Techbot Solutions
	  * @param nomeUsuario recebe um int
	  * @return uma lista com objetos do tipo PessoaFisica
	  * @throws Exception - Chamada da excecao Exception
	  */
	public List<PessoaFisica> consultarPorNome(String nomeUsuario) throws Exception {

		List<PessoaFisica> listaPf = new ArrayList<PessoaFisica>();

		stmt = con.prepareStatement("SELECT * FROM T_SGE_USUARIO "
				+ "INNER JOIN T_SGE_PESSOA ON " 
				+ "(T_SGE_USUARIO.CD_USUARIO=T_SGE_PESSOA.CD_USUARIO) " 
				+ "INNER JOIN T_SGE_PESSOA_FISICA ON "
				+ "(T_SGE_PESSOA.CD_USUARIO=T_SGE_PESSOA_FISICA.CD_USUARIO) " 
				+ "WHERE T_SGE_PESSOA_FISICA.NM_USUARIO LIKE ?");

		stmt.setString(1, "%" + nomeUsuario + "%");

		while (rs.next()) {
			listaPf.add(new PessoaFisica(
					rs.getInt("CD_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DS_SENHA"),
					rs.getString("NM_USUARIO"),
					rs.getLong("NR_TELEFONE"),
					rs.getString("DS_ENDERECO"),
					rs.getLong("NR_CPF"),
					rs.getInt("NR_CPF_DIGITO"),
					rs.getString("DS_GENERO").charAt(0),
					rs.getInt("NR_RG"),
					rs.getString("NR_RG_DIGITO").charAt(0),
					rs.getString("DT_NASCIMENTO")
				)
			);
		}
		return listaPf;
	}

	/**
	 * Metodo para editar um registro na tabela T_SGE_PESSOA_FISICA
	 * @author Techbot Solutions
	 * @param pessoaFisica recebe um objeto do tipo PessoaFisica
	 * @return um int com a quantidade de registros editados
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int editar(PessoaFisica pessoaFisica) throws Exception {

		stmt = con.prepareStatement(
				"UPDATE T_SGE_PESSOA_FISICA SET CD_USUARIO = ?, NR_CPF=?, "
						+ "NR_CPF_DIGITO=?, DS_GENERO=?, NR_RG=?, "
						+ "NR_RG_DIGITO=? , DT_NASCIMENTO=? "
						+ "WHERE CD_USUARIO=?");

		stmt.setInt(1, pessoaFisica.getCodigoUsuario());
		stmt.setLong(2, pessoaFisica.getCpf());
		stmt.setInt(3, pessoaFisica.getCpfDigito());
		stmt.setString(4, String.valueOf(pessoaFisica.getGenero()));
		stmt.setInt(5, pessoaFisica.getRg());
		stmt.setString(6, String.valueOf(pessoaFisica.getRgDigito()));
		stmt.setString(7, pessoaFisica.getDataNascimento());
		stmt.setInt(8, pessoaFisica.getCodigoUsuario());

		return stmt.executeUpdate();
	}

	/**
	 * Metodo para remover um registro na tabela T_SGE_PESSOA_FISICA
	 * @author Techbot Solutions
	 * @param codUsuario recebe um int
	 * @return um int com o numero de itens removidos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public int remover(int codUsuario) throws Exception {
		stmt = con.prepareStatement("DELETE FROM T_SGE_PESSOA_FISICA "
				+ "WHERE CD_USUARIO = ?");
		stmt.setInt(1, codUsuario);

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

