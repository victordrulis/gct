package br.com.drulis.gct.web.command;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.util.Resultado;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public interface CommandInterface {
    public Resultado execute(Entidade entidade);
}
