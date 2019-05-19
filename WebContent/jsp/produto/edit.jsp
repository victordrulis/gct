<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.classificacao.ProdutoStatus"%>
<%@page import="br.com.drulis.gct.dominio.classificacao.ProdutoTipo"%>
<%@page import="br.com.drulis.gct.dominio.Produto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<%
        	Produto produto = (Produto) request.getAttribute("resultado");
    	%>
		<div class="container">
			<h2>Alterar Produto - ID #<%= produto.getId() %></h2>
			<p>Alterar ou editar dados de produto.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->
		<form action="/gct/produto?acao=alterar" method="post">
		<input type="hidden" id="id" name="id" value="<%= produto.getId() %>">
			<div class="row">
	            <div class="form-group col-md-4">
	                <h6><strong><label for="titulo">Título</label></strong></h6>
	                <input type="text" class="form-control" id="titulo" name="titulo" value="<%= produto.getTitulo() %>">
	            </div>
	        </div>
	        
            <div class="row">
            	<div class="form-group col-md-1">
	                <h6><strong><label for="versao">Versão</label></strong></h6>
	                <input type="text" class="form-control" id="versao" name="versao" value="<%= produto.getVersao() %>">
	            </div>
	            <div class="form-group col-md-2">
	                <h6><strong><label for="codigo">Código</label></strong></h6>
	                <input type="text" class="form-control" id="codigo" name="codigo" value="<%= produto.getCodigo()%>">
	            </div>
            </div>
	            
	        <div class="row">
                <div class="form-group col-md-6">
	                <h6><strong><label for="descricao">Descrição</label></strong></h6>
	                <textarea class="form-control" id="descricao" name="descricao"> <%= produto.getDescricao() %></textarea>
                </div>
                
	        </div>
	       <div class="row">
                <div class="form-group col-md-2">
                    <label for="tipo"><strong>Tipo</strong></label>
                    <select class="form-control" name="tipo" id="tipo">
                    		<option value="0">Selecione...</option>
                    	<%
                    		for(Map.Entry<Integer, ProdutoTipo> tipo : ProdutoTipo.getMapa().entrySet()) {
                   		%>
                   				<option value="<%= tipo.getKey()%>" 
                   					<% 
                   						if(produto.getProdutoTipo() != null && produto.getProdutoTipo().getId() == tipo.getValue().getId()) { 
                   					%>
                   							selected="selected"
                   					<% } %>
                   					>
                   					<%= tipo.getValue().getDescricao() %>
                   				</option>
                    	<%
                    		}
                    	%>
                    </select>
                </div>
                
                <div class="form-group col-md-2">
                    <label for="status"><strong>Status</strong></label>
                    <select class="form-control" name="status" id="status">
                    		<option value="0">Selecione...</option>
                    	<%
                    		for(Map.Entry<Integer, ProdutoStatus> status : ProdutoStatus.getMapa().entrySet()) {
                   		%>
                   				<option value="<%= status.getKey()%>"
                   				<% 
               						if(produto.getProdutoStatus() != null && produto.getProdutoStatus().getId() == status.getValue().getId()) { 
               					%>
                 						selected="selected"
                  				<% } %>
                   					><%= status.getValue().getDescricao() %>
                   				</option>
                    	<%
                    		}
                    	%>
                    </select>
                </div>
            </div>
	       <div class="row">
				<div class="form-group col-md-3">
					<input type="checkbox" class="form-check-input" id="ativo" name="ativo" 
					<% 
						if(produto.getAtivo() != 0) {
					%>
							checked="checked"
					<%
						}
					%>
					>
					<h6><strong><label class="form-check-label" for="ativo">Ativo</label></strong></h6>
				</div>
				
			</div>
			<div class="row">
			     <button type="submit" class="btn btn-primary col-md-1" id="acao" name="acao" value="alterar">Salvar</button>
			     <button type="reset" class="btn btn-warning col-md-1">Desfazer</button>
			     <a class="col-md-2" href="/gct/produto">
			     	<button type="button" class="btn btn-secundary col-md">Cancelar</button>
			     </a>
			</div>
			
		</form>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>