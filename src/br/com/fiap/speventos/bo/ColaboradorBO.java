package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Colaborador;
import br.com.fiap.speventos.beans.Usuario;
import br.com.fiap.speventos.dao.ColaboradorDAO;

/**
 *  Classe para validar e padronizar dados para a tabela T_SGE_COLABORADOR e T_SGE_USUARIO
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see ColaboradorDAO
 *  @see Colaborador
 */
public class ColaboradorBO {

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a insercao de um novo Colaborador 
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * O nivel de acesso deve ter entre 1 e 30 caracteres
	 * O nome do departamento deve ter entre 1 e 60 caracteres
	 * @author Techbot Solutions
	 * @param colaborador recebe objetos do tipo Colaborador e Usuario
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String novoColaborador(Colaborador colaborador) throws Exception{

		if(colaborador.getCodigoUsuario()<1 || colaborador.getCodigoUsuario() > 99999) {
			return "Codigo invalido";
		}
		
		if(colaborador.getNivelAcesso().isEmpty() || colaborador.getNivelAcesso().length()>30) {
			return "Nivel de acesso invalido";
		}

		if(colaborador.getDepartamento().isEmpty() || colaborador.getDepartamento().length()>60) {
			return "Departamento invalido";
		}

		colaborador.setNivelAcesso(colaborador.getNivelAcesso().toUpperCase());
		colaborador.setDepartamento(colaborador.getDepartamento().toUpperCase());

		ColaboradorDAO dao = new ColaboradorDAO();

		Colaborador resultado = dao.consultarPorCodigo(colaborador.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			dao.fechar();
			return "Usuario ja existe";
		}

		String cadastroUsuario = UsuarioBO.novoUsuario(
				new Usuario(
						colaborador.getCodigoUsuario(), 
						colaborador.getEmail(),
						colaborador.getSenha(), 
						colaborador.getNome()));
		if(!cadastroUsuario.equals("1 registro cadastrado")) {
			dao.fechar();
			return "Erro no cadastro do usuario";
		}
		
		String retorno = dao.cadastrar(colaborador) + " registro inserido";

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de um Colaborador por codigo
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * @param codigoColaborador recebe um int
	 * @return um construtor vazio
	 * @throws Exception - Chamada da excecao checked
	 */
	public static Colaborador consultaColaboradorPorCodigo(int codigoColaborador) throws Exception {

		if(codigoColaborador < 1 || codigoColaborador > 99999) {
			return new Colaborador();
		}
		
		ColaboradorDAO dao = new ColaboradorDAO();

		Colaborador retorno = dao.consultarPorCodigo(codigoColaborador);

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de colaboradores por nome
	 * Regras de negocio validadas:
	 * O nome do colaborador deve ter de 1 a 60 caracteres
	 * @param nomeColaborador recebe um objeto do tipo String
	 * @return uma lista com objetos do tipo Colaborador 
	 * @throws Exception - Chamada da exceção checked
	 */

	public static List<Colaborador> consultaColaboradorPorNome(String nomeColaborador) throws Exception {

		List<Colaborador> listaColaborador = new ArrayList<Colaborador>();

		if(nomeColaborador.isEmpty() || nomeColaborador.length()> 60) {
			return listaColaborador;
		}

		nomeColaborador = nomeColaborador.toUpperCase();

		ColaboradorDAO dao = new ColaboradorDAO();

		listaColaborador = dao.consultarPorNome(nomeColaborador);

		dao.fechar();
		return listaColaborador;
	}
	
	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a edicao de um Colaborador 
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * O nivel de acesso deve ter entre 1 e 30 caracteres
	 * O nome do departamento deve ter entre 1 e 60 caracteres
	 * @author Techbot Solutions
	 * @param colaborador recebe um objeto do tipo Colaborador
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String edicaoColaborador(Colaborador colaborador) throws Exception{

		if(colaborador.getCodigoUsuario()<1 || colaborador.getCodigoUsuario() > 99999) {
			return "Codigo invalido";
		}
		
		if(colaborador.getNivelAcesso().isEmpty() || colaborador.getNivelAcesso().length()>30) {
			return "Nivel de acesso invalido";
		}

		if(colaborador.getDepartamento().isEmpty() || colaborador.getDepartamento().length()>60) {
			return "Departamento invalido";
		}

		colaborador.setNivelAcesso(colaborador.getNivelAcesso().toUpperCase());
		colaborador.setDepartamento(colaborador.getDepartamento().toUpperCase());


		ColaboradorDAO dao = new ColaboradorDAO();

		Colaborador resultado = dao.consultarPorCodigo(colaborador.getCodigoUsuario());

		if(resultado.getCodigoUsuario()>0) {
			dao.fechar();
			return "Usuario ja existe";
		}

		String edicaoUsuario = UsuarioBO.edicaoUsuario(
				new Usuario(
						colaborador.getCodigoUsuario(), 
						colaborador.getEmail(),
						colaborador.getSenha(), 
						colaborador.getNome()));
		if(!edicaoUsuario.equals("1 registro editado")) {
			dao.fechar();
			return "Erro na edicao do usuario";
		}
		String retorno = dao.editar(colaborador) + " registro inserido";

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo para verificar regras de negocio, validacoes e padronizacoes 
	 * relacionadas a remocao de um colaborador
	 * Regras de negocio validadas:
	 * O codigo do usuario deve ter entre 1 a 5 digitos
	 * @author Techbot Solutions
	 * @param codigoUsuario recebe um int
	 * @return uma String com a quantidade de registros removidos ou o erro ocorrido
	 * @throws Exception - Chamada da Exception
	 */
	public static String remocaoColaborador(int codigoUsuario) throws Exception{

		if(codigoUsuario < 1 || codigoUsuario > 99999) {
			return "Codigo invalido";
		}

		ColaboradorDAO dao = new ColaboradorDAO();
		
		String remocaoUsuario = UsuarioBO.remocaoUsuario(codigoUsuario);
		if(!remocaoUsuario.equals("1 registro removido")) {
			dao.fechar();
			return "Erro na remocao do usuario";
		}
		String retorno = dao.remover(codigoUsuario) + " registro removido";

		dao.fechar();
		return retorno;
	}
}