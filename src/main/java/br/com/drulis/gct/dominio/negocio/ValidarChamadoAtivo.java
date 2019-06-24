/**
 * 
 */
package br.com.drulis.gct.dominio.negocio;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Atividade;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidarChamadoAtivo implements StrategyInterface {

    @Override
    public String processar(Entidade entidade) {

    	if(entidade.getClass().getName().equals(Atividade.class.getName())) {
    		Atividade atividade = (Atividade) entidade;
	    	if(atividade.getChamado() != null && atividade.getChamado().getAtivo() <= 0 ) 
	    		return "O chamado, ao qual a atividade está sendo relacioando, está inativo.";
    	}
    	
    	return null;
    }

}
