package br.com.drulis.gct.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.core.TipoContrato;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Cliente extends Entidade {
    
    private Contato contato;
    private ClienteStatus status;
    private List<Produto> listaProdutos;
    private int sla;
    private Date inicioContrato;
    private int duracaoContrato;
    private TipoContrato tipoContrato;
    
    /**
     * Listas para montar as views
     */
    private List<Contato> listaContatosView;
    private List<Produto> listaProdutosView;
    
    public Cliente() {
        this.listaContatosView = new ArrayList<Contato>();
        this.listaProdutosView = new ArrayList<Produto>();
    }
    
    /**
     * @param sla
     * @param inicioContrato
     * @param duracaoContrato
     * @param tipoContrato
     */
    public Cliente(Contato contato, int sla, Date inicioContrato, int duracaoContrato, TipoContrato tipoContrato) {
        super();
        this.contato = contato;
        this.sla = sla;
        this.inicioContrato = inicioContrato;
        this.duracaoContrato = duracaoContrato;
        this.tipoContrato = tipoContrato;
        this.status = ClienteStatus.CADASTRADO;
        this.listaProdutos = new ArrayList<Produto>();
        this.listaContatosView = new ArrayList<Contato>();
        this.listaProdutosView = new ArrayList<Produto>();
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
     * @return the status
     */
    public ClienteStatus getStatusCliente() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatusCliente(ClienteStatus status) {
        this.status = status;
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
    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }
    
    /**
     * @param tipoContrato the tipoContrato to set
     */
    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status.getId();
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ClienteStatus status) {
        this.status = status;
    }

    /**
     * @return the listaContatosView
     */
    public List<Contato> getListaContatosView() {
        return listaContatosView;
    }

    /**
     * @param listaContatosView the listaContatosView to set
     */
    public void setListaContatosView(List<Contato> listaContatosView) {
        this.listaContatosView = listaContatosView;
    }

    /**
     * @return the listaProdutosView
     */
    public List<Produto> getListaProdutosView() {
        return listaProdutosView;
    }

    /**
     * @param listaProdutosView the listaProdutosView to set
     */
    public void setListaProdutosView(List<Produto> listaProdutosView) {
        this.listaProdutosView = listaProdutosView;
    }
    
}
