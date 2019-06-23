/**
 * 
 */
package br.com.drulis.gct.dominio.negocio;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Chamado;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidarChamadoAtivo implements StrategyInterface {

    @Override
    public String processar(Entidade entidade) {

    	if(entidade.getClass().isInstance(Chamado.class))
	    	if(entidade.getAtivo() > 0 ) 
	    		return null;

    	return "O registro de " + entidade.getClass().getSimpleName() + " está inativo.";
    }

}
