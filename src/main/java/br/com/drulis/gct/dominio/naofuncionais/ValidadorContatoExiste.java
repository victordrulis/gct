/**
 * 
 */
package br.com.drulis.gct.dominio.naofuncionais;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.web.command.ConsultarCommand;

/**
 * @author Victor Drulis Oliveira
 * @since 17 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidadorContatoExiste implements StrategyInterface {

    private static final String MENSAGEM = "Contato já existe: ";
    private static final ConsultarCommand COMMAND = new ConsultarCommand();
    
    private Resultado resultado = new Resultado();
    
    @Override
    public String processar(Entidade entidade) {
        Contato contato = (Contato) entidade;
        resultado = COMMAND.execute(contato);
        
        if(resultado != null && (resultado.getEntidades().size() > 0 || resultado.getEntidade() != null)) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + MENSAGEM + "ID = " + resultado.getEntidades().get(0).getId());
            return MENSAGEM + "ID = " + resultado.getEntidades().get(0).getId();
        }
        
        return null;
    }

}
