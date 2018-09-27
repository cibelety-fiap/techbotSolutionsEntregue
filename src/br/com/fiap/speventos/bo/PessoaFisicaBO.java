package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Pessoa;
import br.com.fiap.speventos.beans.PessoaFisica;
import br.com.fiap.speventos.dao.PessoaFisicaDAO;

/**
 *  Classe para validar e padronizar dados para as tabelas 
 *  T_SGE_PESSOA_FISICA, T_SGE_PESSOA E T_SGE_USUARIO
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see PessoaFisicaDAO
 *  @see PessoaFisica
 */
public class PessoaFisicaBO {

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a insercao de uma nova PessoaFisica 
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * O cpf deve possuir 9 digitos
	 * O digito do cpf deve possuir 2 digitos 
	 * O rg deve possuir 9 digitos
	 * O digito do rg deve possuir 1 caracter que deve ser maiusculo, caso seja letra
	 * @author Techbot Solutions
	 * @param pessoaFisica recebe objetos do tipo Usuario, Pessoa, PessoaFisica
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String novoPessoaFisica(PessoaFisica pessoaFisica) throws Exception {

		if(pessoaFisica.getCodigoUsuario()<1 || pessoaFisica.getCodigoUsuario() > 99999) {
			return "Codigo invalido";
		}

		if(pessoaFisica.getCpf()<100000000 || pessoaFisica.getCpf()>999999999) {
			return "Cpf invalido";
		}

		if(pessoaFisica.getCpfDigito()<10 || pessoaFisica.getCpfDigito()>99) {
			return "Digito Cpf invalido";
		}

		pessoaFisica.setGenero(Character.toUpperCase(pessoaFisica.getGenero()));

		if(pessoaFisica.getGenero() != 'M' && pessoaFisica.getGenero() != 'F' && pessoaFisica.getGenero() != 'O') {
			return "Genero inv�lido";
		}

		if(pessoaFisica.getRg()<100000000 || pessoaFisica.getRg() > 999999999) {
			return "Rg invalido";
		}

		pessoaFisica.setRgDigito(Character.toUpperCase(pessoaFisica.getRgDigito()));

		PessoaFisicaDAO pfdao = new PessoaFisicaDAO();

		PessoaFisica resultado = pfdao.consultarPorCodigo(pessoaFisica.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			pfdao.fechar();
			return "Usuario ja existe";
		}

		String cadastroPessoa = PessoaBO.novoPessoa(
				new Pessoa(
						pessoaFisica.getCodigoUsuario(),
						pessoaFisica.getEmail(),
						pessoaFisica.getSenha(),
						pessoaFisica.getNome(),
						pessoaFisica.getTelefone(),
						pessoaFisica.getEndereco()));

		if(!cadastroPessoa.equals("1 registro cadastrado")) {
			pfdao.fechar();
			return "Erro no cadastro do usuario";
		}

		String retorno = pfdao.cadastrar(pessoaFisica) + "registro inserido";

		pfdao.fechar();
		return retorno;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a consulta de uma PessoaFisica por codigo
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * @param codigo recebe um int
	 * @return um construtor vazio
	 * @throws Exception - Chamada da excecao checked
	 */
	public static PessoaFisica consultaPessoaFisicaPorCodigo(int codigo) throws Exception{

		if(codigo <1 || codigo > 99999) {
			return new PessoaFisica();
		}

		PessoaFisicaDAO dao = new PessoaFisicaDAO();

		PessoaFisica retorno = dao.consultarPorCodigo(codigo);

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de pessoas fisicas por nome
	 * Regras de negocio validadas:
	 * O nome da pessoa fisica deve ter de 1 a 60 caracteres
	 * @param nomePf recebe um objeto do tipo String
	 * @return uma lista com objetos do tipo Colaborador 
	 * @throws Exception - Chamada da exce��o checked
	 */
	public static List<PessoaFisica> consultaPessoaFisicaPorNome(String nomePf) throws Exception{

		List<PessoaFisica> listaPessoaFisica = new ArrayList<PessoaFisica>();

		if(nomePf.isEmpty() || nomePf.length()>60) {
			return listaPessoaFisica;
		}

		nomePf = nomePf.toUpperCase();

		PessoaFisicaDAO dao = new PessoaFisicaDAO();

		listaPessoaFisica = dao.consultarPorNome(nomePf);

		dao.fechar();
		return listaPessoaFisica;
	}

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a edicao de uma PessoaFisica 
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * O cpf deve possuir 9 digitos
	 * O digito do cpf deve possuir 2 digitos 
	 * O rg deve possuir 9 digitos
	 * O digito do rg deve possuir 1 caracter que deve ser maiusculo, caso seja letra
	 * @author Techbot Solutions
	 * @param pessoaFisica recebe um objeto do tipo PessoaFisica
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String edicaoPessoaFisica(PessoaFisica pessoaFisica) throws Exception{

		if(pessoaFisica.getCodigoUsuario()<1 || pessoaFisica.getCodigoUsuario() > 99999) {
			return "Codigo invalido";
		}

		if(pessoaFisica.getCpf()<100000000 || pessoaFisica.getCpf()>999999999) {
			return "Cpf invalido";
		}

		if(pessoaFisica.getCpfDigito()<10 || pessoaFisica.getCpfDigito()>99) {
			return "Digito Cpf invalido";
		}

		pessoaFisica.setGenero(Character.toUpperCase(pessoaFisica.getGenero()));

		if(pessoaFisica.getGenero() != 'M' && pessoaFisica.getGenero() != 'F' && pessoaFisica.getGenero() != 'O') {
			return "Genero invalido";
		}

		if(pessoaFisica.getRg()<100000000 || pessoaFisica.getRg() > 999999999) {
			return "Rg invalido";
		}

		pessoaFisica.setRgDigito(Character.toUpperCase(pessoaFisica.getRgDigito()));

		PessoaFisicaDAO pfdao = new PessoaFisicaDAO();

		PessoaFisica resultado = pfdao.consultarPorCodigo(pessoaFisica.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			pfdao.fechar();
			return "Usuario ja existe";
		}

		String edicaoPessoa = PessoaBO.edicaoPessoa(
				new Pessoa(
						pessoaFisica.getCodigoUsuario(),
						pessoaFisica.getEmail(),
						pessoaFisica.getSenha(),
						pessoaFisica.getNome(),
						pessoaFisica.getTelefone(),
						pessoaFisica.getEndereco()));

		if(!edicaoPessoa.equals("1 registro cadastrado")) {
			pfdao.fechar();
			return "Erro na edicao do usuario";
		}
		String retorno = pfdao.editar(pessoaFisica) + "registro editado";

		pfdao.fechar();
		return retorno;
	}

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a remocao de uma pessoaFisica
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * @author Techbot Solutions
	 * @param codigoUsuario recebe um int
	 * @return uma String com a quantidade de registros removidos ou o erro ocorrido
	 * @throws Exception - Chamada da Exception
	 */
	public static String remocaoPessoaFisica(int codigoUsuario) throws Exception{

		if(codigoUsuario < 1 || codigoUsuario > 99999) {
			return "C�digo inv�lido";
		}

		PessoaFisicaDAO pfdao = new PessoaFisicaDAO();

		String remocaoUsuario = UsuarioBO.remocaoUsuario(codigoUsuario);

		if(!remocaoUsuario.equals("1 registro removido")) {
			pfdao.fechar();
			return "Erro na remocao do usuario";
		}

		String remocaoPessoa = PessoaBO.remocaoPessoa(codigoUsuario);

		if(!remocaoPessoa.equals("1 registro removido")) {
			pfdao.fechar();
			return "Erro na remocao da pessoa";
		}

		String retorno = pfdao.remover(codigoUsuario) + "registro removido";

		pfdao.fechar();
		return retorno;
	}

}
