<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Produto"%>
<%@page import="java.util.List"%>
<%@include file="../fragmentos/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GCT - Produtos</title>
<style type="text/css">
.conteudo-topo {
    padding-top: 40px;
}
</style>
</head>
<body>
<%@include file="../fragmentos/nav.jsp" %>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
<div class="row conteudo-topo">
        <div class="container">
            <h2>Produtos</h2>
            <p>Lista de produtos</p>
            <a href="/gct/produto?acao=novo"><button type="button" class="btn btn-success">Novo</button></a>
        </div>

    </div>
    <hr>
<div class="container">
<div class="row">
	<div class="col-sm-12 col-md-6">
	    <div id="dtBasicExample_filter" class="dataTables_filter">
	        <label>Busca:<input type="search" class="form-control" placeholder="" aria-controls="dtBasicExample"></label>
	    </div>
    </div>
    <div class="col-sm-12 col-md-6">
	    <div class="dataTables_length bs-select" id="dtBasicExample_length" >
	        <label>Registros por página <select name="dtBasicExample_length" aria-controls="dtBasicExample" class="custom-select custom-select-sm form-control form-control-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select></label>
	    </div>
    </div>
</div>
    
    <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
		<thead>
		  <tr>
		      <th class="th-sm">ID</th>
		      <th class="th-sm">Título</th>
		      <th class="th-sm">Descricao</th>
			  <th class="th-sm">Cadastrado em</th>
			  <th class="th-sm">Ativo</th>
			  <th class="th-sm">Ação</th>
		  </tr>
		</thead>
  <tbody>
   <%
       List<Produto> resultado = (List<Produto>) request.getAttribute("resultado");
   
       if(resultado != null) {
           for(Produto produto : resultado) {
   %>
      <tr>
          <td><%= produto.getId() %></td>
          <td><%= produto.getTitulo() %></td>
          <td><%= produto.getDescricao() %></td>
          <td><%= produto.getDataInclusao() %></td>
          <td><%= produto.getAtivo() %></td>
          <td>
            <span><a href="/gct/produto?acao=exibir&id=<%= produto.getId() %>"><button type="button" class="btn btn-primary">Visualizar</button></a></span>
            <span><a href="/gct/chamado?acao=novo&produtoId=<%= produto.getId() %>"><button type="button" class="btn btn-warning">Chamado</button></a></span>
          </td>
      </tr>
      <%
              }
          }
      %>  
  </tbody>
</table>
</div>
</main>
<%@include file="../fragmentos/footer.jsp" %>

<script>
//Basic example
$(document).ready(function () {
  $('#dtBasicExample').DataTable({
    "paging": false // false to disable pagination (or any other option)
  });
  $('.dataTables_length').addClass('bs-select');
});
</script>
</body>
</html>