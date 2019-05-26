package br.com.drulis.gct.web.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.dominio.classificacao.DominioType;
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
        Dashboard dashboard = new Dashboard();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String acao = "dashboard";
        String dataInicio = request.getParameter("dataIncio");
        String dataFim = request.getParameter("dataFim");
        String entidade = request.getParameter("entidade");
        String statusEntidade = request.getParameter("statusEntidade");
        
        try {
        	dashboard.setDataInicio(dateFormat.parse("2018-01-01"));
        	dashboard.setDataFim(new Date());
        	
        	// TODO
        	dashboard.setEntidade(new Produto());
        	
			if(dataInicio != null)
				dashboard.setDataInicio(dateFormat.parse(dataInicio));
			
			if(dataFim != null)
				dashboard.setDataFim(dateFormat.parse(dataFim));
			
//			if(entidade != null)
//				dashboard.setEntidade();
			
        } catch (ParseException e) {
        	System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + e.getMessage());
        	e.printStackTrace();
        }
        
        return dashboard;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        Gson dados = new Gson();
        
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Redirecionando requisicao: Acao = " + acao + ", URI: " + uri);
        
        if(resultado != null && resultado.getMensagem() != null) {
        	request.setAttribute("mensagem", resultado.getMensagem());
        	request.getRequestDispatcher("/mensagem.jsp").forward(request, response);
        }
        
        // TODO
        if(acao == null)
        	acao = "Produto";
        
        Dashboard dash = (Dashboard) resultado.getEntidades().get(0);
        request.setAttribute("dadosGrafico", dash);
        switch(acao) {
	        case "Chamado":
	        	request.getRequestDispatcher("/jsp/dashboard/chamado.jsp").forward(request, response);
	        	break;
	        case "Atividade":
	        	request.getRequestDispatcher("/jsp/dashboard/atividade.jsp").forward(request, response);
	        	break;
	        case "Produto":
	        	if(dash.getMapaTipoProdutos() != null)
	        			request.setAttribute("dadosGraficoTipo", Arrays.toString(getMapaDescricaoQtde(dash.getMapaTipoProdutos()).entrySet().toArray()));
	        	
	        	if(dash.getMapaStatusProdutos() != null)
	        			request.setAttribute("dadosGraficoStatus", Arrays.toString(getMapaDescricaoQtde(dash.getMapaStatusProdutos()).entrySet().toArray()));
	        	
	        	request.getRequestDispatcher("/jsp/dashboard/produto.jsp").forward(request, response);
	        	break;
	        case "Cliente":
	        	request.getRequestDispatcher("/jsp/dashboard/cliente.jsp").forward(request, response);
	        	break;
	    	default:
	    		request.setAttribute("dadosGrafico", null);
	    		request.getRequestDispatcher("/jsp/dashboard/index.jsp").forward(request, response);
	    		break;
        }
        
    }
    
    private Map<String, Integer> getMapaDescricaoQtde(Map<DominioType, Integer> mapa) {
    	Map<String, Integer> mapaDescricaoQtde = new TreeMap<String, Integer>();
    	mapa.forEach((k, v) -> {
    		mapaDescricaoQtde.put(k.getDescricao(), v);
    	});
    	return mapaDescricaoQtde;
    }

}
