package br.com.drulis.gct.web.viewhelper;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.drulis.gct.core.util.PdfGenerator;
import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.dashboard.Dashboard;
import br.com.drulis.gct.web.command.ConsultarCommand;

/**
 * 
 * @author Victor Drulis
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class DashboardViewHelper implements ViewHelperInterface {

    @Override
    public DominioInterface getData(HttpServletRequest request) {
        ConsultarCommand consultar = new ConsultarCommand();
        String acao = request.getParameter("acao");
        Dashboard dashboard = new Dashboard();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        PdfGenerator pdf = new PdfGenerator();
        pdf.gerar();
        pdf.copiarUltimoPdf();
        
        return dashboard;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        
        PdfGenerator pdf = new PdfGenerator();
        pdf.gerar();
        pdf.copiarUltimoPdf();
        
        System.out.println("[" + this.getClass().getSimpleName() + "] Redirecionando requisicao: Acao = " + acao + ", URI: " + uri);
        
        if(resultado != null && resultado.getMensagem() != null) {
        	request.setAttribute("mensagem", resultado.getMensagem());
        }
        
        Gson dados = new Gson();
        dados.toJson("teste");
        
        request.setAttribute("dados", dados);
        request.getRequestDispatcher("/jsp/dashboard/index.jsp").forward(request, response);
        
    }

}
