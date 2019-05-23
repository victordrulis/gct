package br.com.drulis.gct.web.command;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.util.Resultado;

/**
 * @author Victor Drulis Oliveira
 *
 */
public class SalvarCommand extends AbstractCommand {

	@Override
	public Resultado execute(Entidade entidade) {
		System.out.println(this.getClass().getName() + ": SALVAR \n" + entidade.getClass().getName());
		return this.fachada.salvar(entidade);
	}
	
}
