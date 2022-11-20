<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cadastre-se</title>
    </head>
    <body>
        <header>
			<img src="imagens/logo.svg" alt="logo" class="logo">
		</header>
        <main class="box">
            <h1>Cadastre-se</h1>
            <c:if test="${not empty erro}" >
                <div class="alert alert-danger">${erro}</div>
            </c:if>
            <form action="servletCadastro" method="POST">
                <fieldset>
                    <label>Nome</label>
                    <input type="text" name="nome" maxlength="50" placeholder="Digite seu nome" autocomplete="on" required class="nome">
                    <label>Email</label>
                    <input type="text" name="email" maxlength="50" placeholder="Digite seu e-mail" autocomplete="on" required class="email">
                    <label>Senha</label>
                    <input type="text" name="senha" minlength="8" maxlength="50" placeholder="Digite sua senha" required class="senha">
                    <label>Confirmar senha</label>
                    <input type="text" name="confirmarSenha" minlength="8" maxlength="50" placeholder="Digite a mesma senha" required class="confirmar_senha">
                </fieldset>
                    <input type="submit" value="Criar conta" class="criar_conta">
            </form>
        </main>
    </body>
</html>