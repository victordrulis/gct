package br.com.drulis.gct.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.command.ConsultarCommand;
import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.OcorrenciaTipo;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.dominio.Usuario;
import br.com.drulis.gct.util.Resultado;

/**
 * 
 * @author Victor Drulis
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ChamadoViewHelper implements ViewHelperInterface {

    @Override
    public DominioInterface getData(HttpServletRequest request) {
        ConsultarCommand consultar = new ConsultarCommand();
        String acao = request.getParameter("acao");
        Chamado chamado = new Chamado();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String[] listaAtividades = request.getParameterValues("listaAtividades");
        String produtoId = request.getParameter("produtoId");
        String clienteId = request.getParameter("clienteId");
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String status = request.getParameter("status");
        String tipo = request.getParameter("tipo");
        String dataAbertura = request.getParameter("dataAbertura");
        String dataFechamento = request.getParameter("dataFechamento");
        String usrAtribuido = request.getParameter("usuarioAtribuidoId");
        String ativo = request.getParameter("ativo");
        String usrInclusao = request.getParameter("usuarioInclusaoId");
        
        System.out.println("[" + this.getClass().getSimpleName() + "] --getData, ACAO: " + acao + ", URI: " + request.getRequestURI());

        if(acao == null) {
            acao = Acao.LISTAR.getAcao();
        }
        
        if(!acao.equals(Acao.SALVAR.getAcao()) && !acao.equals(Acao.NOVO.getAcao())) {
            String id = request.getParameter("id");
            
            if(id != null && id != "")
                chamado.setId(Integer.parseInt(id));
        }
        
        if(acao.equals(Acao.SALVAR.getAcao()) || acao.equals(Acao.ALTERAR.getAcao()) || acao.equals(Acao.EDITAR.getAcao()) && request.getMethod().equals("POST")) {
        	Resultado resultado = new Resultado();
        	Usuario usuarioInclusao = new Usuario();
        	usuarioInclusao.setId(Integer.parseInt(usrInclusao));
            Usuario usuarioAtribuido = new Usuario();
            usuarioAtribuido.setId(Integer.parseInt(usrAtribuido));
            Cliente cliente = new Cliente();
            cliente.setId(Integer.parseInt(clienteId));
            Produto produto = new Produto();
            produto.setId(Integer.parseInt(produtoId));
            
            Calendar calendar = Calendar.getInstance();
            
            if (listaAtividades != null){
                for(String atividadeId : listaAtividades) {
                    Atividade p = new Atividade();
                    p.setId(Integer.parseInt(atividadeId));
                    chamado.getListaAtividades().add((Atividade) consultar.execute(p).getEntidades().get(0));
                }
            }

            try {
                resultado = consultar.execute(usuarioInclusao);
                chamado.setUsuarioInclusao(usuarioInclusao);
                
            	resultado = consultar.execute(usuarioAtribuido);
            	chamado.setUsuarioAtribuido((Usuario) resultado.getEntidades().get(0));
            	
            	resultado = consultar.execute(cliente);
            	chamado.setCliente((Cliente) resultado.getEntidades().get(0));
            	
            	resultado = consultar.execute(produto);
            	chamado.setProduto((Produto) resultado.getEntidades().get(0));
            	
                chamado.setTitulo(titulo);
                chamado.setDescricao(descricao);
                chamado.setTipo(OcorrenciaTipo.valueOf(tipo));
                chamado.setStatus(Integer.parseUnsignedInt(status));
                chamado.setStatus(1);
                chamado.setDataAbertura(dateFormat.parse(dataAbertura));
                calendar.setTime(dateFormat.parse(dataAbertura));
                calendar.add(Calendar.DATE, 2);
                chamado.setDataFechamento(calendar.getTime());
            } catch (ParseException e) {
                System.out.println("[" + this.getClass().getSimpleName() + "] Erro ao obter dados do formulário." + e.getMessage());
                e.printStackTrace();
            }

            if(ativo != null || ativo != "0")
                chamado.setAtivo(1);
        }
        
        if(!acao.equals(Acao.EXCLUIR.getAcao()) && request.getMethod().equals("GET")) {
            
            try {
                
                if (listaAtividades != null){
                    for(String atividadeId : listaAtividades) {
                        Atividade p = new Atividade();
                        p.setId(Integer.parseInt(atividadeId));
                    }
                }
                
                chamado.setTitulo(titulo);
                chamado.setStatus((status != null) ? Integer.parseUnsignedInt(status) : 0);
                chamado.setDataAbertura((dataAbertura != null) ? dateFormat.parse(dataAbertura) : new Date());
                chamado.setDataFechamento((dataFechamento != null) ? dateFormat.parse(dataFechamento) : new Date());
            } catch (ParseException e) {
                System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_CONVERTER_DADOS.getDescricao() + "; \n" + e.getMessage());
                e.printStackTrace();
            }
            
            if(ativo != null || ativo != "0")
                chamado.setAtivo(1);
        }
        
        return chamado;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConsultarCommand consultar = new ConsultarCommand();
        Resultado resCliente = new Resultado();
        Resultado resProduto = new Resultado();
        Resultado resUsuario = new Resultado();
        
        
        String mensagem = null;
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        
        System.out.println("[" + this.getClass().getSimpleName() + "] setView: Acao = " + acao + ", URI: " + uri);
        
        if(resultado != null) {
            mensagem = resultado.getMensagem();
        }
        
        if(acao != null && (acao.equals(Acao.NOVO.getAcao()) || acao.equals(Acao.EDITAR.getAcao()))) {
            
            try {
                resProduto = consultar.execute(new Produto());
                resCliente = consultar.execute(new Cliente());
                resUsuario = consultar.execute(new Usuario());
            } catch (Exception e) {
                System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_CONVERTER_DADOS.getDescricao() + ": " + e.getMessage());
                e.printStackTrace();
            }
            
            request.setAttribute("listaCliente", resCliente.getEntidades());
            request.setAttribute("listaProduto", resProduto.getEntidades());
            request.setAttribute("listaUsuario", resUsuario.getEntidades());
            request.getRequestDispatcher("/jsp/chamado/form.jsp").forward(request, response);
        } else {
            switch(resultado.getEntidades().size()) {
            case 0:
                
                try {
                    resProduto = consultar.execute(new Produto());
                    resCliente = consultar.execute(new Cliente());
                    resUsuario = consultar.execute(new Usuario());
                } catch (Exception e) {
                    System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_CONVERTER_DADOS.getDescricao() + ": " + e.getMessage());
                    e.printStackTrace();
                }
                
                request.setAttribute("listaCliente", resCliente.getEntidades());
                request.setAttribute("listaProduto", resProduto.getEntidades());
                request.setAttribute("listaUsuario", resUsuario.getEntidades());
                request.getRequestDispatcher("/jsp/chamado/form.jsp").forward(request, response);
                break;
            
            case 1:
                request.setAttribute("resultado", resultado.getEntidades().get(0));
                
                if(acao != null && acao.equals(Acao.EDITAR.getAcao())) {
                    request.getRequestDispatcher("/jsp/chamado/edit.jsp").forward(request, response);
                    break;
                }
                
                if(acao != null && acao.equals(Acao.EXIBIR.getAcao())) {
                    request.getRequestDispatcher("/jsp/chamado/show.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("resultado", resultado.getEntidades());
                request.getRequestDispatcher("/jsp/chamado/index.jsp").forward(request, response);
                break;
            
            default:
                if(mensagem != null && !mensagem.equals("")) {
                    request.setAttribute("mensagem", mensagem);
                    request.getRequestDispatcher("mensagem.jsp").forward(request, response);
                    break;
                }
                
                if(resultado.getEntidades() == null || resultado.getEntidades().size() < 1) {
                    request.setAttribute("mensagem", Mensagem.ERRO_NAO_ENCONTRADO.getDescricao());
                    request.getRequestDispatcher("mensagem.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("resultado", resultado.getEntidades());
                request.getRequestDispatcher("/jsp/chamado/index.jsp").forward(request, response);
                break;
            }
        }
        
    }

}
