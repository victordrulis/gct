<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Chamado"%>
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
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Novo atividade</h2>
			<p>Inserir dados do atividade.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

		<form action="/gct/atividade?acao=salvar" method="post">
		  <div class="row">
                <div class="form-group col-md-6">
                    <label for="usuarioAtribuidoId"><strong>Usuario Atribuido</strong></label>
                    <select class="form-control" id="usuarioAtribuidoId" name="usuarioAtribuidoId">
                        <%
                           List<Usuario> listaUsuario = (List<Usuario>) request.getAttribute("listaUsuario");
                    		if(listaUsuario != null || !listaUsuario.isEmpty()) {
                           		for(Usuario usuarioAtribuido : listaUsuario) {
		                        	%>
		                                <option value="<%= usuarioAtribuido.getId() %>"><%= usuarioAtribuido.getContato().getNome() %></option>
		                        	<%
                           		}
                    		}
                        %>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label for="chamadoId"><strong>Chamado</strong></label>
                    <select class="form-control" id="chamadoId" name="chamadoId">
                        <%
                           List<Chamado> listaChamado = (List<Chamado>) request.getAttribute("listaChamado");
                           for(Chamado chamado : listaChamado) {
	                        	%>
	                                <option value="<%= chamado.getId() %>"><span>#<%= chamado.getId() %>: <%= chamado.getTitulo() %></span></option>
	                        	<%
                           }
                        %>
                    </select>
                </div>
            </div>
			<div class="row">
	            <div class="form-group col-md-8">
	                <label for="titulo"><strong>T�tulo</strong></label>
	                <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Titulo">
	            </div>
	        </div>
	        
	        <div class="row">
                <div class="form-group col-md-8">
	                <label for="descricao"><strong>Descricao</strong></label> 
	                <textarea class="form-control" id="descricao" name="descricao" rows="3"></textarea>
                </div>
                
	        </div>
	        
                 <div class="form-group col-md-4">
                    <label for="status"><strong>Status</strong></label>
                    <select class="form-control" id="status" name="status">
                        <%
                        	for(Map.Entry<Integer, OcorrenciaStatus> status : OcorrenciaStatus.getMapa().entrySet()) {
                        %>
	                                <option value="<%= status.getKey() %>"><%= status.getValue().getDescricao() %></option>
	                        	<%
                           }
                        %>
                    </select>
                </div>
                
                <div class="form-group col-md-4">
                    <label for="tipo"><strong>Tipo</strong></label>
                    <select class="form-control" id="tipo" name="tipo">
                        <%
                        	for(Map.Entry<Integer, OcorrenciaTipo> tipo : OcorrenciaTipo.getMapa().entrySet()) {
                        %>
	                                <option value="<%= tipo.getKey() %>"><%= tipo.getValue().getDescricao() %></option>
	                        	<%
                           }
                        %>
                    </select>
                </div>
            </div>
            
            <div class="row">
                <div class="form-group col-md-3">
                    <label for="dataAbertura"><strong>In�cio</strong></label>
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
				     <button type="submit" class="btn btn-success col-md-1" id="acao" name="acao" value="salvar">Salvar</button>
				     <a href="/gct/atividade"><button type="button" class="btn btn-secundary col-md-1">Cancelar</button></a>
				</div>
			
		</form>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>