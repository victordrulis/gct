/**
 * 
 */
package br.com.drulis.gct.core;

import br.com.drulis.gct.dominio.DominioInterface;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public interface StrategyInterface {
    public String processar(DominioInterface entidade);
}
