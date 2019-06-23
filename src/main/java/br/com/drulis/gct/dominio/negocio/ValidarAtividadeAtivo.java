package br.com.drulis.gct.dominio.negocio;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Atividade;

public class ValidarAtividadeAtivo implements StrategyInterface {

	@Override
	public String processar(Entidade entidade) {
		if(entidade.getClass().isInstance(Atividade.class))
	    	if(entidade.getAtivo() > 0 ) 
	    		return null;

    	return "O registro de " + entidade.getClass().getSimpleName() + " está inativo.";
	}

}
