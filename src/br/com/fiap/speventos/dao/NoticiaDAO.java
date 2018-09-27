package br.com.fiap.speventos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.speventos.beans.Noticia;
import br.com.fiap.speventos.conexao.Conexao;

/**
 * Classe para manipular a tabela T_SGE_NOTICIA
 * Possui metodos para: abrir conexao, cadastrar, consultarPorCodigo, consultarPorNomeNoticia, 
 * editar, remover, fechar conexao.
 * @author Techbot Solutions
 * @version 1.0
 * @since 1.0
 * @see Noticia
 *
 */
public class NoticiaDAO {

		private Connection con;
		private PreparedStatement stmt;
		private ResultSet rs;
		
		/**
		  * Metodo construtor que estabelece a comunicacao com o banco de dados
		  * @author Techbot Solutions
		  * @throws Exception - Chamada da excecao checked SQLException
		  */	
		public NoticiaDAO() throws Exception {
			con = new Conexao().conectar();
		}
		
		/**
		  * Metodo para adicionar um registro na tabela T_SGE_NOTICIA
		  * @author Techbot Solutions
		  * @param noticia recebe um objeto do tipo Noticia (Beans)
		  * @return um int com a quantidade de registros inseridos
		  * @throws Exception - Chamada da excecao Exception
		  */	  
		public int cadastrar(Noticia noticia)throws Exception {
			stmt = con.prepareStatement("INSERT INTO T_SGE_NOTICIA " + 
					"(CD_NOTICIA, DS_LINK_IMAGEM, NM_NOTICIA, DS_CATEGORIA_NOTICIA, DT_HR_NOTICIA, DS_NOTICIA) VALUES (?, ?, ?, ?, ?, ?)");

			stmt.setInt(1, noticia.getCodigoNoticia());
			stmt.setString(2, noticia.getLinkImagem());
			stmt.setString(3, noticia.getNomeNoticia());
			stmt.setString(4, noticia.getCategoriaNoticia());
			stmt.setString(5, noticia.getDataHoraNoticia());
			stmt.setString(6, noticia.getNoticia());
			

			return stmt.executeUpdate();
		}
		
		/**
		  * Metodo para consultar por codigo da noticia
		  * um registro na tabela T_SGE_NOTICIA
		  * @author Techbot Solutions
		  * @param codigoNoticia recebe um int
		  * @return um objeto do tipo Noticia
		  * @throws Exception - Chamada da excecao Exception
		  */
		public Noticia consultarPorCodigo(int codigoNoticia) throws Exception{
			stmt = con.prepareStatement("SELECT CD_NOTICIA, DS_LINK_IMAGEM, NM_NOTICIA, DS_CATEGORIA_NOTICIA, DT_HR_NOTICIA, DS_NOTICIA FROM T_SGE_NOTICIA "
					+ "WHERE CD_NOTICIA=?");
			
			stmt.setInt(1, codigoNoticia);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Noticia(
					rs.getInt("CD_NOTICIA"),
					rs.getString("DS_LIK_IMAGEM"),
					rs.getString("NM_NOTICIA"),
					rs.getString("DS_CATEGORIA_NOTICIA"),
					rs.getString("DT_HR_NOTICIA"),
					rs.getString("DS_NOTICIA")
				);
			}
			return new Noticia();		
		}
		
		/**
		  * Metodo para consultar por nome da noticia
		  * um registro na tabela T_SGE_NOTICIA
		  * @author Techbot Solutions
		  * @param nomeNoticia recebe um objeto do tipo String
		  * @return uma lista com objetos do tipo Noticia
		  * @throws Exception - Chamada da excecao Exception
		  */
		public List<Noticia> consultarPorNome(String nomeNoticia) throws Exception {
			
			List<Noticia> listaNoticia = new ArrayList<Noticia>();
			
			stmt = con.prepareStatement("SELECT CD_NOTICIA, DS_LINK_IMAGEM, NM_NOTICIA, DS_CATEGORIA_NOTICIA, DT_HR_NOTICIA, DS_NOTICIA "
					+ "FROM T_SGE_NOTICIA "
					+ "WHERE NM_NOTICIA LIKE ?");
			stmt.setString(1, "%" + nomeNoticia + "%");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				listaNoticia.add(
					new Noticia(
						rs.getInt("CD_NOTICIA"),
						rs.getString("DS_LINK_IMAGEM"),
						rs.getString("NM_NOTICIA"),
						rs.getString("DS_CATEGORIA_NOTICIA"),
						rs.getString("DT_HR_NOTICIA"),
						rs.getString("DS_NOTICIA")
					)
				);
			}	
			return listaNoticia;
		}
		
		/**
		  * Metodo para editar um registro na tabela T_SGE_NOTICIA
		  * @author Techbot Solutions
		  * @param noticia recebe um objeto do tipo Noticia
		  * @return um int com a quantidade de registros editados
		  * @throws Exception - Chamada da excecao Exception
		  */
		public int editar(Noticia noticia) throws Exception {
			stmt = con.prepareStatement("UPDATE T_SGE_NOTICIA "
					+ "SET CD_NOTICIA=?, DS_LINK_IMAGEM=?, NM_NOTICIA=?, DS_CATEGORIA_NOTICIA=?, DT_HR_NOTICIA=?, DS_NOTICIA=? "
					+ "WHERE CD_NOTICIA=?");
			
			stmt.setInt(1, noticia.getCodigoNoticia());
			stmt.setString(2, noticia.getLinkImagem());
			stmt.setString(3, noticia.getNomeNoticia());
			stmt.setString(4,  noticia.getCategoriaNoticia());
			stmt.setString(5, noticia.getDataHoraNoticia());
			stmt.setString(6, noticia.getNoticia());
			stmt.setInt(7, noticia.getCodigoNoticia());
			
			return stmt.executeUpdate();
		}
		
		/**
		  * Metodo para remover um registro na tabela T_SGE_NOTICIA
		  * @author Techbot Solutions
		  * @param codigoNoticia recebe um objeto do tipo int
		  * @return um int com o numero de itens removidos
		  * @throws Exception - Chamada da excecao Exception
		  */	
		public int remover(int codigoNoticia) throws Exception {
			stmt = con.prepareStatement("DELETE FROM T_SGE_NOTICIA "
					+ "WHERE CD_NOTICIA=?");
			
			stmt.setInt(1, codigoNoticia);
			
			return stmt.executeUpdate();
		}
		
		/**
		  * Metodo que fecha a comunicacao com o banco de dados
		  * @author Techbot Solutions
		  * @throws Exception - Chamada da excecao Exception
		  */	
		public void fechar() throws Exception{
			con.close();
		}		
}