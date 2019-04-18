/**
 * 
 */
package br.com.drulis.gct.dominio.validacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.DominioInterface;

/**
 * @author Victor Drulis Oliveira
 * @since 17 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidadorContatoEmail implements StrategyInterface {

    private static final String MENSAGEM = "E-mail inválido: ";

    @Override
    public String processar(DominioInterface entidade) {
        Contato contato = (Contato) entidade;
        String email = contato.getEmail();

        if (email != null && isValido(email)) {
            return null;
        }
        
        System.out.println("[" + this.getClass().getSimpleName() + "] " + MENSAGEM + email);
        return MENSAGEM + email;
    }

    private boolean isValido(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        
        return matcher.find();
    }

}
