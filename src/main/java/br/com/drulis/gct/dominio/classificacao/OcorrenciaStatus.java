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
public enum OcorrenciaStatus {
    
    ABERTO(1, "Aberto"),
    FECHADO(2, "Fechado"),
    EM_ANDAMENTO(3, "Em Andamento"),
    CANCELADO(4, "Cancelado"),
    FINALIZADO(5, "Finalizado"),
    AGUARDANDO(6, "Aguardando"),
    EM_EXECUCAO(7, "Em Execução"),
    ATRIBUIDO(8,"Atribuido"),
    NAO_ATRIBUIDO(9, "Não Atribuido")
    ;
    
    private static Map<Integer, OcorrenciaStatus> mapOcorrenciaTipo;
    private Integer id;
    private String descricao;
    
    private OcorrenciaStatus(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static Map<Integer, OcorrenciaStatus> getMapa() {
        mapOcorrenciaTipo = new HashMap<Integer, OcorrenciaStatus>();
        
        for(OcorrenciaStatus tipo : OcorrenciaStatus.values())
            mapOcorrenciaTipo.put(tipo.getId(), tipo);
        
        return mapOcorrenciaTipo;
    }
}
