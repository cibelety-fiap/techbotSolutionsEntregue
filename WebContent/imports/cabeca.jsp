<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, br.com.fiap.speventos.beans.* "%>
    
<!DOCTYPE html>
<html>

  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style><%@include file="../css/style.css" %></style>
    <title>SP Eventos</title>
  </head>

  <body>
    <div class="container">
      <header>
        <h1 style="float: left;">SP Eventos</h1>
        <a href="login/login.jsp"><img style="float: right; width: 60px;" src="img/user_login.png" /></a>
        <br />
        <br />
        <br />
      </header>
      <div class="topnav" id="myTopnav">
        <a href="index.jsp">Home</a>
        <div class="dropdown">
          <button class="dropbtn">Cinema
						  <i class="fa fa-caret-down"></i>
						</button>
          <div class="dropdown-content">
            <a href="filmes_em_cartaz.jsp">Em cartaz</a>
            <a href="proximos_lancamentos.jsp">Pr&oacute;ximos lan&ccedil;amentos</a>
          </div>
        </div>
        <div class="dropdown">
          <button class="dropbtn">Shows
						  <i class="fa fa-caret-down"></i>
						</button>
          <div class="dropdown-content">
            <a href="proximos_shows.jsp">Pr&oacute;ximos shows</a>
            <a href="mais_esperados_ano.jsp">Mais esperados do ano</a>
          </div>
        </div>
        <div class="dropdown">
          <button class="dropbtn">Teatro
					      <i class="fa fa-caret-down"></i>
					    </button>
          <div class="dropdown-content">
            <a href="teatro_em_cartaz.jsp">Em cartaz</a>
          </div>
        </div>
        <a href="contato.jsp">Contato</a>
        <a href="chatbot.jsp" target="_blank" class="active">Chatbot</a>
        <div class="search-container">
          <form>
            <img src="img/search.png" alt="" id="lupa">
            <input type="search" placeholder="Pesquisa" name="search">
          </form>
        </div>
        <a href="javascript:void(0); " style="font-size:15px; " class="icon " onclick="myFunction() ">&#9776;</a>
      </div>