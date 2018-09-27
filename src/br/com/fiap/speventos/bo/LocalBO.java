package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.dao.LocalDAO;

/**
 * Classe para validar e padronizar dados para a tabela T_SGE_LOCAL
 * @version 1.0
 * @since 1.0
 * @author Techbot Solutions
 * @see Local
 * @see LocalDAO
 *
 */
public class LocalBO {

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a insercao de um novo Local
	 * Regras de negocio validadas:
	 * O codigo do local deve ter entre 1 a 5 digitos
	 * O nome do local deve ter de 1 a 60 caracteres
	 * O endereco do local deve ter de 1 a 100 caracteres
	 * O codigo do local nao pode ser cadastrado caso ja exista o banco
	 * @author Techbot Solutions
	 * @param local recebe um objeto do tipo Local (Beans)
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception 
	 *
	 */
	public static String novoLocal(Local local) throws Exception {
		
		if (local.getCodigoLocal() < 1 || local.getCodigoLocal() > 99999) {
			return "Codigo do local invalido";
		}	
		if (local.getNomeLocal().isEmpty() || local.getNomeLocal().length() > 60) {
			return "Nome do local invalido";
		}
		if (local.getEnderecoLocal().isEmpty() || local.getEnderecoLocal().length() > 100) {
			return "Endereco do local invalido";
		}
		
		LocalDAO dao = new LocalDAO();

		Local localRepetido = LocalBO.consultaLocalPorCodigo(local.getCodigoLocal());
		
		if (localRepetido.getCodigoLocal() > 0) {
			return "local ja existe";
		}
		
		local.setNomeLocal(local.getNomeLocal().toUpperCase());
		local.setEnderecoLocal(local.getEnderecoLocal().toUpperCase());
		
		int retorno = dao.cadastrar(local);
		dao.fechar();
		
		return retorno + " registro cadastrado";
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de um local por codigo
	 * Regras de negocio validadas:
	 * O codigo do local deve ter entre 1 a 5 digitos
	 * @param codLocal recebe um int
	 * @return um construtor vazio
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static Local consultaLocalPorCodigo(int codLocal) throws Exception {
		
		if (codLocal < 1 || codLocal > 99999) {
			return new Local();
		}
		
		LocalDAO dao = new LocalDAO();
		
		Local retorno = dao.consultarPorCodigo(codLocal);
		dao.fechar();
		
		return retorno; 
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de um Local por nome
	 * Regras de negocio validadas:
	 * O nome do local deve ter de 1 a 60 caracteres
	 * @param nomeLocal recebe um objeto do tipo String
	 * @return uma lista com objetos do tipo Local 
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static List<Local> consultaLocalPorNome(String nomeLocal) throws Exception {

		List<Local> listaLocal = new ArrayList<Local>();

		if (nomeLocal.isEmpty() || nomeLocal.length() > 60 ) {
			return listaLocal;
		}
		
		nomeLocal = nomeLocal.toUpperCase();
		
		LocalDAO dao = new LocalDAO();
		listaLocal = dao.consultarPorNome(nomeLocal);
		
		dao.fechar();		
		
		return listaLocal; 
		
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a edicao do Local
	 * Regras de negocio validadas:
	 * O codigo do local deve ter entre 1 a 5 digitos
	 * O nome do local deve ter de 1 a 60 caracteres
	 * O endereco do local deve ter de 1 a 100 caracteres
	 * @param local recebe um objeto do tipo Local
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String edicaoLocal(Local local) throws Exception {
		
		if (local.getCodigoLocal() < 1 || local.getCodigoLocal() > 99999) {
			return "Codigo do local invalido";
		}	
		if (local.getNomeLocal().isEmpty() || local.getNomeLocal().length() > 60) {
			return "Nome do local invalido";
		}
		if (local.getEnderecoLocal().isEmpty() || local.getEnderecoLocal().length() > 100) {
			return "Endereco do local invalido";
		}
		
		LocalDAO dao = new LocalDAO();
		
		local.setNomeLocal(local.getNomeLocal().toUpperCase());
		local.setEnderecoLocal(local.getEnderecoLocal().toUpperCase());
		
		int retorno = dao.editar(local);
		dao.fechar();
		
		return retorno + " registro editado";
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a remocao do Local
	 * Regras de negocio validadas:
	 * O codigo do local deve ter entre 1 a 5 digitos
	 * @param codLocal recebe um int
	 * @return uma String com o numero de registros removidos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String remocaoLocal(int codLocal) throws Exception {
		
		if (codLocal < 1 || codLocal > 99999) {
			return "Codigo do local invalido";
		}

		
		LocalDAO dao = new LocalDAO();
		
		int retorno = dao.remover(codLocal);
		dao.fechar();
		
		return retorno + "registro removido";
	}
	
	/**
	 * Metodo para chamar o calculo do proximo codigo de local
	 * @author Techbot Solutions
	 * @return um int com o numero do proximo codigo de local
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static int consultaProxCodLocal() throws Exception {
		LocalDAO dao = new LocalDAO();
		int retorno = dao.calcularCodLocal();
		dao.fechar();
		return retorno;
	}
}
