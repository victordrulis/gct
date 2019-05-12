<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Chamado"%>
<%@page import="br.com.drulis.gct.dominio.Atividade"%>
<%@page import="java.util.List"%>
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
    %>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Chamado - ID: <%= chamado.getId() %></h2>
			<p>Dados de chamado.</p>
		</div>

	</div>
	<div class="container">
		<div class="row">
	    <div class="form-group">
		     <a href="/gct/chamado?acao=editar&id=<%= chamado.getId() %>"><button type="button" class="btn btn-primary">Editar</button></a>
	     </div>
	                <div class=" ">
		     <a href="/gct/chamado?acao=excluir&id=<%= chamado.getId() %>"><button type="button" class="btn btn-warning">Excluir</button></a>
	     </div>
	     
	     <div class=" ">
              <a href="/gct/chamado"><button type="button" class="btn btn-secundary">Voltar</button></a>
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
				      <th class="th-sm">SLA</th>
					  <th class="th-sm">Início do Contrato</th>
			          <th class="th-sm">Duração</th>
					  <th class="th-sm">Ativo</th>
					  <th class="th-sm">Produtos</th>
					  <th class="th-sm">Ação</th>
				  </tr>
				</thead>
		  		<tbody>
		  			<tr>
				      <td><%= chamado.getCliente().getId() %></td>
				      <td><%= chamado.getCliente().getContato().getNome() %></td>
				      <td><%= chamado.getCliente().getSla() %></td>
				      <td><%= chamado.getCliente().getDataInclusao() %></td>
				      <td><%= chamado.getCliente().getDuracaoContrato() %></td>
				      <td><%= chamado.getCliente().getAtivo() %></td>
				      <td>
				            <ul>
				                <li>Produto 1</li>
				                <li>Produto 2</li>
				            </ul>
				      </td>
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
           		 <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
					<thead>
					  <tr>
					      <th class="th-sm">ID</th>
					      <th class="th-sm">Título</th>
						  <th class="th-sm">Cadastrado em</th>
						  <th class="th-sm">Status</th>
						  <th class="th-sm">Ativo</th>
						  <th class="th-sm">Ação</th>
					  </tr>
					</thead>
  					<tbody>
  					<tr>
				          <td><%= chamado.getProduto().getId() %></td>
				          <td><%= chamado.getProduto().getTitulo() %></td>
				          <td><%= chamado.getProduto().getDataInclusao() %></td>
				          <td><%= chamado.getProduto().getStatus() %></td>
				          <td><%= chamado.getProduto().getAtivo() %></td>
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
                    <span><%= chamado.getStatus() %></span>
                </div>
            </div>
			<div class="row">
				<div class="form-group col-md">
	                <h6><strong>Descrição: </strong></h6>
	                <p><%= chamado.getDescricao() %></p>
                </div>
	        </div>

	        <div class="row">
                <div class="form-group col-md-2">
                    <h6><strong>Atribuido</strong></h6> 
                    <span>Id = <%= chamado.getUsuarioAtribuido().getId() %></span>
                </div>
                
                <div class="form-group col-md-2">
                    <h6><strong>Data inclusão</strong></h6> 
                    <span><%= chamado.getDataInclusao() %></span>
                </div>
                
                <div class="form-group col-md-2">
                    <h6><strong>Data alteração</strong></h6> 
                    <span><%= chamado.getDataAlteracao() %></span>
                </div>
            </div>
            <hr>
           	<div class="row">
	            <div class="form-group col-md-12">
	                <h5><strong>Atividades</strong></h5>
	                
	                <a href="/gct/atividade?acao=novo"><button type="button" class="btn btn-primary">Nova</button></a>
	                
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
	                    <td><span><%= atividade.getStatus() %></span></td>
	                    <td><span><%= atividade.getAtivo() %></span></td>
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
					     <a href="/gct/chamado?acao=editar&id=<%= chamado.getId() %>"><button type="button" class="btn btn-primary">Editar</button></a>
				     </div>
                    <div class="form-group ">
					     <a href="/gct/chamado?acao=excluir&id=<%= chamado.getId() %>"><button type="button" class="btn btn-warning">Excluir</button></a>
				     </div>
				     
				     <div class="form-group ">
                         <a href="/gct/chamado"><button type="button" class="btn btn-secundary">Voltar</button></a>
                     </div>
		     </div>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>