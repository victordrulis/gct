package br.com.drulis.gct.web.viewhelper;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.web.command.ConsultarCommand;

/**
 * 
 * @author Victor Drulis
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class LoginViewHelper implements ViewHelperInterface {

    @Override
    public DominioInterface getData(HttpServletRequest request) {
        String acao = request.getParameter("acao");
        Cliente cliente = new Cliente();
        Contato contato = new Contato();
        Produto produto = new Produto();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String contatoId = request.getParameter("contatoId");
        String produtoId = request.getParameter("listaProdutoId");
        String sla = request.getParameter("sla");
        String dataInicioContrato = request.getParameter("dataInicioContrato");
        String duracaoContrato = request.getParameter("duracaoContrato");
        
        System.out.println(this.getClass().getSimpleName() + ": --getData, ACAO: " + acao + ", URI: " + request.getRequestURI());

        if(acao == null) {
            acao = Acao.LISTAR.getAcao();
        }
        
        if(!acao.equals(Acao.SALVAR.getAcao()) && !acao.equals(Acao.NOVO.getAcao())) {
            String id = request.getParameter("id");
            
            if(id != null && id != "")
                cliente.setId(Integer.parseInt(id));
        }
        
        if(!acao.equals(Acao.EXCLUIR.getAcao()) || acao.equals(Acao.SALVAR.getAcao()) && request.getMethod().equals("GET")) {
            
            try {
                contato.setId((contatoId != null) ? Integer.parseInt(contatoId) : 0);
                produto.setId((produtoId != null) ? Integer.parseInt(produtoId) : 0);
                
                cliente.setContato(contato);
                
                cliente.setSla((sla != null) ? Integer.parseUnsignedInt(sla) : 0);
                cliente.setDuracaoContrato((duracaoContrato != null) ? Integer.parseInt(duracaoContrato) : 0);
            } catch (Exception e) {
                System.out.println(this.getClass().getSimpleName() + ": " + Mensagem.ERRO_CONVERTER_DADOS.getDescricao() + "; \n" + e.getMessage());
                e.printStackTrace();
            }
            
            if(request.getParameter("ativo") != null)
                cliente.setAtivo(1);
        }
        
        if(acao.equals(Acao.SALVAR.getAcao()) || acao.equals(Acao.ALTERAR.getAcao()) || acao.equals(Acao.EDITAR.getAcao()) && request.getMethod().equals("POST")) {
            contato.setId(Integer.parseInt(contatoId));
            
            cliente.setContato(contato);
            cliente.setSla(Integer.parseUnsignedInt(sla));
//            cliente.setDuracaoContrato(duracaoContrato);
            cliente.setDuracaoContrato(Integer.parseInt(duracaoContrato));
            
            if(request.getParameter("ativo") != null)
                cliente.setAtivo(1);
        }
        
        return cliente;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConsultarCommand consultar = new ConsultarCommand();
        Resultado resContato = new Resultado();
        Resultado resProduto = new Resultado();
        
        try {
            resProduto = consultar.execute(new Produto());
            resContato = consultar.execute(new Contato());
        } catch (Exception e) {
            System.out.println(this.getClass().getSimpleName() + ": " + Mensagem.ERRO_CONVERTER_DADOS + ": " + e.getMessage());
            e.printStackTrace();
        }
        
        String mensagem = null;
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        
        System.out.println(this.getClass().getSimpleName() + " -- setView: Acao = " + acao + ", URI: " + uri);
        
        if(resultado != null) {
            mensagem = resultado.getMensagem();
        }
        
        if(acao != null && acao.equals(Acao.NOVO.getAcao())) {
            request.setAttribute("listaContato", resContato.getEntidades());
            request.setAttribute("listaProduto", resProduto.getEntidades());
            request.getRequestDispatcher("/jsp/cliente/form.jsp").forward(request, response);
        } else {
            switch(resultado.getEntidades().size()) {
            case 0:
                request.setAttribute("listaContato", resContato.getEntidades());
                request.setAttribute("listaProduto", resProduto.getEntidades());
                request.getRequestDispatcher("/jsp/cliente/form.jsp").forward(request, response);
                break;
            
            case 1:
                request.setAttribute("resultado", resultado.getEntidades().get(0));
                
                if(acao != null && acao.equals(Acao.EDITAR.getAcao())) {
                    request.getRequestDispatcher("/jsp/cliente/edit.jsp").forward(request, response);
                    break;
                }
                
                if(acao != null && acao.equals(Acao.EXIBIR.getAcao())) {
                    request.getRequestDispatcher("/jsp/cliente/show.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("resultado", resultado.getEntidades());
                request.getRequestDispatcher("/jsp/cliente/index.jsp").forward(request, response);
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
                request.getRequestDispatcher("/jsp/cliente/index.jsp").forward(request, response);
                break;
            }
        }
        
    }

}
