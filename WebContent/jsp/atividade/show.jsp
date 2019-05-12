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
                <div class="form-group col-md-4">
                    <h6><strong>Atribuido</strong></h6>
                    <span><%= atividade.getUsuarioAtribuido().getId() %></span>
                </div>
                
                <div class="form-group col-md-4">
                    <h6><strong>Tipo</strong></h6>
                    <span><%= atividade.getTipo().getDescricao() %></span>
                </div>
                
                <div class="form-group col-md-4">
                    <h6><strong>Status</strong></h6>
                    <span><%= atividade.getStatus() %></span>
                </div>
            </div>
			<div class="row">
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
                <div class="form-group col-md-8">
                    
                    
                    <table>
                        <thead>
	                        <tr>
	                            <th><h6><strong>Interações</strong></h6></th>
	                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Contato</td>
                                <td>Descricao da interacao na atividade. Informacao informacao informacao informacao</td>
                            </tr>
                            <tr>
                                <td>Contato</td>
                                <td>Descricao da interacao na atividade. Informacao informacao informacao informacao</td>
                            </tr>
                            <tr>
                                <td>Contato</td>
                                <td>Descricao da interacao na atividade. Informacao informacao informacao informacao</td>
                            </tr>
                            <tr>
                                <td>Contato</td>
                                <td>Descricao da interacao na atividade. Informacao informacao informacao informacao</td>
                            </tr>
                            
                        </tbody>
                    </table>
                    <hr>
                    <form>
                        <h6><strong>Nova Interação</strong></h6>
		                <label for="interacao">Descrição</label> 
		                <textarea class="form-control" id="interacao" rows="3"></textarea>
		                
	                     <button type="submit" class="btn btn-primary col-md-2">Salvar</button>
	                     <button type="submit" class="btn btn-secundary col-md-2">Cancelar</button>
                
	                </form>
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
				     <a class="col-md-1" href="../../jsp/atividade/edit.jsp"><button type="submit" class="btn btn-primary">Editar</button></a>
				     <a class="col-md-1" href="../../jsp/atividade/list.jsp"><button type="submit" class="btn btn-primary">Finalizar</button></a>
				</div>
			

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>