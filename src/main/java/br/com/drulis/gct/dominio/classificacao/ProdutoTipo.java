/**
 * 
 */
package br.com.drulis.gct.dominio.classificacao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public enum ProdutoTipo {
    
    INFRA(1, "Infraestrutura"),
    SUPORTE(2, "Suporte"),
    REDE(3, "Rede"),
    BI(4, "Business Intelligence (B.I.)"),
    ERP(5, "ERP"),
    SUPPLY_CHAIN(6, "Supply Chain")
    ;
    
	private static Map<Integer, ProdutoTipo> mapa;
    private Integer id;
    private String descricao;
    
    private ProdutoTipo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static Map<Integer, ProdutoTipo> getMapa() {
        mapa = new HashMap<Integer, ProdutoTipo>();
        
        for(ProdutoTipo status : ProdutoTipo.values())
            mapa.put(status.getId(), status);
        
        return mapa;
    }

}
