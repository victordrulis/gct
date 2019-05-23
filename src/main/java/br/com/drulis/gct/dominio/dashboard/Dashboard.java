/**
 * 
 */
package br.com.drulis.gct.dominio.dashboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Cliente;

/**
 * @author victor
 *
 */
public class Dashboard extends Entidade {
	private static final long serialVersionUID = 1L;
	
	private Gson dados;
	private Date dataInicio;
	private Date dataFim;
	private List<Chamado> listaChamados;
	private List<Atividade> listaAtividades;
	private List<Cliente> listaClientes;
	
	public Dashboard() {}
	
	public Dashboard(Gson dados) {
		super();
		this.dados = dados;
		this.listaChamados = new ArrayList<Chamado>();
		this.listaAtividades = new ArrayList<Atividade>();
		this.listaClientes = new ArrayList<Cliente>();
	}

	public Gson getDados() {
		return dados;
	}

	public void setDados(Gson dados) {
		this.dados = dados;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<Chamado> getListaChamados() {
		return listaChamados;
	}

	public void setListaChamados(List<Chamado> listaChamados) {
		this.listaChamados = listaChamados;
	}

	public List<Atividade> getListaAtividades() {
		return listaAtividades;
	}

	public void setListaAtividades(List<Atividade> listaAtividades) {
		this.listaAtividades = listaAtividades;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
}
