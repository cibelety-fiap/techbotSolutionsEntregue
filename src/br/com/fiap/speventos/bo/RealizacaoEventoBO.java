package br.com.fiap.speventos.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Evento;
import br.com.fiap.speventos.beans.Local;
import br.com.fiap.speventos.beans.RealizacaoEvento;
import br.com.fiap.speventos.dao.RealizacaoEventoDAO;


/**
 *  Classe para validar e padronizar dados para a tabela T_SGE_REALIZACAO_EVENTO
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see RealizacaoEventoDAO
 *  @see RealizacaoEvento
 */
public class RealizacaoEventoBO {

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a insercao de uma nova RealizacaoEvento
	 * Regras de negocio validadas:
	 * O codigo da realizacaoEvento deve ter entre 1 a 5 digitos
	 * O codigo do evento deve ter entre 1 a 5 digitos
	 * O codigo do local deve ter entre 1 a 5 digitos
	 * A data de inicio e termino de realizacaoEvento deve ser valida
	 * A data de inicio deve ser menor que a de termino
	 * O codigo do realizacaoEvento nao pode ser cadastrado caso ja exista o banco
	 * O codigo do evento deve existir
	 * O codigo do local deve ser valido
	 * @author Techbot Solutions
	 * @param realizacaoEvento recebe um objeto do tipo RealizacaoEvento (Beans)
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception 
	 *
	 */
	public static String novaRealizacaoEvento(RealizacaoEvento realizacaoEvento) throws Exception {

		if (realizacaoEvento.getCodigoRealizacaoEvento() < 1 || 
				realizacaoEvento.getCodigoRealizacaoEvento() > 99999) {
			return "Codigo de realizacao de evento invalido";
		}

		if (realizacaoEvento.getEvento().getCodigoEvento() < 1 || 
				realizacaoEvento.getEvento().getCodigoEvento() > 99999) {
			return "Codigo de evento invalido";
		}

		if (realizacaoEvento.getLocal().getCodigoLocal() < 1 || 
				realizacaoEvento.getLocal().getCodigoLocal() > 99999) {
			return "Codigo de evento invalido";
		}

		if (!DataBO.validacaoDataHora(realizacaoEvento.getDataHoraInicio())) {
			return "Data/hora de inicio invalida(s)";
		}

		if (!DataBO.validacaoDataHora(realizacaoEvento.getDataHoraTermino())) {
			return "Data/hora de termino invalida(s)";
		}

		if(!DataBO.compararDtHrInicioTermino(realizacaoEvento.getDataHoraInicio(), realizacaoEvento.getDataHoraTermino())) {
			return "Data de inicio maior que data de termino";
		}

		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

		RealizacaoEvento realizacaoEventoRepetido = dao
				.consultarPorCodigo(realizacaoEvento.getCodigoRealizacaoEvento());

		if (realizacaoEventoRepetido.getCodigoRealizacaoEvento() > 0) {
			return "Realizacao de evento ja existe";
		}

		Evento eventoExiste = EventoBO.consultaEvento(realizacaoEvento.getEvento().getCodigoEvento());

		if(eventoExiste.getCodigoEvento() == 0) {
			return "Evento relacionado a realizacao de evento nao existe";
		} 

		Local localExiste = LocalBO.consultaLocalPorCodigo(realizacaoEvento.getLocal().getCodigoLocal());
		//		System.out.println(localExiste.getCodigoLocal());

		String localValido = "";

		if(localExiste.getCodigoLocal() == 0) {
			localValido = LocalBO.novoLocal(realizacaoEvento.getLocal());
		}
		System.out.println(localValido);
		String retorno = null;

		if(localValido.equals("1 registro cadastrado") || localValido.isEmpty()) {
			retorno = dao.cadastrar(realizacaoEvento) + " registro cadastrado";
		}

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de uma realizacaoEvento por codigo
	 * Regras de negocio validadas:
	 * O codigo de realizacaoEvento deve ter entre 1 a 5 digitos
	 * @param codRealizEvento recebe um int
	 * @return um construtor vazio
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static RealizacaoEvento consultaRealizEventoPorCodigo(int codRealizEvento) throws Exception {
		if (codRealizEvento < 1 || codRealizEvento > 99999 ) {
			return new RealizacaoEvento();
		}

		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();
		RealizacaoEvento retorno = dao.consultarPorCodigo(codRealizEvento);
		dao.fechar();

		return retorno;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de uma realizacaoEvento por nome do evento
	 * Regras de negocio validadas:
	 * O nome do evento deve ter de 1 a 80 caracteres
	 * @param nomeEvento recebe um objeto do tipo String
	 * @return uma lista com objetos do tipo realizacaoEvento
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static List<RealizacaoEvento> consultaRealizEventoPorNomeEvento(String nomeEvento) throws Exception {

		List<RealizacaoEvento> listaRealizacaoEvento = new ArrayList<RealizacaoEvento>();

		if (nomeEvento.isEmpty() || nomeEvento.length() > 80 ) {
			return listaRealizacaoEvento;
		}

		nomeEvento = nomeEvento.toUpperCase();
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();
		listaRealizacaoEvento = dao.consultarPorNomeEvento(nomeEvento);
		dao.fechar();

		return listaRealizacaoEvento;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a consulta de uma realizacaoEvento por codigo do evento
	 * Regras de negocio validadas:
	 * O codigo do evento deve ter de 1 a 5 digitos
	 * @param codEvento recebe um objeto do tipo int
	 * @return uma lista com objetos do tipo realizacaoEvento
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static List<RealizacaoEvento> consultaRealizEventoPorCodEvento(int codEvento) throws Exception {

		List<RealizacaoEvento> listaRealizacaoEvento = new ArrayList<RealizacaoEvento>();

		if (codEvento < 1 || codEvento > 99999 ) {
			return listaRealizacaoEvento;
		}

		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();
		listaRealizacaoEvento = dao.consultarPorCodEvento(codEvento);
		dao.fechar();

		return listaRealizacaoEvento;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a edicao do realizacaoEvento
	 * Regras de negocio validadas:
	 * O codigo da realizacaoEvento deve ter entre 1 a 5 digitos
	 * O codigo do evento deve ter entre 1 a 5 digitos
	 * O codigo do local deve ter entre 1 a 5 digitos
	 * A data de inicio e termino de realizacaoEvento deve ser valida
	 * A data de inicio deve ser menor que a de termino
	 * O codigo do realizacaoEvento nao pode ser cadastrado caso ja exista o banco
	 * O codigo do evento deve existir
	 * O codigo do local deve ser valido
	 * @param realizacaoEvento recebe um objeto do tipo RealizacaoEvento
	 * @return uma String com a quantidade de registros inseridos ou o erro ocorrido
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String edicaoRealizacaoEvento(RealizacaoEvento realizacaoEvento) throws Exception {

		if (realizacaoEvento.getCodigoRealizacaoEvento() < 1 || 
				realizacaoEvento.getCodigoRealizacaoEvento() > 99999) {
			return "Codigo de realizacao de evento invalido";
		}

		if (realizacaoEvento.getEvento().getCodigoEvento() < 1 || 
				realizacaoEvento.getEvento().getCodigoEvento() > 99999) {
			return "Codigo de evento invalido";
		}

		if (realizacaoEvento.getLocal().getCodigoLocal() < 1 || 
				realizacaoEvento.getLocal().getCodigoLocal() > 99999) {
			return "Codigo de evento invalido";
		}

		if (!DataBO.validacaoDataHora(realizacaoEvento.getDataHoraInicio())) {
			return "Data/hora de inicio invalida(s)";
		}

		if (!DataBO.validacaoDataHora(realizacaoEvento.getDataHoraTermino())) {
			return "Data/hora de termino invalida(s)";
		}

		if(!DataBO.compararDtHrInicioTermino(realizacaoEvento.getDataHoraInicio(), realizacaoEvento.getDataHoraTermino())) {
			return "Data de inicio maior que data de termino";
		}

		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

		Evento eventoExiste = EventoBO.consultaEvento(realizacaoEvento.getEvento().getCodigoEvento());

		if(eventoExiste.getCodigoEvento() == 0) {
			return "Evento relacionado a realizacao de evento nao existe";
		} 

		Local localExiste = LocalBO.consultaLocalPorCodigo(realizacaoEvento.getLocal().getCodigoLocal());

		String localValido = "";

		if(localExiste.getCodigoLocal() == 0) {
			//o usuario nao pode editar o local, somente adicionar um novo com valores alterados
			localValido = LocalBO.novoLocal(realizacaoEvento.getLocal());
		}

		String retorno = null;

		if(localValido.equals("1 registro inserido") || localValido.isEmpty()) {
			retorno = dao.editar(realizacaoEvento) + " registro editado";
		}

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo responsavel por verificar regras de negocio, validacoes e padronizacoes
	 * relacionadas a remocao da RealizacaoEvento
	 * Regras de negocio validadas:
	 * O codigo da noticia deve ter entre 1 a 5 digitos
	 * @param codRealizEvento recebe um int
	 * @return uma String com o numero de registros removidos
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static String remocaoRealizacaoEvento(int codRealizEvento) throws Exception {

		if (codRealizEvento < 1 || codRealizEvento > 99999) {
			return "Codigo invalido";
		}

		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();

		String retorno = dao.remover(codRealizEvento) + " registro removido";

		dao.fechar();
		return retorno;
	}

	/**
	 * Metodo para chamar o calculo do proximo codigo de realizacao de evento
	 * @author Techbot Solutions
	 * @return um int com o numero do proximo codigo de realizacao de evento
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static int consultaProxCodRealizEvento() throws Exception {
		RealizacaoEventoDAO dao = new RealizacaoEventoDAO();
		int retorno = dao.calcularCodRealizEvento();
		dao.fechar();
		return retorno;
	}
}
