/**
 * 
 */
package br.com.drulis.gct.core;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Usuario;

/**
 * @author Victor Drulis Oliveira
 * @since 14 de fev de 2019
 * @contact victordrulis@gmail.com
 *
 */
public abstract class Entidade implements DominioInterface, Serializable  {
    private static final long serialVersionUID = 1L;
    
    protected int id;
    public Usuario usuarioInclusao;
    private Usuario usuarioUpdate;
    private Usuario usuarioInativacao;
    protected Date dataInclusao;
    protected Date dataAlteracao;
    protected Date dataInativacao;
    protected int status;
    protected int ativo;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the usuarioInclusao
     */
    public Usuario getUsuarioInclusao() {
        return usuarioInclusao;
    }

    /**
     * @param usuarioInclusao the usuarioInclusao to set
     */
    public void setUsuarioInclusao(Usuario usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    /**
     * @return the usuarioUpdate
     */
    public Usuario getUsuarioUpdate() {
        return usuarioUpdate;
    }

    /**
     * @param usuarioUpdate the usuarioUpdate to set
     */
    public void setUsuarioUpdate(Usuario usuarioUpdate) {
        this.usuarioUpdate = usuarioUpdate;
    }

    /**
     * @return the usuarioInativacao
     */
    public Usuario getUsuarioInativacao() {
        return usuarioInativacao;
    }

    /**
     * @param usuarioInativacao the usuarioInativacao to set
     */
    public void setUsuarioInativacao(Usuario usuarioInativacao) {
        this.usuarioInativacao = usuarioInativacao;
    }

    /**
     * @return the dataInclusao
     */
    public Date getDataInclusao() {
        return dataInclusao;
    }
    
    /**
     * @param dataInclusa the dataInclusao to set
     */
    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
    
    /**
     * @return the dataAlteracao
     */
    public Date getDataAlteracao() {
        return dataAlteracao;
    }
    
    /**
     * @param dataAlteracao the dataAlteracao to set
     */
    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    
    /**
     * @return the dataInativacao
     */
    public Date getDataInativacao() {
        return dataInativacao;
    }
    
    /**
     * @param dataInativacao the dataInativacao to set
     */
    public void setDataInativacao(Date dataInativacao) {
        this.dataInativacao = dataInativacao;
    }

    /**
     * @return the ativo
     */
    public int getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Converte Date para String
     * @param dtData
     * @return
     */
    public static String converteDateString(Date data)
    {  
        if (data == null)  
            return null;  
        SimpleDateFormat formatBra = new SimpleDateFormat("dd/MM/yyyy");              
        return (formatBra.format(data)); 
     } 
     
    /**
     * Converte a data em formato String para Date
     * @param data
     * @return Date
     */
    public static Date converteStringDate(String data)
    {   
        if (data == null || data.equals(""))  
            return null;  
        Date date = null;
        try {  
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            date = (java.util.Date)formatter.parse(data);  
            return date;
        } catch (ParseException e) {              
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Entidade other = (Entidade) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
}
