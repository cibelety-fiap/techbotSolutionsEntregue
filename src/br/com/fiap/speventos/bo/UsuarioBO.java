package br.com.fiap.speventos.bo;

import br.com.fiap.speventos.beans.Usuario;
import br.com.fiap.speventos.dao.UsuarioDAO;

/**
 *  Classe para validar e padronizar dados para a tabela T_SGE_USUARIO
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see UsuarioDAO
 *  @see Usuario
 */
public class UsuarioBO {

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a insercao de um novo usuario 
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * O nome do usuario deve ter entre 1 e 60 caracteres
	 * O email do usuario deve ter entre 1 e 60 caracteres e deve conter '@' e '.'
	 * A senha deve ter entre 8 e 20 digitos
	 * @author Techbot Solutions
	 * @param usuario recebe um objeto do tipo Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String novoUsuario(Usuario usuario) throws Exception{

		if(usuario.getCodigoUsuario()<1 || usuario.getCodigoUsuario()>99999) {
			return "Codigo invalido";
		}

		if(usuario.getNome().isEmpty() || usuario.getNome().length()>60) {
			return "Nome invalido";
		}

		if(usuario.getEmail().isEmpty() || usuario.getEmail().length()>60) {
			return "Email invalido";
		}

		if(usuario.getEmail().indexOf("@")<0 || usuario.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(usuario.getSenha().length()<8 || usuario.getSenha().length()>20) {
			return "Senha invalida";
		}

		usuario.setNome(usuario.getNome().toUpperCase());
		usuario.setEmail(usuario.getEmail().toUpperCase());

		UsuarioDAO dao = new UsuarioDAO();
	
		String retorno = dao.cadastrar(usuario) + " registro inserido";
		dao.fechar();
		return retorno;
	}



	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a edicao de um usuario 
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * O nome do usuario deve ter entre 1 e 60 caracteres
	 * O email do usuario deve ter entre 1 e 60 caracteres e deve conter '@' e '.'
	 * A senha deve ter entre 8 e 20 digitos
	 * @author Techbot Solutions
	 * @param usuario recebe um objeto do tipo Usuario
	 * @return uma String com a quantidade de registros editados ou o erro ocorrido
	 * @throws Exception - Chamada da Exception
	 */
	public static String edicaoUsuario(Usuario usuario) throws Exception{

		if(usuario.getCodigoUsuario()<1 || usuario.getCodigoUsuario()>99999) {
			return "Codigo invalido";
		}

		if(usuario.getNome().isEmpty() || usuario.getNome().length()>60) {
			return "Nome invalido";
		}

		if(usuario.getEmail().isEmpty() || usuario.getEmail().length()>60) {
			return "Email invalido";
		}

		if(usuario.getEmail().indexOf("@")<0 || usuario.getEmail().indexOf(".")<0) {
			return "Email invalido";
		}

		if(usuario.getSenha().length()<8 || usuario.getSenha().length()>20) {
			return "Senha invalida";
		}

		usuario.setNome(usuario.getNome().toUpperCase());
		usuario.setEmail(usuario.getEmail().toUpperCase());


		UsuarioDAO dao = new UsuarioDAO();

		String retorno = dao.editar(usuario) + " registro editado";
		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a remocao de um usuario 
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * @author Techbot Solutions
	 * @param codigoUsuario recebe um int
	 * @return uma String com a quantidade de registros removidos ou o erro ocorrido
	 * @throws Exception - Chamada da Exception
	 */
	public static String remocaoUsuario(int codigoUsuario) throws Exception{

		if (codigoUsuario < 1 || codigoUsuario > 99999) {
			return "Codigo de usuario invalido";
		}

		UsuarioDAO dao = new UsuarioDAO();

		String retorno = dao.remover(codigoUsuario) + " registro removido";

		dao.fechar();
		return retorno;
	}
	
	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas ao login de um usuario 
	 * Regras de negocio validadas:
	 * O nome do usuario deve ter entre 1 e 60 caracteres
	 * O email do usuario deve ter entre 1 e 60 caracteres e deve conter '@' e '.'
	 * A senha deve ter entre 8 e 20 digitos	 * @author Techbot Solutions
	 * @param email recebe uma String
	 * @param senha recebe uma String
	 * @return um objeto do tipo Usuario
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static Usuario login(String email, String senha) throws Exception{

		if(email.isEmpty() || email.length()>60) {
			return new Usuario();
		}

		if(email.indexOf("@")<0 || email.indexOf(".")<0) {
			return new Usuario();
		}

		if(senha.length()<4 || senha.length()>20) {
			return new Usuario();
		}

		email = email.toUpperCase();
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.logar(email, senha);
		dao.fechar();

		return usuario;
	}
}
