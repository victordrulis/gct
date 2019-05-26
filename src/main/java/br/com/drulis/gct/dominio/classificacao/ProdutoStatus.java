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
public enum ProdutoStatus implements DominioType {
    
    TESTES(1, "Testes"),
    HOMOLOGACAO(2, "Homologação"),
    PRODUCAO(3, "Produção"),
    DESCONTINUADO(4, "Descontinuado")
    ;
    
	private static Map<Integer, ProdutoStatus> mapa;
    private Integer id;
    private String descricao;
    
    private ProdutoStatus(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }
    
    public static Map<Integer, ProdutoStatus> getMapa() {
        mapa = new HashMap<Integer, ProdutoStatus>();
        
        for(ProdutoStatus status : ProdutoStatus.values())
            mapa.put(status.getId(), status);
        
        return mapa;
    }

}
