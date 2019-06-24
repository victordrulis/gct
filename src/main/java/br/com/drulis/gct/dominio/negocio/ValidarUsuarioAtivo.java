package br.com.drulis.gct.dominio.negocio;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Usuario;

public class ValidarUsuarioAtivo implements StrategyInterface {

	@Override
	public String processar(Entidade entidade) {
		if(entidade.getClass().equals(Usuario.class.getName())) {
			return entidade.getAtivo() > 0 ? null : "O registro do Usuario está inativo.";
		}
		
		return null;
	}

}
