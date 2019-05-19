<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Produto"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../fragmentos/header.jsp"%>
<style type="text/css">
.conteudo-topo {
	padding-top: 40px;
}
</style>

</head>
<body>
	<%@include file="../fragmentos/nav.jsp"%>
    <%
        Produto produto = (Produto) request.getAttribute("resultado");
    %>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Produto - ID: <%= produto.getId() %></h2>
			<p>Dados de produto.</p>
		</div>

	</div>
	<div class="container">
		<div class="row">
	    <div class="form-group">
		     <a href="/gct/produto?acao=editar&id=<%= produto.getId() %>"><button type="button" class="btn btn-primary">Editar</button></a>
	     </div>
	                <div class=" ">
		     <a href="/gct/produto?acao=excluir&id=<%= produto.getId() %>"><button type="button" class="btn btn-warning">Excluir</button></a>
	     </div>
	     
	     <div class=" ">
              <a href="/gct/produto"><button type="button" class="btn btn-secundary">Voltar</button></a>
           </div>
	    </div>
			<hr>
	        
			<div class="row">
				<h5>Informações</h5>
			</div>
			<div class="row">
				<div class="form-group col-md-4">
                    <h6><strong>Título</strong></h6><p> <%= produto.getTitulo() %></p>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-1">
                    <h6><strong>Versão</strong></h6><p> <%= produto.getVersao() %></p>
                </div>
                <div class="form-group col-md-2">
                    <h6><strong>Código</strong></h6><p> <%= produto.getCodigo() %></p>
                </div>
            </div>
	        <div class="row">
                <div class="form-group col-md-6">
                    <h6><strong>Descrição</strong></h6><p> <%= produto.getDescricao() %></p>
                </div>
            </div>
            
            <div class="row">
                <div class="form-group col-md-2">
                    <h6><strong>Status</strong></h6><p> <%= produto.getProdutoStatus().getDescricao() %></p>
                </div>
            </div>    
           <hr> 
			<div class="row">
				    <div class="form-group">
					     <a href="/gct/produto?acao=editar&id=<%= produto.getId() %>"><button type="button" class="btn btn-primary">Editar</button></a>
				     </div>
                    <div class="form-group ">
					     <a href="/gct/produto?acao=excluir&id=<%= produto.getId() %>"><button type="button" class="btn btn-warning">Excluir</button></a>
				     </div>
				     
				     <div class="form-group ">
                         <a href="/gct/produto"><button type="button" class="btn btn-secundary">Voltar</button></a>
                     </div>
		     </div>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>