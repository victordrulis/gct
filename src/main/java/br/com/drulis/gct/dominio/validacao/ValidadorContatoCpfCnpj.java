/**
 * 
 */
package br.com.drulis.gct.dominio.validacao;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.util.CpfCnpjUtils;

/**
 * @author Victor Drulis Oliveira
 * @since 17 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidadorContatoCpfCnpj implements StrategyInterface {

    private static final String MENSAGEM = "CPF/CNPJ inválido: ";
    
    @Override
    public String processar(Entidade entidade) {
        Contato contato = (Contato) entidade;
        String cpfCnpj = contato.getCpfCnpj();
        
        if(cpfCnpj != null && CpfCnpjUtils.isValid(cpfCnpj)) {
            return null;
        }
        
        System.out.println("[" + this.getClass().getSimpleName() + "] " + MENSAGEM + cpfCnpj);
        return MENSAGEM + cpfCnpj;
    }

}
