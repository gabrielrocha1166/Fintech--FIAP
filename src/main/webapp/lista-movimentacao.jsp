<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Movimentações</title>
<%@ include file="header.jsp" %>
</head>
<body>

<%@ include file="menu.jsp" %>
	<div class="container">
		<h1>Movimentação</h1>


		<c:if test="${not empty msg }">
			<div class="alert alert-success">${msg}</div>
		</c:if>
		<c:if test="${not empty erro }">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		


		<table class="table table-striped">
			<tr>
				<th>Categoria</th>
				<th>Sub Categoria</th>
				<th>Descrição</th>
				<th>Data</th>
				<th>Valor</th>
				<th></th>
			</tr>
			<c:forEach items="${movimentacoes}" var="p">
				<tr>
					<td>${p.categoria}</td>
					<td>${p.subCategoria}</td>
					<td>${p.descricao}</td>
					<td>
						<fmt:formatDate value="${p.data.time}" pattern="dd/MM/YYYY"/>
					</td>
					<td>${p.valor}</td>
					<td>
						<c:url value="movimentacao" var="link">
							<c:param name="acao" value="abrir-form-edicao"/>
							<c:param name="id" value="${p.id}"/>
						</c:url>
						<a href="${link}">Editar</a>
						
						<button type="button"
						class="btn btn-danger btn-xs" 
						data-toggle="modal" 
						data-target="#excluirModal" 
						onClick="codigoExcluir.value = ${p.id}">
							Excluir
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
<%@ include file="footer.jsp" %>



<!-- Modal -->
<div class="modal fade" id="excluirModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
 	<div class="modal-dialog" role="document">
    	<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Confirmação</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
        		</button>
			</div>
			<div class="modal-body">
				Deseja realmente excluir a movimentação?
			</div>
			<div class="modal-footer">
				<form action="movimentacao" method="post">
					<input type="hidden" name="acao" value="excluir">
					<input type="hidden" name="codigo" id="codigoExcluir">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
					<button type="submit" class="btn btn-danger">Excluir</button>
				</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>



