<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Produto"%>
<%@page import="br.com.drulis.gct.dominio.Produto"%>
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
	   System.out.println(this.getClass().getSimpleName() + ": Show");
	   Produto produto = (Produto) request.getAttribute("resultado");
	%>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Produto - ID: <%= produto.getId() %></h2>
			<p>Dados do produto.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

			<div class="row">
                <div class="form-group">
                    <h5><strong>Produto</strong></h5>
                    <p>
	                    <span><strong>Título:</strong></span> Titulo
	                    <span><strong>Versão:</strong></span> Versao
                    </p>
                </div>
            </div>
			<div class="row">
			    <a href="/gct/produto?acao=exibir&id=<%= produto.getId() %>">
                    <button type="button" class="btn btn-primary">Exibir Produto</button>
                </a>
            </div>
	        <hr>
	        <h5><strong>Produtos contratados</strong></h5>
	        <div class="row">
                <table id="listaProduto" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
        <thead>
          <tr>
              <th class="th-sm">ID</th>
              <th class="th-sm">Título</th>
              <th class="th-sm">Status</th>
              <th class="th-sm">Início do Contrato</th>
              <th class="th-sm">Duração</th>
              <th class="th-sm">Ativo</th>
              <th class="th-sm">Ação</th>
          </tr>
        </thead>
  <tbody>
    <tr>
      <td>Título</td>
      <td>X horas</td>
    </tr>
  </tbody>
</table>
       </div>
	        
            <div class="form-group col-md-3">
                <div class="form-group col-md-3">
                    <a href="#"><button type="submit" class="btn btn-primary">+ Produto</button></a>
                </div>
            </div>
                <hr>
				<div class="row">
				    <a href="/gct/produto?acao=editar&id=1">
				        <button type="button" class="btn btn-primary">Editar</button>
			        </a>
			        <a href="/gct/produto?acao=excluir&id=1">
                        <button type="button" class="btn btn-warning">Excluir</button>
                    </a>
				     <a href="/gct/produto">
				        <button type="button" class="btn btn-secundary">Voltar</button>
			         </a>
				</div>
			

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>