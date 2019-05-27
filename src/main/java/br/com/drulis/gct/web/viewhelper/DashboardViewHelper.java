package br.com.drulis.gct.web.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Cliente;
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

        String tipo = request.getParameter("tipo");
        String dataInicio = request.getParameter("dataIncio");
        String dataFim = request.getParameter("dataFim");
        
        try {
        	dashboard.setDataInicio(dateFormat.parse("2018-01-01"));
        	dashboard.setDataFim(new Date());
        	
			if(dataInicio != null)
				dashboard.setDataInicio(dateFormat.parse(dataInicio));
			
			if(dataFim != null)
				dashboard.setDataFim(dateFormat.parse(dataFim));
			
			if(tipo != null)
				switch(tipo) {
				case "produto":
					dashboard.setEntidade(new Produto());
					break;
				case "cliente":
					dashboard.setEntidade(new Cliente());
					break;
				case "atividade":
					dashboard.setEntidade(new Atividade());
					break;
				case "chamado":
				default:
					dashboard.setEntidade(new Chamado());
					break;
				}
			
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
        String tipo = request.getParameter("tipo");
        Dashboard dash = new Dashboard();
        Gson gson = new Gson();
        
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Redirecionando requisicao: Tipo = " + tipo + ", URI: " + uri);
        
        if(resultado != null && resultado.getMensagem() != null) {
        	request.setAttribute("mensagem", resultado.getMensagem());
        	request.getRequestDispatcher("/mensagem.jsp").forward(request, response);
        }
        
        // TODO
        if(tipo == null)
        	tipo = "atividade";
        
        if(resultado.getEntidades() != null) {
        	dash = (Dashboard) resultado.getEntidades().get(0);
        }
        
        switch(tipo) {
	        case "chamado":
	        	request.getRequestDispatcher("/jsp/dashboard/chamado.jsp").forward(request, response);
	        	break;
	        case "atividade":
	        	if(dash.getMapaListaAtividades() != null) {
	        		List<String> labels = new ArrayList<String>();
	        		List<String> label = new ArrayList<String>();
	        		List<Integer> values = new ArrayList<Integer>();
	        		
	        		dash.getMapaListaAtividades().forEach((k, v) -> {
	        			labels.add(k);
	        			v.forEach((kl, vl) -> {
	        				label.add(kl);
	        				values.add(vl);
	        			});
	        		});
	        		
	        		request.setAttribute("labels", gson.toJson(labels));
	        		request.setAttribute("label", gson.toJson(label));
	        		request.setAttribute("values", gson.toJson(values));
	        		
	        		request.setAttribute("dadosGrafico", gson.toJson(dash.getMapaListaAtividades()));
	        	}
	        	
	        	request.getRequestDispatcher("/jsp/dashboard/atividade.jsp").forward(request, response);
	        	break;
	        case "produto":
	        	if(dash.getMapaTipoProdutos() != null)
	        		request.setAttribute("dadosGraficoTipo", Arrays.toString(getMapaDescricaoQtde(dash.getMapaTipoProdutos()).entrySet().toArray()));
	        	
	        	if(dash.getMapaStatusProdutos() != null)
	        			request.setAttribute("dadosGraficoStatus", Arrays.toString(getMapaDescricaoQtde(dash.getMapaStatusProdutos()).entrySet().toArray()));
	        	
	        	request.getRequestDispatcher("/jsp/dashboard/produto.jsp").forward(request, response);
	        	break;
	        case "cliente":
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
