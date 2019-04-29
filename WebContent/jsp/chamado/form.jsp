<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.drulis.gct.dominio.Cliente"%>
<%@page import="br.com.drulis.gct.dominio.Produto"%>
<%@page import="br.com.drulis.gct.dominio.Usuario"%>
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
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="row conteudo-topo">
		<div class="container">
			<h2>Novo chamado</h2>
			<p>Inserir dados do chamado.</p>
		</div>

	</div>
	<div class="container">
		<!-- Example row of columns -->

		<form>
		  <div class="row">
                <div class="form-group col-md-6">
                    <label for="clienteId">Cliente</label>
                    <select class="form-control" id="clienteId" name="clienteId">
                        <%
                           List<Cliente> listaCliente = (List<Cliente>) request.getAttribute("listaCliente");
                           for(Cliente cliente : listaCliente) {
                        %>
                                <option value="<%= cliente.getId() %>"><%= cliente.getContato().getNome() %>  (CPF/CNPJ: <%= cliente.getContato().getCpfCnpj() %>)</option>
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
                                <option value="<%= produto.getId() %>"><%= produto.getTitulo() %></option>
                        <%
                           }
                        %>
                    </select>
                </div>
            </div>
			<div class="row">
	            <div class="form-group col-md-8">
	                <label for="titulo">Título</label>
	                <input type="text" class="form-control" id="titulo" placeholder="Titulo">
	            </div>
	        </div>
	        
	        <div class="row">
	           <div class="form-group col-md-3">
                    <label for="tipo">Tipo</label>
                    <select class="form-control" id="tipo">
                        <option>Manutenção</option>
                        <option>Melhoria</option>
                        <option>Alteração</option>
                        <option>Configuração</option>
                        <option>Adequação</option>
                    </select>
                </div>
	        </div>
	        
	        <div class="row">
                <div class="form-group col-md-8">
	                <label for="descricao">Descricao</label> 
	                <textarea class="form-control" id="descricao" rows="3"></textarea>
                </div>
                
	        </div>
	        
	        <div class="row">
               <div class="form-group col-md-4">
                    <label for="usuarioAtribuidoId">Atribuido:</label>
                    <select class="form-control" id="usuarioAtribuidoId" name="usuarioAtribuidoId">
                        <%
                           List<Usuario> listaUsuario = (List<Usuario>) request.getAttribute("listaUsuario");
                        if(listaUsuario != null) {
                       	%>
                       	
                       	    <option value="0">Selecione um usuário...</option>
                       	<%
                        } else {
                           for(Usuario usuario : listaUsuario) {
                        %>
                                <option value="<%= usuario.getId() %>"><%= usuario.getContato().getNome() %></option>
                        <%
                           }
                        }
                        %>
                    </select>
                </div>
                
                <div class="form-group col-md-4">
                    <label for="contato">Status</label>
                    <select class="form-control" id="contato">
                        <option>Atribuido</option>
                        <option>Em execução</option>
                        <option>Aguardando</option>
                        <option>Finalizado</option>
                        <option>Cancelado</option>
                    </select>
                </div>
            </div>
            
            <div class="row">
                <div class="form-group col-md-3">
                    <label for="dataInicio">Início</label>
                    <input type="text" class="form-control" id="dataInicio" placeholder="00/00/0000">
                </div>
            </div>
			
			<div class="row">
			     <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="ativo" checked="checked">
                    <label class="form-check-label" for="stivo">Ativo</label>
                </div>
			</div>	
				
				<div class="row">
				     <button type="submit" class="btn btn-primary col-md-1">Salvar</button>
				     <button type="submit" class="btn btn-secundary col-md-1">Cancelar</button>
				</div>
			
		</form>

	</div>
	</main>
	<%@include file="../fragmentos/footer.jsp"%>
</body>
</html>