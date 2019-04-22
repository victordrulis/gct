<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Contato"%>
<%@page import="br.com.drulis.gct.dominio.Contrato"%>
<%@page import="java.util.List"%>
<%@include file="../fragmentos/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GCT - Contratos</title>
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
            <h2>Contratos</h2>
            <p>Todos os contratos cadastrados.</p>
            <a href="/gct/contrato?acao=novo">Novo Contrato</a>
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
    <table id="listaContrato" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
		<thead>
		  <tr>
		      <th class="th-sm">ID</th>
		      <th class="th-sm">Nome</th>
		      <th class="th-sm">SLA</th>
			  <th class="th-sm">Início do Contrato</th>
	          <th class="th-sm">Duração</th>
			  <th class="th-sm">Ativo</th>
			  <th class="th-sm">Produtos</th>
			  <th class="th-sm">Ação</th>
		  </tr>
		</thead>
  <tbody>
    <%
       List<Contrato> resultado = (List<Contrato>) request.getAttribute("resultado");
   
       if(resultado != null) {
           for(Contrato contrato : resultado) {
   %>
    <tr>
      <td><%= contrato.getId() %></td>
      <td><%= contrato.getCliente().getContato().getNome() %></td>
      <td><%= contrato.getCliente().getSla() %></td>
      <td><%= contrato.getProduto().getTitulo() %></td>
      <td><%= contrato.getDataInicio() %></td>
      <td><%= contrato.getDataFim() %></td>
      <td><%= contrato.getAtivo() %></td>
      <td>
            <ul>
                <li>Produto 1</li>
                <li>Produto 2</li>
            </ul>
      </td>
      <td>
            <a href="/gct/contrato?acao=exibir&id=<%= contrato.getId() %>"><span>Visualizar</span></a>
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