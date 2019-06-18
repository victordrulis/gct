<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Contato"%>
<%@page import="br.com.drulis.gct.dominio.Chamado"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../fragmentos/header.jsp" %>
<meta charset="ISO-8859-1">
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
            <h2>Chamados</h2>
            <p>Todos os chamados cadastrados.</p>
            <a href="/gct/chamado?acao=novo"><button type="button" class="btn btn-success">Novo</button></a>
        </div>
    </div>
    <hr>
<div class="container">
<div class="row">
	<div class="col-sm-12 col-md-6">
	    <div id="dtBasicExample_filter" class="dataTables_filter">
	        <label>Busca:<input id="busca" name="busca" type="search" class="form-control" placeholder="" aria-controls="dtBasicExample"></label>
	    </div>
    </div>
    <div class="col-sm-12 col-md-6">
	    <div class="dataTables_length bs-select" id="dtBasicExample_length" >
	        <label>Registros por página <select name="dtBasicExample_length" aria-controls="dtBasicExample" class="custom-select custom-select-sm form-control form-control-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select></label>
	    </div>
    </div>
</div>
    <table id="tabela" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
		<thead>
		  <tr>
		      <th class="th-sm">ID</th>
		      <th class="th-sm">Título</th>
		      <th class="th-sm">Cliente</th>
			  <th class="th-sm">Produto</th>
	          <th class="th-sm">Atribuido</th>
	          <th class="th-sm">Status</th>
			  <th class="th-sm">Ativo</th>
			  <th class="th-sm">Ação</th>
		  </tr>
		</thead>
  <tbody>
    <%
       List<Chamado> resultado = (List<Chamado>) request.getAttribute("resultado");
   
       if(resultado != null) {
           for(Chamado chamado : resultado) {
   %>
    <tr>
      <td align="center"><%= chamado.getId() %></td>
      <td><%= chamado.getTitulo() %></td>
      <td><a href="/gct/cliente?acao=exibir&id=<%= chamado.getCliente().getId() %>"><%= chamado.getCliente().getContato().getNome() %></a></td>
      <td><a href="/gct/produto?acao=exibir&id=<%= chamado.getProduto().getId() %>"><span><%= chamado.getProduto().getTitulo() %> (ID: <%= chamado.getProduto().getId() %>)</span></td>
      <td><%= chamado.getUsuarioAtribuido().getContato().getNome() %></td>
      <td align="center"><%= chamado.getOcorrenciaStatus().getDescricao() %></td>
      <td align="center"><%= chamado.getAtivo() > 0 ? "Sim" : "-" %></td>
      <td align="center">
            <a href="/gct/chamado?acao=exibir&id=<%= chamado.getId() %>"><button type="button" class="btn btn-primary">Visualizar</button></a>
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
</body>
</html>