<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Atividade"%>
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
        Atividade atividade = (Atividade) request.getAttribute("resultado");
    %>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h3>Atividade # <%= atividade.getId() %></h3>
			<p>Informações da atividade.</p>
		</div>
	</div>
	<hr>
	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
           <div class="col-md-8">
                   <h6><strong>Título</strong></h6> 
                   <span>
                   		<%= atividade.getTitulo() %>
                   </span>
           </div>
      </div>
      	<br>
        <div class="row">
           <div class="col-md-8">
                   <h6><strong>Descrição</strong></h6> 
                   <span>
                       <%= atividade.getDescricao() %> 
                   </span>
               </div>
        </div>
                   <br>
		
		  <div class="row">
                <div class="form-group col-md-2">
                    <h6><strong>Atribuido</strong></h6>
                    <span><%= atividade.getUsuarioAtribuido().getContato().getNome() %></span>
                </div>
                
                <div class="form-group col-md-2">
                    <h6><strong>Incluído por</strong></h6>
                    <span><%= atividade.getUsuarioInclusao().getContato().getNome() %></span>
                </div>
                
                <div class="form-group col-md-4">
                    <h6><strong>Tipo</strong></h6>
                    <span><%= atividade.getTipo().getDescricao() %></span>
                </div>
                
                <div class="form-group col-md-4">
                    <h6><strong>Status</strong></h6>
                    <span><%= atividade.getOcorrenciaStatus().getDescricao() %></span>
                </div>
            </div>
			<div class="row">
			     <div class="form-group col-md-2">
                    <h6><strong>Ativo</strong></h6> 
                    <span><%= atividade.getAtivo() > 0 ? "Sim" : "Não" %></span>
                </div>
	            <div class="form-group col-md-3">
	                <h6><strong>Início</strong></h6>
                    <span><%= atividade.getDataInicio() %></span>
	            </div>
	            
	            <div class="form-group col-md-3">
                    <h6><strong>Data finalização</strong></h6>
                    <span><%= atividade.getDataFim() %></span>
                </div>
	        </div>
	        
	        <div class="row">
               <div class="form-group col-md-4">
                    
                </div>
                
                <div class="form-group col-md-4">
                    
                </div>
            </div>
            
            <div class="row">
                <div class="form-group col-md-3">
                    
                </div>
            </div>
			
			<div class="row">
			     
			</div>	
				
				<div class="row">
				     <a class="col-md-1" href="/gct/atividade?acao=editar&id=<%= atividade.getId() %>"><button type="submit" class="btn btn-warning">Editar</button></a>
				     <a class="col-md-1" href="/gct/atividade?acao=alterar&id=<%= atividade.getId() %>&status=finalizado"><button type="button" class="btn btn-danger">Finalizar</button></a>
				     <a class="col-md-1" href="/gct/chamado?acao=exibir&id=<%= atividade.getChamado().getId()%>"><button type="button" class="btn btn-primary">Voltar ao Chamado</button></a>
				</div>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>