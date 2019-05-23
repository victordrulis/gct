package br.com.drulis.gct.dominio;

import java.util.Date;
import java.util.List;

import br.com.drulis.gct.core.Entidade;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Cliente extends Entidade {
    private static final long serialVersionUID = 1L;

    private Contato contato;
    private List<Produto> listaProdutos;
    private int sla;
    private Date dataInicioContrato;
    private Date dataFimContrato;
    private int duracaoContrato;
    private List<Contrato> listaContrato;
    
    public Cliente() {}
    
    /**
     * @param sla
     * @param inicioContrato
     * @param duracaoContrato
     * @param tipoContrato
     */
    public Cliente(Contato contato, int sla, Date dataInicioContrato, Date dataFimContrato, int duracaoContrato, List<Contrato> listaContrato, List<Produto> listaProdutos) {
        super();
        this.contato = contato;
        this.sla = sla;
        this.dataInicioContrato = dataInicioContrato;
        this.dataFimContrato = dataFimContrato;
        this.duracaoContrato = duracaoContrato;
        this.listaContrato = listaContrato;
        this.listaProdutos = listaProdutos;
    }
    
    public Cliente(Integer id) {
		this.id = id;
	}

	/**
     * @return the contato
     */
    public Contato getContato() {
        return contato;
    }

    /**
     * @param contato the contato to set
     */
    public void setContato(Contato contato) {
        this.contato = contato;
    }

    /**
     * @return the listaProdutos
     */
    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    /**
     * @param listaProdutos the listaProdutos to set
     */
    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    /**
     * @return the sla
     */
    public int getSla() {
        return sla;
    }
    
    /**
     * @param sla the sla to set
     */
    public void setSla(int sla) {
        this.sla = sla;
    }
    
    public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Date getDataFimContrato() {
		return dataFimContrato;
	}

	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}

	/**
     * @return the duracaoContrato
     */
    public int getDuracaoContrato() {
        return duracaoContrato;
    }
    
    /**
     * @param duracaoContrato the duracaoContrato to set
     */
    public void setDuracaoContrato(int duracaoContrato) {
        this.duracaoContrato = duracaoContrato;
    }

	public List<Contrato> getListaContrato() {
		return listaContrato;
	}

	public void setListaContrato(List<Contrato> listaContrato) {
		this.listaContrato = listaContrato;
	}
    
}
