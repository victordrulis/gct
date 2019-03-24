package br.com.drulis.gct.viewhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.dominio.Contato;
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
public class DashboardViewHelper implements ViewHelperInterface {

    @Override
    public DominioInterface getData(HttpServletRequest request) {
        System.out.println(this.getClass() + " --getData");
        String acao = request.getParameter("operacao");
        Contato contato = new Contato();
        
        if(acao == null) {
            acao = "listar";
        }
        
        if(acao.equals("salvar") && request.getMethod().equals("POST")) {
            contato.setNome(request.getParameter("nome"));
            contato.setCpfCnpj(request.getParameter("cpfCnpj"));
            contato.setEmail(request.getParameter("email"));
            contato.setTel(request.getParameter("telefone"));
        }
        
        return contato;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println(this.getClass() + " --setView");
        List<Contato> listContato = new ArrayList<>();
        String mensagem = null;
        
        if(resultado != null) {
            mensagem = resultado.getMensagem();
            listContato = (List<Contato>) (Object) resultado.getEntidades();
        }
        
        if(mensagem !=null && !mensagem.equals("")) {
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("mensagem.jsp").forward(request, response);
        }
        
        if(listContato == null || listContato.size() < 1) {
            request.setAttribute("mensagem", Mensagem.ERRO_NAO_ENCONTRADO.getDescricao());
            request.getRequestDispatcher("mensagem.jsp").forward(request, response);
        }
        
        if(listContato != null && listContato.size() > 1) {
            request.setAttribute("listContato", listContato);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        }
        
        if(listContato != null && listContato.size() == 1) {
            request.setAttribute("contato", listContato.get(0));
            request.getRequestDispatcher("show.jsp").forward(request, response);
        }

    }

}
