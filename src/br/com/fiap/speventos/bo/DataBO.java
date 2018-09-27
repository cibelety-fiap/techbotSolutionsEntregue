package br.com.fiap.speventos.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Classe para validar e comparar dados de data
 *  @version 1.0
 *  @since 1.0
 *  @author Techbot Solutions
 *  @see AtualizacaoEventoBO
 *  @see AtualizacaoNoticiaBO
 *  @see RealizacaoEventoBO
 */
public class DataBO {

	/**
	 * Metodo para verificar a validade do formato da data/hora (dd/MM/yyyy HH:mm)
	 * @author Techbot Solutions
	 * @param dataHora recebe um objeto do tipo String
	 * @return um boolean informando se o formato e valido ou nao
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static boolean validacaoDataHora(String dataHora) throws Exception{
		return dataHora
				.matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)[0-9][0-9] ([01][0-9]|2[0-3]):([0-5][0-9])");
	}

	/**
	 * Metodo para comparar se a data de termino tem valor maior que a data de inicio
	 * @author Techbot Solutions
	 * @param inicio recebe um objeto do tipo String
	 * @param termino recebe um objeto do tipo String
	 * @return um boolean informando se termino tem um valor maior que inicio ou nao
	 * @throws Exception - Chamada da excecao Exception
	 */
	public static boolean compararDtHrInicioTermino(String inicio, String termino) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Date dtHrInicio = format.parse(inicio);
		Date dtHrTermino = format.parse(termino);
		
		if (dtHrTermino.compareTo(dtHrInicio) > 0) {
	        return true;
	    } else {
	    	return false;
	    }
	}
}
