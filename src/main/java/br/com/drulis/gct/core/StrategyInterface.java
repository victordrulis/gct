/**
 * 
 */
package br.com.drulis.gct.core;

import br.com.drulis.gct.core.Entidade;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public interface StrategyInterface {
    public String processar(Entidade entidade);
}
