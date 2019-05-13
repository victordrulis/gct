/**
 * 
 */
package br.com.drulis.gct.dominio.validacao;

import br.com.drulis.gct.command.ConsultarCommand;
import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.util.Resultado;

/**
 * @author Victor Drulis Oliveira
 * @since 17 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidadorContatoNaoExiste implements StrategyInterface {

    private static final String MENSAGEM = "Contato não existe: ";
    private static final ConsultarCommand COMMAND = new ConsultarCommand();
    
    private Resultado resultado = new Resultado();
    
    @Override
    public String processar(Entidade entidade) {
        Contato contato = (Contato) entidade;
        resultado = COMMAND.execute(contato);
        
        if(resultado == null) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + MENSAGEM + "ID = " + contato.getId());
            return MENSAGEM + "ID = " + contato.getId();
        }
        
        return null;
    }

}
