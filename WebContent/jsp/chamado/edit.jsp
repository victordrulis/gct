<%@page import="com.itextpdf.text.pdf.events.IndexEvents.Entry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Chamado"%>
<%@page import="br.com.drulis.gct.dominio.Cliente"%>
<%@page import="br.com.drulis.gct.dominio.Produto"%>
<%@page import="br.com.drulis.gct.dominio.Usuario"%>
<%@page import="br.com.drulis.gct.dominio.classificacao.OcorrenciaTipo"%>
<%@page import="br.com.drulis.gct.dominio.classificacao.OcorrenciaStatus"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
			<h2>Alterar chamado - ID: <%= chamado.getId() %></h2>
			<p>Alterar ou atualizar dados do chamado.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->
		<form action="/gct/chamado?acao=alterar" method="post">
		<input type="hidden" id="id" name="id" value="<%= chamado.getId()%>">
		  <div class="row">
                <div class="form-group col-md-6">
                    <label for="clienteId">Cliente</label>
                    <select class="form-control" id="clienteId" name="clienteId">
                        <%
                           List<Cliente> listaCliente = (List<Cliente>) request.getAttribute("listaCliente");
                           for(Cliente cliente : listaCliente) {
                        %>
                                <option value="<%= cliente.getId() %>" 
                                <% if(cliente.getId() == chamado.getCliente().getId()) { %> selected="selected"> <% } else {%> > <% } %>
                                <%= cliente.getContato().getNome() %>  (CPF/CNPJ: <%= cliente.getContato().getCpfCnpj() %>)</option>
                        <%
                           }
                        %>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label for="produtoId">Produto</label>
                    <select class="form-control" id="produtoId" name="produtoId">
                        <%
                           List<Produto> listaProduto = (List<Produto>) request.getAttribute("listaProduto");
                           for(Produto produto : listaProduto) {
                        %>
                                <option value="<%= produto.getId() %>"
                                <% if(produto.getId() == chamado.getProduto().getId()) { %> selected="selected"> <% } else {%> > <% } %>
                                <%= produto.getTitulo() %></option>
                        <%
                           }
                        %>
                    </select>
                </div>
            </div>
			<div class="row">
	            <div class="form-group col-md-8">
	                <label for="titulo">T�tulo</label>
	                <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Titulo">
	            </div>
	        </div>
	        
	        <div class="row">
	        <div class="form-group col-md-4">
                    <label for="usuarioAtribuidoId">Atribuido:</label>
                    <select class="form-control" id="usuarioAtribuidoId" name="usuarioAtribuidoId">
                        <%
                           List<Usuario> listaUsuario = (List<Usuario>) request.getAttribute("listaUsuario");
                        if(listaUsuario == null || listaUsuario.size() < 1) {
                        %>
                        
                            <option value="0">Selecione um usu�rio...</option>
                        <%
                        } else {
                           for(Usuario usuario : listaUsuario) {
                        %>
                                <option value="<%= usuario.getId() %>"><%= usuario.getLogin() %></option>
                        <%
                           }
                        }
                        %>
                    </select>
                </div>
               
	        </div>
	        
	        <div class="row">
                <div class="form-group col-md-8">
	                <label for="descricao">Descricao</label> 
	                <textarea class="form-control" id="descricao" name="descricao" rows="3"></textarea>
                </div>
                
	        </div>
	        
	        <div class="row">
               
               <div class="form-group col-md-3">
                    <label for="tipo">Tipo</label>
                    <select class="form-control" id="tipo" name="tipo">
                    <%
                        for(OcorrenciaTipo tipo : OcorrenciaTipo.values()) {
                    %>
                        <option value="<%= tipo.getId() %>"><%= tipo.getDescricao() %></option>
                   <%
                        }                    
                   %>
                    </select>
                </div>
                 
                <div class="form-group col-md-4">
                    <label for="status">Status</label>
                    <select class="form-control" id="status" name="status">
                        <%
                        for(OcorrenciaStatus status : OcorrenciaStatus.values()) {
	                    %>
	                        <option value="<%= status.getId() %>"><%= status.getDescricao() %></option>
	                   <%
                        }                    
	                   %>
                    </select>
                </div>
            </div>
            
            <div class="row">
                <div class="form-group col-md-3">
                    <label for="dataAbertura">In�cio</label>
                    <input type="date" class="form-control" id="dataAbertura" name="dataAbertura" placeholder="00/00/0000">
                </div>
            </div>
			
			<div class="row">
			     <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="ativo" name="ativo" checked="checked">
                    <label class="form-check-label" for="ativo">Ativo</label>
                </div>
			</div>	
				
				<div class="row">
				     <button type="submit" class="btn btn-primary col-md-1" id="acao" name="acao" value="alterar">Salvar</button>
				     <a href="/gct/chamado"><button type="button" class="btn btn-secundary col-md-1">Cancelar</button></a>
				</div>
			
		</form>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>