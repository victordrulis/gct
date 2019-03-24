package br.com.drulis.gct.command;

import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.util.Resultado;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class InserirCommand extends AbstractCommand {

    /* (non-Javadoc)
     * @see br.com.drulis.gct.command.CommandInterface#execute(br.com.drulis.gct.dominio.DominioInterface)
     */
    @Override
    public Resultado execute(Entidade entidade) {
        System.out.println("Command: " + Acao.SALVAR.getClass().getSimpleName());
        return this.fachada.salvar(entidade);
    }

}
