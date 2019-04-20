package br.com.drulis.gct.command;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.util.Resultado;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class AlterarCommand extends AbstractCommand {

    /* (non-Javadoc)
     * @see br.com.drulis.gct.command.CommandInterface#execute(br.com.drulis.gct.core.Entidade)
     */
    @Override
    public Resultado execute(Entidade entidade) {
        System.out.println("[" + this.getClass().getSimpleName() + "] " + entidade.getClass().getSimpleName());
        return this.fachada.alterar(entidade);
    }

}
