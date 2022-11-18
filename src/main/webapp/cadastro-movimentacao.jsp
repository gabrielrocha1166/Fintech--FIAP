<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Movimentação</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container">
	<h1>Cadastro de Movimentação</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	
	
	<form action="movimentacao" method="post">
	<input type="hidden" value="cadastrar" name="acao">
	
		<div>
			<label for="id-categoria">Categoria</label>
			<select class="form-select" id="id-categoria" name="categoria">
				<option>receita</option>
			    <option>despesa</option>
			</select>
		</div>
	
	
		<%-- SELECAO DE CATEGORIA POR STRING - NAO USAR, MAS MANTER
		<div class="form-group">
			<label for="id-categoria">Categoria</label>
			<input type="text" name="categoria" id="id-categoria" class="form-control">
		</div> 
		--%>
	
	
		<div class="form-group">
			<label for="id-subcategoria">Sub Categoria</label>
			<input type="text" name="subcategoria" id="id-subcategoria" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-descricao">Descrição</label>
			<input type="text" name="descricao" id="id-descricao" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-datamovimentacao">Data</label>
			<input type="text" name="data" id="id-datamovimentacao" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-valor">Valor</label>
			<input type="text" name="valor" id="id-valor" class="form-control">
		</div>
		<input type="submit" value="Salvar" class="btn btn-primary">
	</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>


