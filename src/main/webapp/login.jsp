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
	<h1>Login</h1>
	<c:if test="${not empty erro}" >
		<div class="alert alert-danger">${erro}</div>
	</c:if>
	<form action="servletLogin" method="POST">
        <fieldset>
            <label>Login</label>
            <input type="text" name="email">
            <hr />
            <label>Password</label>
            <input type="text" name="senha">
        </fieldset>
            <input type="submit" value="Conectar">
    </form>
     <a href="cadastro.jsp">
   		<button>Cadastrar-se</button>
	 </a>
</body>
</html>