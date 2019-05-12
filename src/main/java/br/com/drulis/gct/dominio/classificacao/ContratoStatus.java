/**
 * 
 */
package br.com.drulis.gct.dominio.classificacao;

/**
 * @author Victor Drulis Oliveira
 * @since 30 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public enum ContratoStatus {

    EM_ANDAMENTO(1, "Em andamento"),
    EM_TESTES(2, "Em testes"),
    EM_HOMOLOGACAO(3, "Em homologação"),
    EM_PRODUÇÃO(4, "Em produção")
    ;
    
    private Integer id;
    private String descricao;
    
    private ContratoStatus(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

}
