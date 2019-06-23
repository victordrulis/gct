/**
 * 
 */
package br.com.drulis.gct.dominio.negocio;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidarAtivo implements StrategyInterface {

    @Override
    public String processar(Entidade entidade) {
    	return entidade.getAtivo() <= 0 || entidade.getAtivo() > 1 ? "O registro de " + entidade.getClass().getSimpleName() + " está inativo." : null;
    }

}
