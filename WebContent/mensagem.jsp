<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="jsp/fragmentos/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GCT - Contatos</title>
<style type="text/css">
.conteudo-topo {
    padding-top: 40px;
}
</style>
</head>
<body>
<%@include file="jsp/fragmentos/nav.jsp" %>

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
<div class="row conteudo-topo">
        <div class="container">
            <h2>Gestão de Chamados Técnicos</h2>
        </div>

    </div>
    <hr>
<div class="container">
<div class="row">
    <div class="col-sm-12 col-md-6">
        
        <%
        	String mensagem = (String) request.getAttribute("resultado");
            
        %>
        <h5><strong>Mensagem:</strong></h5>
        <span><p><%= mensagem %></p></span> 
        <hr>
        <button type="button" class="btn btn-warning" onclick="goBack()">Voltar</button>

    </div>
</div>

</div>
</main>
<%@include file="jsp/fragmentos/footer.jsp" %>
<script>
function goBack() {
  window.history.back();
}
</script>
</body>
</html>