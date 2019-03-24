package br.com.drulis.gct.viewhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.util.Resultado;

/**
 * 
 * @author Victor Drulis
 * @since 2 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ChamadoViewHelper implements ViewHelperInterface {

    @Override
    public DominioInterface getData(HttpServletRequest request) {
        System.out.println(this.getClass() + " --getData");
        String acao = request.getParameter("operacao");
        Chamado chamado = new Chamado();

        if (acao == null) {
            acao = "listar";
        }

        if(acao.equals("salvar") && request.getMethod().equals("POST")) {
            // TODO xyz
        
        }
        
        return chamado;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println(this.getClass() + " --setView");
        List<Chamado> listChamado = new ArrayList<>();
        String mensagem = null;
        
        if(resultado != null) {
            mensagem = resultado.getMensagem();
            listChamado = (List<Chamado>) (Object) resultado.getEntidades();
        }
        
        if(mensagem !=null && !mensagem.equals("")) {
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("mensagem.jsp").forward(request, response);
        }
        
        if(listChamado == null || listChamado.size() < 1) {
            request.setAttribute("mensagem", Mensagem.ERRO_NAO_ENCONTRADO);
            request.getRequestDispatcher("mensagem.jsp").forward(request, response);
        }
        
        if(listChamado != null && listChamado.size() > 1) {
            request.setAttribute("listChamado", listChamado);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        }
        
        if(listChamado != null && listChamado.size() == 1) {
            request.setAttribute("chamado", listChamado.get(0));
            request.getRequestDispatcher("show.jsp").forward(request, response);
        }

    }

}
