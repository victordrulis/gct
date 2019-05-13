package br.com.drulis.gct.dominio;

import java.io.Serializable;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.classificacao.ProdutoStatus;
import br.com.drulis.gct.dominio.classificacao.ProdutoTipo;

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
    private String codigo;
    private String versao;
    private ProdutoStatus produtoStatus;
    private ProdutoTipo produtoTipo;
    
    public Produto() {}
    
    /**
     * @param titulo
     * @param descricao
     * @param versao
     */
    public Produto(String titulo, String descricao, String codigo, String versao, ProdutoStatus produtoStatus, ProdutoTipo produtoTipo) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;
        this.codigo = codigo;
        this.versao = versao;
        this.produtoStatus = produtoStatus;
        this.produtoTipo = produtoTipo;
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

    public ProdutoStatus getProdutoStatus() {
		return produtoStatus;
	}

	public void setProdutoStatus(ProdutoStatus produtoStatus) {
		this.produtoStatus = produtoStatus;
	}

	public ProdutoTipo getProdutoTipo() {
		return produtoTipo;
	}

	public void setProdutoTipo(ProdutoTipo produtoTipo) {
		this.produtoTipo = produtoTipo;
	}

	public String getVersao() {
        return versao;
    }
    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

    
}
