<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Cadastro</h1>
	<c:if test="${not empty erro}" >
		<div class="alert alert-danger">${erro}</div>
	</c:if>
	<form action="servletCadastro" method="POST">
        <fieldset>
            <label>Nome</label>
            <input type="text" name="nome">
            <hr />
            <label>Email</label>
            <input type="text" name="email">
            <hr />
            <label>Senha</label>
            <input type="text" name="senha">
            <label>Confirmar senha</label>
            <input type="text" name="confirmarSenha">
        </fieldset>
            <input type="submit" value="Criar conta">
    </form>
</body>
</html>