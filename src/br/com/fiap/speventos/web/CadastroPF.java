package br.com.fiap.speventos.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CadastroPF")
public class CadastroPF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");	
		String endereco = request.getParameter("endereco");
		String cpfInteiro = request.getParameter("cpf");
		String rgInteiro = request.getParameter("rg");
		String dataNascimento = request.getParameter("dataNascimento");
		String senha = request.getParameter("senha");
		String genero = request.getParameter("genero");
		//VER SE JA RETORNA SIM
		boolean receberEmails = request.getParameter("receberEmails") != null;
		
		String cpf = cpfInteiro.substring(0,11);
		String cpf_digito = cpfInteiro.substring(12);
		
		String rg = rgInteiro.substring(0, rgInteiro.indexOf("-"));
		String rg_digito = rgInteiro.substring(rgInteiro.indexOf("-") + 1);
		
		
		
		request.setAttribute("email", email);
		request.setAttribute("nome", nome);
		request.setAttribute("telefone", telefone);
		request.setAttribute("endereco", endereco);
		request.setAttribute("cpf", cpf);
		request.setAttribute("cpf_digito", cpf_digito);
		request.setAttribute("rg", rg);
		request.setAttribute("rg_digito", rg_digito);
		request.setAttribute("dataNascimento", dataNascimento);
		request.setAttribute("senha", senha);
		request.setAttribute("genero", genero);
		request.setAttribute("receberEmails", receberEmails);		
		

/*
		//CONFIGURAR NO BANCO CD_USUARIO COMO AUTO-INCREMENT
		PessoaFisica pessoaFisica = new PessoaFisica(email, nome, telefone, endereco, cpf, cpf_digito, 
				rg, rg_digito, dataNascimento, senha, genero, receberEmails);
*/
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("cadastro_realizado.jsp");
		
		dispatcher.forward(request, response);
		
	}
}
