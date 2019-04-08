package br.com.drulis.gct.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.util.Resultado;

/**
 * 
 * @author Victor Drulis
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ClienteViewHelper implements ViewHelperInterface {

    @Override
    public DominioInterface getData(HttpServletRequest request) {
        String acao = request.getParameter("acao");
        Cliente cliente = new Cliente();
        Contato contato = new Contato();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String contatoId = request.getParameter("contatoId");
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
                
                cliente.setContato(contato);
                cliente.setSla((sla != null) ? Integer.parseUnsignedInt(sla) : 0);
                cliente.setInicioContrato((dataInicioContrato != null) ? dateFormat.parse(dataInicioContrato) : new Date());
                cliente.setDuracaoContrato((duracaoContrato != null) ? Integer.parseInt(duracaoContrato) : 0);
            } catch (ParseException e) {
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

    @SuppressWarnings("unchecked")
    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Cliente> listCliente = new ArrayList<>();
        String mensagem = null;
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        
        System.out.println(this.getClass().getSimpleName() + " -- setView: Acao = " + acao + ", URI: " + uri);
        
        if(resultado != null) {
            mensagem = resultado.getMensagem();
            listCliente = (List<Cliente>) (Object) resultado.getEntidades();
        }
        
        if(acao != null && acao.equals(Acao.NOVO.getAcao())) {
            request.getRequestDispatcher("/jsp/cliente/form.jsp").forward(request, response);
        } else {
            switch(listCliente.size()) {
            case 0:
                request.getRequestDispatcher("/jsp/cliente/form.jsp").forward(request, response);
                break;
            
            case 1:
                request.setAttribute("resultado", listCliente.get(0));
                
                if(acao != null && acao.equals(Acao.EDITAR.getAcao())) {
                    request.getRequestDispatcher("/jsp/cliente/edit.jsp").forward(request, response);
                    break;
                }
                
                if(acao != null && acao.equals(Acao.EXIBIR.getAcao())) {
                    request.getRequestDispatcher("/jsp/cliente/show.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("resultado", listCliente);
                request.getRequestDispatcher("/jsp/cliente/index.jsp").forward(request, response);
                break;
            
            default:
                if(mensagem != null && !mensagem.equals("")) {
                    request.setAttribute("mensagem", mensagem);
                    request.getRequestDispatcher("mensagem.jsp").forward(request, response);
                    break;
                }
                
                if(acao != null && acao.equals(Acao.NOVO.getAcao())) {
                    request.getRequestDispatcher("/jsp/cliente/form.jsp").forward(request, response);
                    break;
                }
                
                if(listCliente == null || listCliente.size() < 1) {
                    request.setAttribute("mensagem", Mensagem.ERRO_NAO_ENCONTRADO.getDescricao());
                    request.getRequestDispatcher("mensagem.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("resultado", listCliente);
                request.getRequestDispatcher("/jsp/cliente/index.jsp").forward(request, response);
                break;
            }
        }
        
    }

}
