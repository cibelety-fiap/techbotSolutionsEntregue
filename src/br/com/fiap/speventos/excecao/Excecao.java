package br.com.fiap.speventos.excecao;

public class Excecao extends Exception {

	public static String tratarExcecao(Exception e) {

		if (e.getClass().getName().equals("java.sql.SQLException")) {
			return "Ocorreu um erro ao conectar com o banco";
		} else if (e.getClass().getName().equals("java.sql.SQLRecoverableException")) {
			return "Ocorreu um problema ao tentar acessar o banco de dados. Espere alguns minutos e tente de novo.";	
		} else if (e.getClass().getName().equals("java.sql.SQLSyntaxErrorException")) {
			return "Erro de sintaxe no SQL";
		} else if (e.getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")) {
			return "Uma valiacao do banco de dados foi violada";		
		} else if (e.getClass().getName().equals("java.lang.NumberFormatException")) {
			return "Formato de numero invalido";
		} else {
			return "Ocorreu um problema";
		}
	}
}
