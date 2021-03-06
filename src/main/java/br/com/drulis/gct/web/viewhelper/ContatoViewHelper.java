package br.com.drulis.gct.web.viewhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Mensagem;

/**
 * 
 * @author Victor Drulis
 * @since 2 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ContatoViewHelper implements ViewHelperInterface {

    @Override
    public DominioInterface getData(HttpServletRequest request) {
        String acao = request.getParameter("acao");
        Contato contato = new Contato();
        contato.setAtivo(0);
        
        System.out.println("[" + this.getClass().getSimpleName() + "] getData, ACAO = " + acao + ", URI: " + request.getRequestURI());

        if(acao == null) {
            acao = Acao.LISTAR.getAcao();
        }
        
        if(!acao.equals(Acao.SALVAR.getAcao()) && !acao.equals(Acao.NOVO.getAcao())) {
            String id = request.getParameter("id");
            
            if(id != null && id != "")
                contato.setId(Integer.parseInt(id));
        }
        
        if(!acao.equals(Acao.EXCLUIR.getAcao()) && (request.getMethod().equals("GET") || request.getMethod().equals("POST"))) {
            contato.setNome(request.getParameter("nome"));
            contato.setCpfCnpj(request.getParameter("cpfCnpj"));
            contato.setEmail(request.getParameter("email"));
            contato.setTel(request.getParameter("telefone"));
            contato.setAtivo(1);
            
            if(request.getParameter("ativo") == null)
            	contato.setAtivo(0);
        }
        
        return contato;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Contato> listContato = new ArrayList<>();
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        
        System.out.println("[" + this.getClass().getSimpleName() + "] setView: Acao = " + acao + ", URI: " + uri);
        
        if(resultado != null)
        		listContato = (List<Contato>) (Object) resultado.getEntidades();
        
        switch(listContato.size()) {
        case 0:
        	if(resultado != null && resultado.getMensagem() != null) {
        		request.setAttribute("resultado", resultado.getMensagem());
                request.getRequestDispatcher("/mensagem.jsp").forward(request, response);
                break;
            }
        	
            request.getRequestDispatcher("/jsp/contato/form.jsp").forward(request, response);
            break;
        
        case 1:
            request.setAttribute("resultado", listContato.get(0));
            
            if(acao != null && acao.equals(Acao.EDITAR.getAcao())) {
                request.getRequestDispatcher("/jsp/contato/edit.jsp").forward(request, response);
                break;
            }
            
            if(acao != null && acao.equals(Acao.EXIBIR.getAcao())) {
                request.getRequestDispatcher("/jsp/contato/show.jsp").forward(request, response);
                break;
            }
            
            if(resultado.getMensagem() != null) {
            	request.setAttribute("resultado", resultado.getMensagem());
                request.getRequestDispatcher("/mensagem.jsp").forward(request, response);
                break;
            }
            
            request.getRequestDispatcher("/jsp/contato/index.jsp").forward(request, response);
            break;
        
        default:
            if(acao != null && acao.equals(Acao.NOVO.getAcao())) {
                request.getRequestDispatcher("/jsp/contato/form.jsp").forward(request, response);
                break;
            }
            
            if(listContato == null || listContato.size() < 1) {
                request.setAttribute("mensagem", Mensagem.ERRO_NAO_ENCONTRADO.getDescricao());
                request.getRequestDispatcher("mensagem.jsp").forward(request, response);
                break;
            }
            
            request.setAttribute("resultado", listContato);
            request.getRequestDispatcher("/jsp/contato/index.jsp").forward(request, response);
            break;
        }
        
    }

}
