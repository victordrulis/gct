/**
 * 
 */
package br.com.drulis.gct.dominio.core;

/**
 * @author Victor Drulis Oliveira
 * @since 30 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public enum TipoContrato {

    ALUGUEL("aluguel"),
    COMODATO("comodato"),
    SUPORTE("suporte"),
    VENDA("venda")
    ;
    
    private String descricao;

    /**
     * @param descricao
     */
    private TipoContrato(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

}
