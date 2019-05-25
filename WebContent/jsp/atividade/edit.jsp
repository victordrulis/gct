<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Atividade"%>
<%@page import="br.com.drulis.gct.dominio.Usuario"%>
<%@page import="br.com.drulis.gct.dominio.Chamado"%>
<%@page import="br.com.drulis.gct.dominio.Usuario"%>
<%@page import="br.com.drulis.gct.dominio.classificacao.OcorrenciaTipo"%>
<%@page import="br.com.drulis.gct.dominio.classificacao.OcorrenciaStatus"%>
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
	  Atividade atividade = (Atividade) request.getAttribute("resultado");
	%>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Alterar atividade - ID: <%= atividade.getId() %></h2>
			<p>Alterar ou atualizar dados do atividade.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->
		<form action="/gct/atividade?acao=alterar" method="post">
		<input type="hidden" id="id" name="id" value="<%= atividade.getId()%>">
            <div class="row">
                <div class="form-group col-md-8">
                    <label for="chamadoId"><strong>Chamado</strong></label>
                    <select class="form-control" id="chamadoId" name="chamadoId">
                        <%
                           List<Chamado> listaChamado = (List<Chamado>) request.getAttribute("listaChamado");
                           for(Chamado chamado : listaChamado) {
                        %>
                                <option value="<%= chamado.getId() %>"
                        <%
                        	if(chamado.getId() == atividade.getChamado().getId()) {
                        %>
                                selected="selected">
                        <%
                        	} else { %>> <% } %>
                                
                                <span> ID: <%= chamado.getId() %> - <%= chamado.getTitulo() %></span></option>
                        <%
                           }
                        %>
                    </select>
                </div>
            </div>
			<div class="row">
	            <div class="form-group col-md-8">
	                <label for="titulo"><strong>Título</strong></label>
	                <input type="text" class="form-control" id="titulo" name="titulo" value="<%= atividade.getTitulo() %>">
	            </div>
	        </div>
	        
	        <div class="row">
                <div class="form-group col-md-8">
	                <label for="descricao"><strong>Descricao</strong></label> 
	                <textarea class="form-control" id="descricao" name="descricao" rows="3"><%= atividade.getDescricao() %></textarea>
                </div>
                
	        </div>
	        
	        <div class="row">
	        
	           <div class="form-group col-md-4">
                    <label for="tipo"><strong>Tipo</strong></label>
                    <select class="form-control" id="tipo" name="tipo">
                    <%
                        for(OcorrenciaTipo tipo : OcorrenciaTipo.values()) {
                    %>
                        <option value="<%= tipo.getId() %>"
                        <%
                        	if(tipo.getId() == atividade.getTipo().getId()) {
                        %>		selected="selected">
                        <% } else { %> > <% } %>
                        	<%= tipo.getDescricao() %></option>
                   <%
                        }                    
                   %>
                    </select>
                </div>
                
                <div class="form-group col-md-4">
                    <label for="status"><strong>Status</strong></label>
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
                <div class="form-group col-md-6">
                    <label for="usuarioAtribuidoId"><strong>Usuario Atribuido</strong></label>
                    <select class="form-control" id="usuarioAtribuidoId" name="usuarioAtribuidoId">
                        <%
                           List<Usuario> listaUsuarioAtribuido = (List<Usuario>) request.getAttribute("listaUsuario");
                           for(Usuario usuarioAtribuido : listaUsuarioAtribuido) {
                        %>
                                <option value="<%= usuarioAtribuido.getId() %>" 
                                <% if(usuarioAtribuido.getId() == atividade.getUsuarioAtribuido().getId()) { %> selected> <% } else {%> > <% } %>
                                <%= usuarioAtribuido.getContato().getNome() %>  (CPF/CNPJ: <%= usuarioAtribuido.getContato().getCpfCnpj() %>)</option>
                        <%
                           }
                        %>
                    </select>
                </div>
                <div class="form-group col-md-2">
                    <label for="dataAbertura"><strong>Início</strong></label>
                    <input type="date" class="form-control" id="dataAbertura" name="dataAbertura" placeholder="00/00/0000">
                </div>
            </div>
			
			<div class="row">
			     <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="ativo" name="ativo" checked="checked">
                    <label class="form-check-label" for="ativo"><strong>Ativo</strong></label>
                </div>
			</div>	
				
				<div class="row">
				     <button type="submit" class="btn btn-primary col-md-1" id="acao" name="acao" value="alterar">Salvar</button>
				     <a href="/gct/atividade"><button type="button" class="btn btn-secundary col-md-1">Cancelar</button></a>
				</div>
			
		</form>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>