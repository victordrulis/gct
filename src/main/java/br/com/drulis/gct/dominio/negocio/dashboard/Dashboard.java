/**
 * 
 */
package br.com.drulis.gct.dominio.negocio.dashboard;

import com.google.gson.Gson;

import br.com.drulis.gct.core.Entidade;

/**
 * @author victor
 *
 */
public class Dashboard extends Entidade {
	
	private static final String LINHAS = "line";
	private static final String BARRA = "bar";
	private static final String RADAR = "radar";
	
	private static final long serialVersionUID = 1L;
	private Gson dados;
	
	public Dashboard() {}
	
	public Dashboard(Gson dados) {
		super();
		this.dados = dados;
	}

	public Gson getDados() {
		return dados;
	}

	public void setDados(Gson dados) {
		this.dados = dados;
	}
	
}
