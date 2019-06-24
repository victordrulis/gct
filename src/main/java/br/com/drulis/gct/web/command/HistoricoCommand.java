package br.com.drulis.gct.web.command;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.util.Resultado;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class HistoricoCommand extends AbstractCommand {

    /* (non-Javadoc)
     * @see br.com.drulis.gct.command.CommandInterface#execute(br.com.drulis.gct.dominio.DominioInterface)
     */
    @Override
    public Resultado execute(Entidade entidade) {
        System.out.println("[" + this.getClass().getSimpleName() + "] [HISTORICO] " + entidade.getClass().getSimpleName());
        return this.fachada.historico(entidade);
    }
}
