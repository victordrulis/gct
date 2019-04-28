/**
 * 
 */
package br.com.drulis.gct.dominio;

/**
 * @author Victor Drulis Oliveira
 * @since 28 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public enum OcorrenciaTipo {
    
    MANUTENCAO(1, "Manutenção"),
    TAREFA(2, "Tarefa"),
    MELHORIA(3, "Melhoria"),
    ALTERACAO(4, "Alteração")
    ;
    
    private Integer id;
    private String descricao;
    
    private OcorrenciaTipo(Integer id, String descricao) {
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
