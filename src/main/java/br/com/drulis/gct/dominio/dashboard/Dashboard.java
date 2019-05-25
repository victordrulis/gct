/**
 * 
 */
package br.com.drulis.gct.dominio.dashboard;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Produto;

/**
 * @author victor
 *
 */
public class Dashboard extends Entidade {
	private static final long serialVersionUID = 1L;
	
	private Date dataInicio;
	private Date dataFim;
	private Chamado chamado;
	private Atividade atividade;
	private Cliente cliente;
	private Produto produto;
	private Map<Calendar, List<Chamado>> mapaListaChamados;
	private Map<Calendar, List<Atividade>> mapaListaAtividades;
	private Map<Calendar, List<Cliente>> mapaListaClientes;
	private Map<Calendar, List<Produto>> mapaListaProdutos;
	
	public Dashboard() { super();}
	
	public Dashboard(Map<Calendar, List<Chamado>> mapaListaChamados, Map<Calendar, List<Atividade>> mapaListaAtividades, Map<Calendar, List<Cliente>> mapaListaClientes, Map<Calendar, List<Produto>> mapaListaProdutos) {
		super();
		
		this.mapaListaChamados = mapaListaChamados;
		this.mapaListaAtividades = mapaListaAtividades;
		this.mapaListaClientes = mapaListaClientes;
		this.mapaListaProdutos = mapaListaProdutos;
		
		if(mapaListaChamados == null)
			this.mapaListaChamados = new HashMap<Calendar, List<Chamado>>();
		
		if(mapaListaAtividades == null)
			this.mapaListaAtividades = new HashMap<Calendar, List<Atividade>>();
		
		if(mapaListaClientes == null)
			this.mapaListaClientes = new HashMap<Calendar, List<Cliente>>();
		
		if(mapaListaProdutos == null)
			this.mapaListaProdutos = new HashMap<Calendar, List<Produto>>();
	}

	public Date getDataInicio() {
		return dataInicio;
	}
	
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Map<Calendar, List<Chamado>> getMapaListaChamados() {
		return mapaListaChamados;
	}

	public void setMapaListaChamados(Map<Calendar, List<Chamado>> mapaListaChamados) {
		this.mapaListaChamados = mapaListaChamados;
	}

	public Map<Calendar, List<Atividade>> getMapaListaAtividades() {
		return mapaListaAtividades;
	}

	public void setMapaListaAtividades(Map<Calendar, List<Atividade>> mapaListaAtividades) {
		this.mapaListaAtividades = mapaListaAtividades;
	}

	public Map<Calendar, List<Cliente>> getMapaListaClientes() {
		return mapaListaClientes;
	}

	public void setMapaListaClientes(Map<Calendar, List<Cliente>> mapaListaClientes) {
		this.mapaListaClientes = mapaListaClientes;
	}

	public Map<Calendar, List<Produto>> getMapaListaProdutos() {
		return mapaListaProdutos;
	}

	public void setMapaListaProdutos(Map<Calendar, List<Produto>> mapaListaProdutos) {
		this.mapaListaProdutos = mapaListaProdutos;
	}

}
