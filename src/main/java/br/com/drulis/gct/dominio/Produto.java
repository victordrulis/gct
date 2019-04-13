package br.com.drulis.gct.dominio;

import java.io.Serializable;

import br.com.drulis.gct.core.Entidade;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Produto extends Entidade implements Serializable {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String descricao;
    private String versao;
    private ProdutoStatus status;
    
    public Produto() {}
    
    /**
     * @param titulo
     * @param descricao
     * @param versao
     */
    public Produto(String titulo, String descricao, String versao) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;
        this.versao = versao;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getVersao() {
        return versao;
    }
    public void setVersao(String versao) {
        this.versao = versao;
    }

    public int getStatusId() {
        return status.getId();
    }
    
    public void setStatus(ProdutoStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((versao == null) ? 0 : versao.hashCode());
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
        Produto other = (Produto) obj;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (versao == null) {
            if (other.versao != null)
                return false;
        } else if (!versao.equals(other.versao))
            return false;
        return true;
    }
    
}
