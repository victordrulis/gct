package br.com.drulis.gct.viewhelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.util.Resultado;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ClienteViewHelper implements ViewHelperInterface {

    /*
     * (non-Javadoc)
     * 
     * @see
     * br.com.drulis.gct.viewhelper.ViewHelperInterface#getData(javax.servlet.http.
     * HttpServletRequest)
     */
    @Override
    public DominioInterface getData(HttpServletRequest request) {
        System.out.println(this.getClass() + " --getData");
        String acao = request.getParameter("operacao");
        Cliente cliente = new Cliente();

        if (acao == null) {
            acao = "listar";
        }

        if (acao.equals("salvar") && request.getMethod().equals("POST")) {
            // TODO xyz

        }

        return cliente;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * br.com.drulis.gct.viewhelper.ViewHelperInterface#setView(br.com.drulis.gct.
     * util.Resultado, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println(this.getClass() + " --setView");
        List<Cliente> listCliente = new ArrayList<>();
        String mensagem = null;

        if (resultado != null) {
            mensagem = resultado.getMensagem();
            listCliente = (List<Cliente>) (Object) resultado.getEntidades();
        }

        if (mensagem != null && !mensagem.equals("")) {
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("mensagem.jsp").forward(request, response);
        }

        if (listCliente == null || listCliente.size() < 1) {
            mensagem = "Não foram encontrados resultados";
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("mensagem.jsp").forward(request, response);
        }

        if (listCliente != null && listCliente.size() > 1) {
            request.setAttribute("listCliente", listCliente);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        }

        if (listCliente != null && listCliente.size() == 1) {
            request.setAttribute("cliente", listCliente.get(0));
            request.getRequestDispatcher("show.jsp").forward(request, response);
        }
    }

}
