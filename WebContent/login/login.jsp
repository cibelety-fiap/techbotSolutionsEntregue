<%@ include file="../imports/cabecaDentroPasta.jsp"%>
        <article style="float: left;">
          <form id="login" action="../Login" method="POST" autocomplete="off">
            <label>Login:</label>
            <br>
            <input type="text" placeholder="E-mail" name="email">
            <br><br>
            <label>Senha:</label>
            <br>
            <input type="password" placeholder="Senha" name="senha">
            <br><br>
            <button class="tamanho_botao">Enviar</button>
            <input type="reset" name="reset" value="Reset" class="tamanho_botao">
          </form>
          <br>
          <p>
            Caso não tenha cadastro <a href="cadastro.jsp">Clique aqui</a>
          </p>
        </article>
        <article class="menu_central">
          <p>
            Para fazer login como usuário:
            <br> E-mail: <strong>user@user.com</strong><!-- alterar -->
            <br> Senha: <strong>user</strong>
          </p>
          <br>
          <p>
            Para fazer login como administrador:
            <br> E-mail: <strong>admin@admin.com</strong><!-- alterar -->
            <br> Senha: <strong>admin</strong>
          </p>
        </article>
<%@ include file="../imports/rodapeDentroPasta.jsp"%>
