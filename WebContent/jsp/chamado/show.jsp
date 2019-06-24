<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Chamado"%>
<%@page import="br.com.drulis.gct.dominio.Atividade"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat" %>
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
        Chamado chamado = (Chamado) request.getAttribute("resultado");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    %>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Chamado #<%= chamado.getId() %></h2>
			<p>Dados de chamado.</p>
		</div>

	</div>
	<div class="container">
		<div class="row">
	    <div class="form-group">
		     <a href="/gct/chamado?acao=editar&id=<%= chamado.getId() %>"><button type="button" class="btn btn-warning">Editar</button></a>
	     </div>
	                <div class=" ">
		     <a href="/gct/chamado?acao=excluir&id=<%= chamado.getId() %>"><button type="button" class="btn btn-danger">Excluir</button></a>
	     </div>
	     
	     <div class=" ">
              <button type="button" class="btn btn-secondary" onclick="goBack()">Voltar</button>
           </div>
	    </div>
			<hr>
			<div class="row">
	        	<h5>Cliente</h5>
        	</div>
        	<div class="row">
	        	<div class="col-md-fluid">
	        	<table id="listaCliente" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
				<thead>
				  <tr>
				      <th class="th-sm">ID</th>
				      <th class="th-sm">Nome</th>
				      <th class="th-sm">E-mail</th>
				      <th class="th-sm">Telefone</th>
				      <th class="th-sm">SLA</th>
					  <th class="th-sm">Ativo</th>
					  <th class="th-sm">Ação</th>
				  </tr>
				</thead>
		  		<tbody>
		  			<tr>
				      <td><%= chamado.getCliente().getId() %></td>
				      <td><%= chamado.getCliente().getContato().getNome() %></td>
				      <td><%= chamado.getCliente().getContato().getEmail() %></td>
				      <td><%= chamado.getCliente().getContato().getTel() %></td>
				      <td><%= chamado.getCliente().getSla() %></td>
				      <td><%= chamado.getCliente().getAtivo() > 0 ? "Sim" : "-" %></td>
				      <td>
				            <a href="/gct/cliente?acao=exibir&id=<%= chamado.getCliente().getId() %>"><span>Visualizar</span></a>
				      </td>
				    </tr>
		  		</tbody>
	  			</table>
	        	
                </div>
            </div>
	        <hr>
			
           <div class="row">
           		<h5>Produto</h5>
           	</div>
           	<div class="row">
           		<div class="form-group col-md-fluid">
           		 <table id="produto" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
					<thead>
					  <tr>
					      <th class="th-sm">ID</th>
					      <th class="th-sm">Título</th>
						  <th class="th-sm">Status</th>
						  <th class="th-sm">Ativo</th>
						  <th class="th-sm">Ação</th>
					  </tr>
					</thead>
  					<tbody>
  					<tr>
				          <td><%= chamado.getProduto().getId() %></td>
				          <td><%= chamado.getProduto().getTitulo() %></td>
				          <td><%= chamado.getProduto().getStatus() %></td>
				          <td><%= chamado.getProduto().getAtivo() > 0 ? "Sim" : "-"%></td>
				          <td>
				            <a href="/gct/produto?acao=exibir&id=<%= chamado.getProduto().getId() %>"><span>Visualiar</span></a>
				          </td>
				      </tr>
			      </tbody>
		      </table>
                </div>     
	        </div>
			<hr>
	        
			<div class="row">
				<h5>Informações</h5>
			</div>
			<div class="row">
				<div class="form-group col-md-2">
                    <h6><strong>Título</strong></h6><p> <%= chamado.getTitulo() %></p>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-2">
                    <h6><strong>Tipo</strong></h6> 
                    <span><%= chamado.getTipo().getDescricao() %></span>
                </div>
                
                <div class="form-group col-md-2">
                    <h6><strong>Status</strong></h6> 
                    <span><%= chamado.getOcorrenciaStatus().getDescricao() %></span>
                </div>
                <div class="form-group col-md-2">
                    <h6><strong>Ativo</strong></h6> 
                    <span><%= chamado.getAtivo() > 0 ? "Sim" : "Não" %></span>
                </div>
            </div>
			<div class="row">
				<div class="form-group col-md-4">
	                <h6><strong>Descrição: </strong></h6>
	                <p><%= chamado.getDescricao() %></p>
                </div>
	        </div>

	        <div class="row">
                <div class="form-group col-md-2">
                    <h6><strong>Incluído</strong></h6> 
                    <span><%= chamado.getUsuarioInclusao().getContato().getNome() %></span>
                </div>
                
                <div class="form-group col-md-2">
                    <h6><strong>Atribuido</strong></h6> 
                    <span><%= chamado.getUsuarioAtribuido().getContato().getNome() %></span>
                </div>
                
                <div class="form-group col-md-2">
                    <h6><strong>Data inclusão</strong></h6> 
                    <span><%= chamado.getDataInclusao() %></span>
                </div>
                
                <div class="form-group col-md-2">
                    <h6><strong>Data alteração</strong></h6> 
                    <span><%= chamado.getDataAlteracao() != null ? chamado.getDataAlteracao() : "-" %></span>
                </div>
            </div>
            <hr>
           	<div class="row">
	            <div class="form-group col-md-12">
	                <h5><strong>Atividades</strong></h5>
	                
	                <a href="/gct/atividade?acao=novo"><button type="button" class="btn btn-success">Nova</button></a>
	                
	             <table id="listaAtividade" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
					<thead>
					  <tr>
					      <th class="th-sm">ID</th>
					      <th class="th-sm">Título</th>
					      <th class="th-sm">UsuarioAtribuido</th>
					      <th class="th-sm">Tipo</th>
				          <th class="th-sm">Status</th>
						  <th class="th-sm">Ativo</th>
						  <th class="th-sm">Ação</th>
					  </tr>
					</thead>
			  <tbody>   
            <%
            	List<Atividade> listaAtividade = (List<Atividade>) request.getAttribute("listaAtividades");
            	if(listaAtividade == null || listaAtividade.isEmpty()) {
        	%>
	           		<tr>
            			<td align="center" colspan="12"><span>Não há atividades.</span></td>
           			</tr>
        		<%
            	} else {
            		for(Atividade atividade : listaAtividade) {
            %>
            		<tr>
	                    <td align="center"><span><%= atividade.getId() %></span></td>
	                    <td><span><%= atividade.getTitulo() %></span></td>
	                    <td><span><%= atividade.getUsuarioAtribuido().getContato().getNome() %></span></td>
	                    <td><span><%= atividade.getTipo().getDescricao() %></span></td>
	                    <td><span><%= atividade.getOcorrenciaStatus().getDescricao() %></span></td>
	                    <td><span><%= atividade.getAtivo() > 0 ? "Sim" : "-" %></span></td>
	                    <td>
	                    	<a href="/gct/atividade?acao=exibir&id=<%= atividade.getId() %>"> Visualizar </a>
	                    </td>
                    </tr>
           <% 
           			}
            	}
       		%>
     			</tr>
  			</tbody>
		</table>
		            </div>
	            </div>
	            
           <hr> 
			<div class="row">
				    <div class="form-group">
					     <a href="/gct/chamado?acao=editar&id=<%= chamado.getId() %>"><button type="button" class="btn btn-warning">Editar</button></a>
				     </div>
                    <div class="form-group ">
					     <a href="/gct/chamado?acao=excluir&id=<%= chamado.getId() %>"><button type="button" class="btn btn-danger">Excluir</button></a>
				     </div>
				     
				     <div class="form-group ">
                         <button type="button" class="btn btn-secondary" onclick="goBack()">Voltar</button>
                     </div>
		     </div>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
	<script>
function goBack() {
  window.history.back();
}
</script>
</body>
</html>