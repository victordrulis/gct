package br.com.drulis.gct.dominio;

import java.util.Date;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.classificacao.ContratoStatus;
import br.com.drulis.gct.dominio.classificacao.ContratoTipo;

/**
 * Relaciona Cliente com Produto contratado
 * 
 * @author Victor Drulis Oliveira
 * @since 19 de abril de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Contrato extends Entidade {
    private static final long serialVersionUID = 1L;
    
    private Cliente cliente;
    private Produto produto;
    private Date dataInicio;
    private Date dataFim;
    private ContratoStatus contratoStatus;
    private ContratoTipo contratoTipo;
    
    /**
     * @param cliente
     * @param produto
     * @param dataInicio
     * @param dataFim
     * @param contratoStatus
     * @param contratoTipo
     */
    public Contrato(Cliente cliente, Produto produto, Date dataInicio, Date dataFim, ContratoStatus contratoStatus, ContratoTipo contratoTipo) {
        super();
        this.cliente = cliente;
        this.produto = produto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.contratoStatus = contratoStatus;
        this.contratoTipo = contratoTipo;
    }

    public Contrato() {}

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
    
    public ContratoStatus getContratoStatus() {
        return contratoStatus;
    }
    
    public void setContratoStatus(ContratoStatus contratoStatus) {
        this.contratoStatus = contratoStatus;
    }
    
    public ContratoTipo getContratoTipo() {
        return contratoTipo;
    }
    
    public void setContratoTipo(ContratoTipo contratoTipo) {
        this.contratoTipo = contratoTipo;
    }
}
