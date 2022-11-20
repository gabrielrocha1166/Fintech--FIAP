<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Fintech</a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>



  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    <li class="nav-item">
    	<a class="nav-link" href="cadastro-movimentacao.jsp">Cadastro</a>
    </li>
      
	<li class="nav-item">
		<a class="nav-link" href="movimentacao?acao=listar">Movimentacoes</a>
    </li>
    </ul>
	<c:if test="${empty usuario }">
		<span class="navbar-text text-danger" style="margin-right:10px" >
	        ${erro }
	  	</span>	
	    <form class="form-inline my-2 my-lg-0" action="servletLogin" method="POST">
	    	  <input class="form-control mr-sm-2" type="text" name="email" placeholder="email">
	      <input class="form-control mr-sm-2" type="password" name="senha" placeholder="senha">
	      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Entrar</button>
	    </form>
    </c:if>
    <c:if test="${not empty usuario }">
    		<span class="navbar-text">
	    		${usuario }
	    		<a href="login" class="btn btn-outline-primary my-2 my-sm-0">Sair</a>
	  	</span>	
    </c:if>
    
    
    
    
  </div>
</nav>










