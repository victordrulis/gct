/**
 * 
 */
package br.com.drulis.gct.dominio;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public enum ProdutoStatus {
    
    TESTES(1, "Testes"),
    HOMOLOGACAO(2, "Homologação"),
    PRODUCAO(3, "Produção")
    ;
    
    private int id;
    private String descricao;
    
    /**
     * @param id
     * @param descricao
     */
    private ProdutoStatus(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }
    
}
