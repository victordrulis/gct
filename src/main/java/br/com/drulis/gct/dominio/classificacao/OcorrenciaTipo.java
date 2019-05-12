/**
 * 
 */
package br.com.drulis.gct.dominio.classificacao;

import java.util.HashMap;
import java.util.Map;

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
    ALTERACAO(4, "Alteração"),
    Análise(5, "Análise")
    ;
    
    private static Map<Integer, OcorrenciaTipo> mapOcorrenciaTipo;
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
    
    public static Map<Integer, OcorrenciaTipo> getMapaTipo() {
        mapOcorrenciaTipo = new HashMap<Integer, OcorrenciaTipo>();
        
        for(OcorrenciaTipo tipo : OcorrenciaTipo.values())
            mapOcorrenciaTipo.put(tipo.getId(), tipo);
        
        return mapOcorrenciaTipo;
    }
    
}
