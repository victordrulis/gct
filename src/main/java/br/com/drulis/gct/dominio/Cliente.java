package br.com.drulis.gct.dominio;

import java.util.Date;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.core.TipoContrato;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Cliente extends Entidade {
    
    private int sla;
    private Date inicioContrato;
    private int duracaoContrato;
    private TipoContrato tipoContrato;
    
    public Cliente() {}
    
    /**
     * @param sla
     * @param inicioContrato
     * @param duracaoContrato
     * @param tipoContrato
     */
    public Cliente(int sla, Date inicioContrato, int duracaoContrato, TipoContrato tipoContrato) {
        super();
        this.sla = sla;
        this.inicioContrato = inicioContrato;
        this.duracaoContrato = duracaoContrato;
        this.tipoContrato = tipoContrato;
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
    
}
