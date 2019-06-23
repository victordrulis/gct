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
public class ValidarCpfCnpj implements StrategyInterface {

    @Override
    public String processar(Entidade entidade) {
        Contato c = (Contato) entidade;
        String cpfCnpj = c.getCpfCnpj().replaceAll("^[0-9.-]*$","").trim();

        return cpfCnpj.length() == 0 ? null : "CPF/CNPJ inválido";
    }

}
