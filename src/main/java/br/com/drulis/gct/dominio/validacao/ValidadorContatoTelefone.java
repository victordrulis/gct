/**
 * 
 */
package br.com.drulis.gct.dominio.validacao;

import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.DominioInterface;

/**
 * @author Victor Drulis Oliveira
 * @since 17 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidadorContatoTelefone implements StrategyInterface {

    private static final String MENSAGEM = "Telefone inválido: ";

    @Override
    public String processar(DominioInterface entidade) {
        Contato contato = (Contato) entidade;
        String telefone = contato.getTel();

        if (telefone != null && isValido(telefone)) {
            return null;
        }
        System.out.println("[" + this.getClass().getSimpleName() + "] " + MENSAGEM + telefone);
        return MENSAGEM + telefone;
    }

    private boolean isValido(String numeroTelefone) {
        String regexCel = ".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}";
        String regexTel = ".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}";
        
        return numeroTelefone.matches(regexCel) || numeroTelefone.matches(regexTel);
    }

}
