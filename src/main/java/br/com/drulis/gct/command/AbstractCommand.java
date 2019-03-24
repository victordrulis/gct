package br.com.drulis.gct.command;

import br.com.drulis.gct.core.Fachada;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public abstract class AbstractCommand implements CommandInterface {
    public Fachada fachada = new Fachada();
}
