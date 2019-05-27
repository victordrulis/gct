/**
 * 
 */
package br.com.drulis.gct.dominio.dashboard;

import java.util.Date;
import java.util.Map;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.classificacao.DominioType;

/**
 * @author victor
 *
 */
public class Dashboard extends Entidade {
	private static final long serialVersionUID = 1L;
	
	private Date dataInicio;
	private Date dataFim;
	private Entidade entidade;
	private Map<Chamado, Integer> mapaListaChamados;
	
	// <Ano, <Mes, <Tipo, <Status, Qtde>>>>
	private Map<String,  Map<String, Integer>>  mapaListaAtividades;
	
	private Map<Cliente, Integer> mapaListaClientes;
	private Map<DominioType, Integer> mapaStatusProdutos;
	private Map<DominioType, Integer> mapaTipoProdutos;
	
	public Dashboard() { super();}
	
	public Dashboard(Date dataInicio, Date dataFim) {
		super();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	public Entidade getEntidade() {
		return entidade;
	}
	
	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
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

	public Map<Chamado, Integer> getMapaListaChamados() {
		return mapaListaChamados;
	}

	public void setMapaListaChamados(Map<Chamado, Integer> mapaListaChamados) {
		this.mapaListaChamados = mapaListaChamados;
	}

	public Map<String,  Map<String, Integer>> getMapaListaAtividades() {
		return mapaListaAtividades;
	}

	public void setMapaListaAtividades(Map<String,  Map<String, Integer>> mapaListaAtividades) {
		this.mapaListaAtividades = mapaListaAtividades;
	}

	public Map<Cliente, Integer> getMapaListaClientes() {
		return mapaListaClientes;
	}

	public void setMapaListaClientes(Map<Cliente, Integer> mapaListaClientes) {
		this.mapaListaClientes = mapaListaClientes;
	}

	public Map<DominioType, Integer> getMapaStatusProdutos() {
		return mapaStatusProdutos;
	}

	public void setMapaStatusProdutos(Map<DominioType, Integer> mapaStatusProdutos) {
		this.mapaStatusProdutos = mapaStatusProdutos;
	}

	public Map<DominioType, Integer> getMapaTipoProdutos() {
		return mapaTipoProdutos;
	}

	public void setMapaTipoProdutos(Map<DominioType, Integer> mapaTipoProdutos) {
		this.mapaTipoProdutos = mapaTipoProdutos;
	}

}
