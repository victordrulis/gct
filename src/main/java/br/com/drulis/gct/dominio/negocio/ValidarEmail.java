/**
 * 
 */
package br.com.drulis.gct.dominio.negocio;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Contato;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidarEmail implements StrategyInterface {

    @Override
    public String processar(Entidade entidade) {
        Contato contato = (Contato) entidade;
    	return contato.getEmail() == null ? "E-mail é obrigatório.": null;
    }

}
