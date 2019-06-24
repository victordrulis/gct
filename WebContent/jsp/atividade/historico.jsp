<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Contato"%>
<%@page import="br.com.drulis.gct.dominio.Atividade"%>
<%@page import="br.com.drulis.gct.dominio.Chamado"%>
<%@page import="br.com.drulis.gct.dominio.Usuario"%>
<%@page import="java.text.SimpleDateFormat" %>
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
 <%
       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       List<Atividade> resultado = (List<Atividade>) request.getAttribute("resultado");
   %>
   
<div class="row conteudo-topo">
        <div class="container">
            <h2>Histórico - Atividades #<%= resultado.get(0).getId() %></h2>
            <p>Histórico da atividade cadastrada.</p>
            <button type="button" class="btn btn-warning" onclick="goBack()">Voltar</button>
        </div>
    </div>
    <hr>
<div class="container">
<div class="row">
	<div class="col-sm-12 col-md-6">
	    <div id="dtBasicExample_filter" class="dataTables_filter">
	        <label>Busca:<input id="busca" type="search" class="form-control" placeholder="" aria-controls="dtBasicExample"></label>
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
			  <th class="th-sm">Descricao</th>
			  <th class="th-sm">Data Inicio</th>
			  <th class="th-sm">Data Fim</th>
	          <th class="th-sm">Usr Atribuido</th>
			  <th class="th-sm">Tipo</th>
	          <th class="th-sm">Status</th>
			  <th class="th-sm">Ativo</th>
			  <th class="th-sm">Data Inclusão</th>
			  <th class="th-sm">Data Alteracao</th>
		  </tr>
		</thead>
  <tbody>
	  <%
	       if(resultado != null) {
	           for(Atividade atividade : resultado) {
	   %>
    <tr>
      <td><%= atividade.getId() %></td>
      <td><%= atividade.getTitulo() %></td>
      <td><span><%= atividade.getDescricao() %></span></td>
      <td><%= atividade.getDataInicio() != null ? dateFormat.format(atividade.getDataInicio()) : "-" %></td>
      <td><%= atividade.getDataFim() != null ? dateFormat.format(atividade.getDataFim()) : "-" %></td>
      <td><span><%= atividade.getUsuarioAtribuido().getLogin() %></span></td>
      <td><%= atividade.getTipo().getDescricao() %></td>
      <td><%= atividade.getOcorrenciaStatus().getDescricao() %></td>
      <td><%= atividade.getAtivo() > 0 ? "Sim" : "-" %></td>
      <td><%= dateFormat.format(atividade.getDataInclusao()) %></td>
      <td><%= atividade.getDataAlteracao() != null ? dateFormat.format(atividade.getDataAlteracao()) : "-" %></td>
    </tr>
    <%
           }
       }
    %>
  </tbody>
</table>
<div class="row">
	<button type="button" class="btn btn-warning" onclick="goBack()">Voltar</button>
</div>
</div>
</main>

<%@include file="../fragmentos/footer.jsp" %>
<script>
function goBack() {
  window.history.back();
}
</script>
</body>
</html>