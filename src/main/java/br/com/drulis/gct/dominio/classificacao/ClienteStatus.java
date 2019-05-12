/**
 * 
 */
package br.com.drulis.gct.dominio.classificacao;

import br.com.drulis.gct.core.Entidade;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public enum ClienteStatus {
    
    CADASTRADO(1, "Cadastrado"),
    AVALICAO(2, "Avaliação"),
    EM_PRODUCAO(3, "Em produção"),
    CONTRATO_ATIVO(4, "Contrato ativo"),
    CONTRATO_CANCELADO(5, "Contrato cancelado")
    ;
    
    private int id;
    private String descricao;
    
    /**
     * @param id
     * @param descricao
     */
    private ClienteStatus(int id, String descricao) {
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
