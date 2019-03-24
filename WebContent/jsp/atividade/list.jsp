<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="../fragmentos/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GCT - Gestão de Chamados Técnicos</title>
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
            <p>Lista de todas as atividades</p>
            <a href="../../jsp/atividade/form.jsp">Novo Contato</a>
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
		      <th class="th-sm">Titulo</th>
			  <th class="th-sm">Chamado</th>
	          <th class="th-sm">Cliente</th>
			  <th class="th-sm">Responsavel</th>
			  <th class="th-sm">Termino</th>
			  <th class="th-sm">Ação</th>
		  </tr>
		</thead>
  <tbody>
    <tr>
      <td>1</td>
      <td>Tirulo da Atividade</td>
      <td>Titulo do Chamado</td>
      <td>Nome do Cliente</td>
      <td>Nome do Responsável</td>
      <td>2019/04/25</td>
      <td>
        <a href="../../jsp/atividade/show.jsp">Visualizar</a>
      </td>
    </tr>
    <tr>
      <td>2</td>
      <td>Tirulo da Atividade</td>
      <td>Titulo do Chamado</td>
      <td>Nome do Cliente</td>
      <td>Nome do Responsável</td>
      <td>2019/04/25</td>
      <td>
        <a href="../../jsp/atividade/show.jsp">Visualizar</a>
      </td>
    </tr>
    <tr>
      <td>3</td>
      <td>Tirulo da Atividade</td>
      <td>Titulo do Chamado</td>
      <td>Nome do Cliente</td>
      <td>Nome do Responsável</td>
      <td>2019/04/25</td>
      <td>
        <a href="../../jsp/atividade/show.jsp">Visualizar</a>
      </td>
    </tr>
    <tr>
      <td>4</td>
      <td>Tirulo da Atividade</td>
      <td>Titulo do Chamado</td>
      <td>Nome do Cliente</td>
      <td>Nome do Responsável</td>
      <td>2019/04/25</td>
      <td>
        <a href="../../jsp/atividade/show.jsp">Visualizar</a>
      </td>
    </tr>
    <tr>
      <td>5</td>
      <td>Tirulo da Atividade</td>
      <td>Titulo do Chamado</td>
      <td>Nome do Cliente</td>
      <td>Nome do Responsável</td>
      <td>2019/04/25</td>
      <td>
        <a href="../../jsp/atividade/show.jsp">Visualizar</a>
      </td>
    </tr>
    <tr>
      <td>6</td>
      <td>Tirulo da Atividade</td>
      <td>Titulo do Chamado</td>
      <td>Nome do Cliente</td>
      <td>Nome do Responsável</td>
      <td>2019/04/25</td>
      <td>
        <a href="../../jsp/atividade/show.jsp">Visualizar</a>
      </td>
    </tr>
    <tr>
      <td>7</td>
      <td>Tirulo da Atividade</td>
      <td>Titulo do Chamado</td>
      <td>Nome do Cliente</td>
      <td>Nome do Responsável</td>
      <td>2019/04/25</td>
      <td>
        <a href="../../jsp/atividade/show.jsp">Visualizar</a>
      </td>
    </tr>
    <tr>
      <td>8</td>
      <td>Tirulo da Atividade</td>
      <td>Titulo do Chamado</td>
      <td>Nome do Cliente</td>
      <td>Nome do Responsável</td>
      <td>2019/04/25</td>
      <td>
        <a href="../../jsp/atividade/show.jsp">Visualizar</a>
      </td>
    </tr>
    <tr>
      <td>9</td>
      <td>Tirulo da Atividade</td>
      <td>Titulo do Chamado</td>
      <td>Nome do Cliente</td>
      <td>Nome do Responsável</td>
      <td>2019/04/25</td>
      <td>
        <a href="../../jsp/atividade/show.jsp">Visualizar</a>
      </td>
    </tr>
    
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