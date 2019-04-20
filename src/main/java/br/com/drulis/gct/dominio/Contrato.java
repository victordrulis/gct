package br.com.drulis.gct.dominio;

import br.com.drulis.gct.core.Entidade;

/**
 * @author Victor Drulis Oliveira
 * @since 19 de abril de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Contrato extends Entidade {
    private static final long serialVersionUID = 1L;
    
    private Cliente cliente;
    private Produto produto;
    
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
}
