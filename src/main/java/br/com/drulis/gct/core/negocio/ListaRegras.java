/**
 * 
 */
package br.com.drulis.gct.core.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.el.MethodNotFoundException;

import br.com.drulis.gct.core.StrategyInterface;

/**
 * @author Victor Drulis Oliveira
 * @since 25 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public abstract class ListaRegras {
    
    protected List<StrategyInterface> regrasConsultar;
    protected List<StrategyInterface> regrasSalvar;
    protected List<StrategyInterface> regrasAlterar;
    protected List<StrategyInterface> regrasExcluir;
    
    public ListaRegras() {
        regrasConsultar = new ArrayList<StrategyInterface>();
        regrasSalvar = new ArrayList<StrategyInterface>(); 
        regrasAlterar = new ArrayList<StrategyInterface>();
        regrasExcluir = new ArrayList<StrategyInterface>();
    }
    
    public void addRegra(String acao, StrategyInterface regra) {
        regrasConsultar.add(regra);
    }
    
    /**
     * Deve ser implementado pela classe de regras para a entidade.
     * @return
     */
    public Map<String, List<StrategyInterface>> getMapaRegras() {
        throw new MethodNotFoundException("[" + this.getClass().getSimpleName() + "] Método não implementado.");
    }
}   