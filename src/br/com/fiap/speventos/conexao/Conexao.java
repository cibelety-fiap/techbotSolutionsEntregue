package br.com.fiap.speventos.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";

	public Connection conectar() throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection
				(url,"rm80951", "130186");
		
	}
}
