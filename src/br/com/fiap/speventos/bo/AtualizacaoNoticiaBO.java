package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.AtualizacaoNoticia;
import br.com.fiap.speventos.beans.Colaborador;
import br.com.fiap.speventos.beans.Noticia;
import br.com.fiap.speventos.dao.AtualizacaoNoticiaDAO;

/**
 * Classe para validar e padronizar dados para a tabela T_SGE_ATUALIZACAO_NOTICIA
 * @version 1.0
 * @since 1.0
 * @author Techbot Solutions
 * @see AtualizacaoNoticia
 * @see AtualizacaoNoticiaDAO 
 * @see Colaborador
 * @see Noticia
 *
 */

public class AtualizacaoNoticiaBO {
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a insercao de uma nova atualizacao de noticia
	 * Regras de negocio validadas:
	 * o codigo de atualizacao de noticia deve ter entre 1 a 5 digitos,
	 * o codigo de usuario deve ter entre 1 a 5 digitos,
	 * o codigo de noticia deve ter entre 1 a 5 digitos,
	 * a data da atualizacao de noticia deve ser valida,
	 * o tipo de atualizacao de noticia deve ter de 1 a 30 caracteres,
	 * a atualizacao de noticia nao pode ser cadastrada caso o codigo da atualizacao 
	 * ja exista no banco
	 * @author Techbot Solutions
	 * @param atualizacaoNoticia recebe um objeto do tipo AtualizacaoNoticia (Beans)
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String novaAtualizacaoNoticia(AtualizacaoNoticia atualizacaoNoticia) throws Exception {
		
		if (atualizacaoNoticia.getCodigoAtualizacaoNoticia() < 1 || atualizacaoNoticia.getCodigoAtualizacaoNoticia() > 99999) {
			return "Codigo de atualizacao de noticia invalido";
		}
		
		if (atualizacaoNoticia.getColaborador().getCodigoUsuario() < 1 || atualizacaoNoticia.getColaborador().getCodigoUsuario() > 99999) {
			return "Codigo de usuario invalido";
		}
		
		if (atualizacaoNoticia.getNoticia().getCodigoNoticia() < 1 || atualizacaoNoticia.getNoticia().getCodigoNoticia() > 99999) {
			return "Codigo de noticia invalido";
		}
		
		if (!DataBO.validacaoDataHora(atualizacaoNoticia.getDataHoraAtualizacao())) {
			return "Data/hora atualizacao invalida";
		}
		
		if (atualizacaoNoticia.getTipoAtualizacao().isEmpty() || atualizacaoNoticia.getTipoAtualizacao().length() > 30) {
			return "Tipo de atualizacao invalida";
		}

		atualizacaoNoticia.setTipoAtualizacao(atualizacaoNoticia.getTipoAtualizacao().toUpperCase());

		AtualizacaoNoticiaDAO dao = new AtualizacaoNoticiaDAO();

		AtualizacaoNoticia atualizacaoNoticiaCodRepetido = dao.consultar(atualizacaoNoticia.getCodigoAtualizacaoNoticia());

		if (atualizacaoNoticiaCodRepetido.getCodigoAtualizacaoNoticia() > 0) {
			return "Atualizacao de noticia ja existe";
		}

		String cadastroNoticia = NoticiaBO.novaNoticia(new Noticia(
				atualizacaoNoticia.getNoticia().getCodigoNoticia(), 
				atualizacaoNoticia.getNoticia().getLinkImagem(),
				atualizacaoNoticia.getNoticia().getNomeNoticia(),
				atualizacaoNoticia.getNoticia().getCategoriaNoticia(),
				atualizacaoNoticia.getNoticia().getDataHoraNoticia(),
				atualizacaoNoticia.getNoticia().getNoticia()
			)
		);
		
		if(!cadastroNoticia.equals("1 registro cadastrado")) {
			dao.fechar();
			return "Erro no cadastro de noticia";
		}
		
		String retorno = dao.cadastrar(atualizacaoNoticia) + " registro cadastrado";
		dao.fechar();
		return retorno; 
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de uma atualizacao de noticia por codigo de atualizacao de noticia
	 * Regras de negocio validadas:
	 * O codigo da atualizacao de noticia deve ter entre 1 a 5 digitos
	 * @param codigoAtualizacaoNoticia recebe um int
	 * @return um objeto do tipo AtualizacaoNoticia (Beans)
	 * @throws Exception - Chamada da excecao checked
	 */
	public static AtualizacaoNoticia consultaAtualizacaoNoticia(int codigoAtualizacaoNoticia) throws Exception {

		if (codigoAtualizacaoNoticia < 1 || codigoAtualizacaoNoticia > 99999) {
			return new AtualizacaoNoticia();
		}

		AtualizacaoNoticiaDAO dao = new AtualizacaoNoticiaDAO();

		AtualizacaoNoticia retorno = dao.consultar(codigoAtualizacaoNoticia);
		dao.fechar();
		
		return retorno;
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de atualizacoes de noticia por codigo de noticia
	 * Regras de negocio validadas:
	 * O codigo de noticia deve ter entre 1 a 5 digitos
	 * @param codigoNoticia recebe um int
	 * @return uma lista com objetos do tipo Atualizacao de Noticia 
	 * @throws Exception - Chamada da exceção checked
	 */

	public static List<AtualizacaoNoticia> consultaPorCodigoNoticia(int codigoNoticia) throws Exception {
		
		List<AtualizacaoNoticia> listaAtualizacaoNoticia = new ArrayList<AtualizacaoNoticia>();
		
		if (codigoNoticia < 1 || codigoNoticia > 99999) {
			return listaAtualizacaoNoticia;
		}

		AtualizacaoNoticiaDAO dao = new AtualizacaoNoticiaDAO();
		
		listaAtualizacaoNoticia = dao.consultarPorCodigoNoticia(codigoNoticia);
		dao.fechar();
		
		return listaAtualizacaoNoticia;
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a edicao da atualizacao de noticia
	 * Regras de negocio validadas:
	 * o codigo de atualizacao de noticia deve ter entre 1 a 5 digitos,
	 * o codigo de usuario deve ter entre 1 a 5 digitos,
	 * o codigo de noticia deve ter entre 1 a 5 digitos,
	 * a data da atualizacao de noticia deve ser valida,
	 * o tipo de atualizacao de noticia deve ter de 1 a 30 caracteres
	 * @param atualizacaoNoticia recebe um objeto do tipo AtualizacaoNoticia
	 * @return uma String com a quantidade de registros editados ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String edicaoAtualizacaoNoticia(AtualizacaoNoticia atualizacaoNoticia)
			throws Exception {

		if (atualizacaoNoticia.getCodigoAtualizacaoNoticia() < 1 || atualizacaoNoticia.getCodigoAtualizacaoNoticia() > 99999) {
			return "Codigo de atualizacao noticia invalido";
		}
		
		if (atualizacaoNoticia.getColaborador().getCodigoUsuario() < 1 || atualizacaoNoticia.getColaborador().getCodigoUsuario() > 99999) {
			return "Codigo de usuario invalido";
		}
		
		if (atualizacaoNoticia.getNoticia().getCodigoNoticia() < 1 || atualizacaoNoticia.getNoticia().getCodigoNoticia() > 99999) {
			return "Codigo de noticia invalido";
		}
		
		if (!DataBO.validacaoDataHora(atualizacaoNoticia.getDataHoraAtualizacao())) {
			return "Data/hora atualizacao invalida";
		}
		
		if (atualizacaoNoticia.getTipoAtualizacao().isEmpty() || atualizacaoNoticia.getTipoAtualizacao().length() > 30) {
			return "Tipo de atualizacao invalida";
		}

		atualizacaoNoticia.setTipoAtualizacao(atualizacaoNoticia.getTipoAtualizacao().toUpperCase());
		
		String cadastroNoticia = NoticiaBO.novaNoticia(new Noticia(
				atualizacaoNoticia.getNoticia().getCodigoNoticia(), 
				atualizacaoNoticia.getNoticia().getLinkImagem(),
				atualizacaoNoticia.getNoticia().getNomeNoticia(),
				atualizacaoNoticia.getNoticia().getCategoriaNoticia(),
				atualizacaoNoticia.getNoticia().getDataHoraNoticia(),
				atualizacaoNoticia.getNoticia().getNoticia()
			)
		);
		
		if(!cadastroNoticia.equals("1 registro editado")) {
			return "Erro no cadastro de noticia";
		}
		
		AtualizacaoNoticiaDAO dao = new AtualizacaoNoticiaDAO();

		String retorno = dao.editar(atualizacaoNoticia) + " registro editado";
		dao.fechar();
		return retorno; 
	}
	
	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a remocao da atualizacao de noticia
	 * Regras de negocio validadas:
	 * O codigo da atualizacao de noticia deve ter entre 1 a 5 digitos
	 * @param codigoAtualizacaoNoticia recebe um int
	 * @return uma String com o numero de registros removidos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String remocaoNoticia(int codigoAtualizacaoNoticia) throws Exception {

		if (codigoAtualizacaoNoticia < 1 || codigoAtualizacaoNoticia > 99999) {
			return "Codigo invalido";
		}
		AtualizacaoNoticiaDAO dao = new AtualizacaoNoticiaDAO();
		
		String retorno = dao.remover(codigoAtualizacaoNoticia) + " registro removido";

		dao.fechar();
		
		return retorno;
	}
}
