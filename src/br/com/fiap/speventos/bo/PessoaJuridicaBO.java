package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Pessoa;
import br.com.fiap.speventos.beans.PessoaJuridica;
import br.com.fiap.speventos.dao.PessoaJuridicaDAO;

/**
 *  Classe para validar e padronizar dados para as tabelas
 *  T_SGE_PESSOA_JURIDICA, T_SGE_PESSOA e T_SGE_USUARIO
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see PessoaJuridicaDAO
 *  @see PessoaJuridica
 */
public class PessoaJuridicaBO {

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a insercao de uma nova PessoaJuridica 
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * A razao social deve ter entre 1 e 60 caracteres, 
	 * O cnpj deve possuir 12 digitos
	 * O digito do cnpj deve possuir 2 digitos 
	 * @author Techbot Solutions
	 * @param pessoaJuridica recebe objetos do tipo PessoaJuridica, Pessoa e Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String novoPessoaJuridica(PessoaJuridica pessoaJuridica) throws Exception {

		if(pessoaJuridica.getCodigoUsuario() <1 || pessoaJuridica.getCodigoUsuario() > 99999) {
			return "Codigo invalido";
		}

		if(pessoaJuridica.getRazaoSocial().isEmpty() || pessoaJuridica.getRazaoSocial().length()>60) {
			return "Razao social invalida";
		}

		if(pessoaJuridica.getCnpj() < 100000000000L || pessoaJuridica.getCnpj() > 999999999999L) {
			return "Cnpj invalido";
		}

		if(pessoaJuridica.getCnpjDigito() < 10 || pessoaJuridica.getCnpjDigito() > 99) {
			return "Cnpj invalido";
		}

		pessoaJuridica.setRazaoSocial((pessoaJuridica.getRazaoSocial().toUpperCase()));
		pessoaJuridica.setEndereco(pessoaJuridica.getEndereco().toUpperCase());

		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();

		PessoaJuridica resultado = pjdao.consultarPorCodigo(pessoaJuridica.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			pjdao.fechar();
			return "Usuário já existe";
		}

		String cadastroPessoa = PessoaBO.novoPessoa(
				new Pessoa(
						pessoaJuridica.getCodigoUsuario(), 
						pessoaJuridica.getEmail(),
						pessoaJuridica.getSenha(), 
						pessoaJuridica.getNome(),
						pessoaJuridica.getTelefone(),
						pessoaJuridica.getEndereco()));
		if(!cadastroPessoa.equals("1 registro cadastrado")) {
			pjdao.fechar();
			return "Erro no cadastro do usuario";
		}

		String retorno = pjdao.cadastrar(pessoaJuridica) + "registro inserido";

		pjdao.fechar();
		return retorno + "registro inserido";
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de uma PessoaJuridica por codigo
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * @param codigo recebe um int
	 * @return um construtor vazio
	 * @throws Exception - Chamada da excecao checked
	 */
	public static PessoaJuridica consultaPessoaJuridicaPorCodigo(int codigo) throws Exception{

		if(codigo <1 || codigo > 99999) {
			return new PessoaJuridica();
		}

		PessoaJuridicaDAO dao = new PessoaJuridicaDAO();

		PessoaJuridica retorno = dao.consultarPorCodigo(codigo);

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de PessoaJuridica por nome
	 * Regras de negocio validadas:
	 * O nome do colaborador deve ter de 1 a 60 caracteres
	 * @param nomePj recebe um objeto do tipo String
	 * @return uma lista com objetos do tipo Colaborador 
	 * @throws Exception - Chamada da exceção checked
	 */
	public static List<PessoaJuridica> consultaPessoaJuridicaPorNome(String nomePj) throws Exception{

		List<PessoaJuridica> listaPessoaJuridica = new ArrayList<PessoaJuridica>();

		if(nomePj.isEmpty() || nomePj.length()>60) {
			return listaPessoaJuridica;
		}

		nomePj = nomePj.toUpperCase();

		PessoaJuridicaDAO dao = new PessoaJuridicaDAO();

		listaPessoaJuridica = dao.consultarPorNome(nomePj);

		dao.fechar();
		return listaPessoaJuridica;
	}

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a edicao de uma PessoaJuridica
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * A razao social deve ter entre 1 e 60 caracteres, 
	 * O cnpj deve possuir 12 digitos
	 * O digito do cnpj deve possuir 2 digitos 
	 * @author Techbot Solutions
	 * @param pessoaJuridica recebe um objeto do tipo PessoaJuridica
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String edicaoPessoaJuridica(PessoaJuridica pessoaJuridica) throws Exception{

		if(pessoaJuridica.getCodigoUsuario() <1 || pessoaJuridica.getCodigoUsuario() > 99999) {
			return "Codigo invalido";
		}

		if(pessoaJuridica.getRazaoSocial().isEmpty() || pessoaJuridica.getRazaoSocial().length()>60) {
			return "Razao social invalida";
		}

		if(pessoaJuridica.getCnpj() < 100000000000L || pessoaJuridica.getCnpj() > 999999999999L) {
			return "Cnpj invalido";
		}

		if(pessoaJuridica.getCnpjDigito() < 10 || pessoaJuridica.getCnpjDigito() > 99) {
			return "Cnpj invalido";
		}

		pessoaJuridica.setRazaoSocial((pessoaJuridica.getRazaoSocial().toUpperCase()));
		pessoaJuridica.setEndereco(pessoaJuridica.getEndereco().toUpperCase());

		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();

		PessoaJuridica resultado = pjdao.consultarPorCodigo(pessoaJuridica.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			pjdao.fechar();
			return "Usuário já existe";
		}

		String edicaoPessoa = PessoaBO.edicaoPessoa(
				new Pessoa(
						pessoaJuridica.getCodigoUsuario(), 
						pessoaJuridica.getEmail(),
						pessoaJuridica.getSenha(), 
						pessoaJuridica.getNome(),
						pessoaJuridica.getTelefone(),
						pessoaJuridica.getEndereco()));
		if(!edicaoPessoa.equals("1 registro cadastrado")) {
			pjdao.fechar();
			return "Erro no cadastro do usuario";
		}

		String retorno =  pjdao.editar(pessoaJuridica) + "registro editado";

		pjdao.fechar();
		return retorno;
	}

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a remocao de uma pessoaJuridica
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * @author Techbot Solutions
	 * @param codigoUsuario recebe um int
	 * @return uma String com a quantidade de registros removidos ou o erro ocorrido
	 * @throws Exception - Chamada da Exception
	 */
	public static String remocaoPessoaJuridica(int codigoUsuario) throws Exception{

		if(codigoUsuario < 1 || codigoUsuario > 99999) {
			return "Codigo invalido";
		}

		PessoaJuridicaDAO pjdao = new PessoaJuridicaDAO();

		String remocaoUsuario = UsuarioBO.remocaoUsuario(codigoUsuario);
		if(!remocaoUsuario.equals("1 registro removido")) {
			pjdao.fechar();
			return "Erro na remocao do usuario";
		}

		String remocaoPessoa = PessoaBO.remocaoPessoa(codigoUsuario);

		if(!remocaoPessoa.equals("1 registro removido")) {
			pjdao.fechar();
			return "Erro na remocao da pessoa";
		}

		String retorno = pjdao.remover(codigoUsuario) + " registro removido";

		pjdao.fechar();
		return retorno;
	}
}
