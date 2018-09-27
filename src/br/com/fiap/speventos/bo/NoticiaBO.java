package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Noticia;
import br.com.fiap.speventos.dao.NoticiaDAO;

/**
 * Classe para validar e padronizar dados para a tabela T_SGE_NOTICIA
 * @version 1.0
 * @since 1.0
 * @author Techbot Solutions
 * @see Noticia
 * @see NoticiaDAO
 *
 */

public class NoticiaBO {
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a insercao de uma nova Noticia
	 * Regras de negocio validadas:
	 * O codigo da noticia deve ter entre 1 a 5 digitos
	 * O endereco do link da imagem deve ter de 1 a 50 caracteres
	 * O nome da noticia deve ter de 1 a 80 caracteres
	 * A categoria da noticia deve ter de 1 a 30 caracteres
	 * a data da atualizacao de noticia deve ser valida,
	 * A noticia deve ter de 1 a 2000 caracteres
	 * O codigo da noticia nao pode ser cadastrado caso ja exista o banco
	 * @author Techbot Solutions
	 * @param noticia recebe um objeto do tipo Noticia (Beans)
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception 
	 *
	 */

	public static String novaNoticia(Noticia noticia) throws Exception {
		
		if(noticia.getCodigoNoticia() < 1 || noticia.getCodigoNoticia() > 99999) {
			return "Código da noticia inválido";
		}
		if (noticia.getLinkImagem().isEmpty() || noticia.getLinkImagem().length() > 50) {
			return "Link da noticia invalido";
		}
		
		if (noticia.getNomeNoticia().isEmpty() || noticia.getNomeNoticia().length() > 80) {
			return "Nome da noticia invalido";
		}
		
		if (noticia.getCategoriaNoticia().isEmpty() || noticia.getCategoriaNoticia().length() > 30) {
			return "Categoria da noticia invalida";
		}
		
		if (!DataBO.validacaoDataHora(noticia.getDataHoraNoticia())) {
			return "Data/hora noticia invalida";
		}
		
		if (noticia.getNoticia().isEmpty() || noticia.getNoticia().length() > 2000) {
			return "Descrição da noticia invalida";
		}
		
		noticia.setNomeNoticia(noticia.getNomeNoticia().toUpperCase());
		noticia.setCategoriaNoticia(noticia.getCategoriaNoticia().toUpperCase());
		noticia.setNoticia(noticia.getNoticia().toUpperCase());

		NoticiaDAO dao = new NoticiaDAO();
		Noticia noticiaExiste = dao.consultarPorCodigo(noticia.getCodigoNoticia());
		
		if (noticiaExiste.getCodigoNoticia() > 0) {
			dao.fechar();
			return "Noticia ja existe";
		}
		
		String retorno = dao.cadastrar(noticia) + " registro cadastrado";
		dao.fechar();

		return retorno;
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de uma noticia por codigo
	 * Regras de negocio validadas:
	 * O codigo de noticia deve ter entre 1 a 5 digitos
	 * @param codigoNoticia recebe um int
	 * @return um construtor vazio
	 * @throws Exception - Chamada da excecao Exception
	 */
	public Noticia consultaNoticiaPorCodigo(int codigoNoticia) throws Exception {

		if (codigoNoticia < 1 || codigoNoticia > 99999) {
			return new Noticia();
		}

		NoticiaDAO dao = new NoticiaDAO();

		Noticia retorno = dao.consultarPorCodigo(codigoNoticia);
		dao.fechar();

		return retorno;
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de uma Noticia por nome
	 * Regras de negocio validadas:
	 * O nome da noticia deve ter de 1 a 80 caracteres
	 * @param nomeNoticia recebe um objeto do tipo String
	 * @return uma lista com objetos do tipo Noticia 
	 * @throws Exception - Chamada da excecao Exception
	 */
	public List<Noticia> consultaNoticiaPorNome(String nomeNoticia) throws Exception {
		
		List<Noticia> listaNoticia = new ArrayList<Noticia>();
		
		if (nomeNoticia.isEmpty() || nomeNoticia.length() > 80 ) {
			return listaNoticia;
		}
		
		nomeNoticia = nomeNoticia.toUpperCase();

		NoticiaDAO dao = new NoticiaDAO();
		listaNoticia = dao.consultarPorNome(nomeNoticia);

		dao.fechar();

		return listaNoticia;

	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a edicao da Noticia
	 * Regras de negocio validadas:
	 * O codigo da noticia deve ter entre 1 a 5 digitos
	 * O nome da noticia deve ter de 1 a 80 caracteres
	 * A categoria da noticia deve ter de 1 a 30 caracteres
	 * a data da atualizacao de noticia deve ser valida,
	 * A noticia deve ter de 1 a 2000 caracteres
	 * @param noticia recebe um objeto do tipo Noticia
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	
	public String edicaoNoticia(Noticia noticia) throws Exception {

		if (noticia.getCodigoNoticia() < 1 || noticia.getCodigoNoticia() > 99999) {
			return "Codigo da noticia invalido";
		}
		if (noticia.getLinkImagem().isEmpty() || noticia.getLinkImagem().length() > 50) {
			return "Link da noticia invalido";
		}
		if (noticia.getNomeNoticia().isEmpty() || noticia.getNomeNoticia().length() > 80) {
			return "Nome da noticia invalido";
		}
		if (noticia.getCategoriaNoticia().isEmpty() || noticia.getCategoriaNoticia().length() > 30) {
			return "Categoria da noticia invalida";
		}
		if (!DataBO.validacaoDataHora(noticia.getDataHoraNoticia())) {
			return "Data/hora noticia invalida";
		}
		if (noticia.getNoticia().isEmpty() || noticia.getNoticia().length() > 2000) {
			return "Descricao da noticia invalida";
		}

		noticia.setNomeNoticia(noticia.getNomeNoticia().toUpperCase());
		noticia.setCategoriaNoticia(noticia.getCategoriaNoticia().toUpperCase());
		noticia.setNoticia(noticia.getNoticia().toUpperCase());

		NoticiaDAO dao = new NoticiaDAO();

		String retorno = dao.editar(noticia) + " registro editado";
		dao.fechar();

		return retorno;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a remocao da Noticia
	 * Regras de negocio validadas:
	 * O codigo da noticia deve ter entre 1 a 5 digitos
	 * @param codigoNoticia recebe um int
	 * @return uma String com o numero de registros removidos
	 * @throws Exception - Chamada da excecao Exception
	 */
	
	public String remocaoNoticia(int codigoNoticia) throws Exception {

		if (codigoNoticia < 1 || codigoNoticia > 99999) {
			return "Codigo invalido";
		}

		NoticiaDAO dao = new NoticiaDAO();

		String retorno = dao.remover(codigoNoticia) + "registro removido";
		dao.fechar();

		return retorno;
	}
}