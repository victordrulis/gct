package br.com.drulis.gct.dominio;

import java.util.ArrayList;
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
    private Date inicioContrato;
    private int duracaoContrato;
    private ContratoTipo tipoContrato;
    
    public Cliente() {
        this.listaProdutos = new ArrayList<Produto>();
    }
    
    /**
     * @param sla
     * @param inicioContrato
     * @param duracaoContrato
     * @param tipoContrato
     */
    public Cliente(Contato contato, int sla, Date inicioContrato, int duracaoContrato, ContratoTipo tipoContrato, List<Produto> listaProdutos) {
        super();
        this.contato = contato;
        this.sla = sla;
        this.inicioContrato = inicioContrato;
        this.duracaoContrato = duracaoContrato;
        this.tipoContrato = tipoContrato;
        this.listaProdutos = listaProdutos;
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
    
    /**
     * @return the inicioContrato
     */
    public Date getInicioContrato() {
        return inicioContrato;
    }
    
    /**
     * @param inicioContrato the inicioContrato to set
     */
    public void setInicioContrato(Date inicioContrato) {
        this.inicioContrato = inicioContrato;
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
    
    /**
     * @return the tipoContrato
     */
    public ContratoTipo getTipoContrato() {
        return tipoContrato;
    }
    
    /**
     * @param tipoContrato the tipoContrato to set
     */
    public void setTipoContrato(ContratoTipo tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

}
