<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Contato"%>
<%@page import="br.com.drulis.gct.dominio.Atividade"%>
<%@page import="br.com.drulis.gct.dominio.Chamado"%>
<%@page import="br.com.drulis.gct.dominio.Usuario"%>
<%@page import="java.util.List"%>
<%@include file="../fragmentos/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GCT - Atividades</title>
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
            <h2>Atividades</h2>
            <p>Todos os atividades cadastrados.</p>
            <a href="/gct/atividade?acao=novo">Nova Atividade</a>
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
    <table id="listaAtividade" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
		<thead>
		  <tr>
		      <th class="th-sm">ID</th>
		      <th class="th-sm">Título</th>
		      <th class="th-sm">UsuarioAtribuido</th>
			  <th class="th-sm">Chamado</th>
	          <th class="th-sm">Atribuido</th>
	          <th class="th-sm">Status</th>
			  <th class="th-sm">Ativo</th>
			  <th class="th-sm">Ação</th>
		  </tr>
		</thead>
  <tbody>
    <%
       List<Atividade> resultado = (List<Atividade>) request.getAttribute("resultado");
   
       if(resultado != null) {
           for(Atividade atividade : resultado) {
   %>
    <tr>
      <td><%= atividade.getId() %></td>
      <td><%= atividade.getTitulo() %></td>
      <td><%= atividade.getChamado() %></td>
      <td><%= atividade.getUsuarioAtribuido().getId() %></td>
      <td>Usuario Atribuido 1</td>
<%--       <td><%= atividade.getUsuarioAtribuido().getContato().getNome() %></td> --%>
      <td>Tipo atividade</td>
      <td><%= atividade.getAtivo() %></td>
      <td>
            <a href="/gct/atividade?acao=exibir&id=<%= atividade.getId() %>"><span>Visualizar</span></a>
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