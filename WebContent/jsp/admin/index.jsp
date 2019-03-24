<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="../fragmentos/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GCT - Gestão de Chamados Técnicos]</title>
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
            <h2>Configuração</h2>
            <p>Configuração do sistema</p>
        </div>
    </div>
    <hr>
<div class="container">
<form action="">
	<div class="row">
	    <div class="form-group col-md-4">
	         <label for="bdHost">Host Banco de Dados</label> 
	         <input type="text" class="form-control" id="bdHost" placeholder="192.168.0.1">
	    </div>
	    
	    <div class="form-group col-md-2">
	         <label for="bdPorta">Porta</label> 
	         <input type="text" class="form-control" id="bdPorta" placeholder="3360">
	    </div>
	</div>
	
	<div class="row">
	    <div class="form-group col-md-6">
	        <label for="appDominio">Dominio da aplicação</label> 
             <input type="text" class="form-control" id="appDominio" placeholder="https://www.dominio.com.br">
	    
	    </div>
	</div>
	<div class="row">
        <div class="form-group col-md-3">
	<button type="submit" class="btn btn-primary">Salvar</button>
	<button type="submit" class="btn btn-secundary">Cancelar</button>
	</div>
	</div>
	</form>
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