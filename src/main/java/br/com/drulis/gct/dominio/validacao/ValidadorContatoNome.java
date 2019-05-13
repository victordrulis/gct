/**
 * 
 */
package br.com.drulis.gct.dominio.validacao;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Contato;

/**
 * @author Victor Drulis Oliveira
 * @since 17 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidadorContatoNome implements StrategyInterface {

    private static final String MENSAGEM = "Nome inválido: ";

    @Override
    public String processar(Entidade entidade) {
        Contato contato = (Contato) entidade;
        String nome = contato.getNome();

        if (nome != null && isValido(nome)) {
            return null;
        }

        System.out.println("[" + this.getClass().getSimpleName() + "] " + MENSAGEM + nome);
        return MENSAGEM + nome;
    }

    private boolean isValido(String nome) {
        String regexTamanhoNome = ".[a-zA-Z]{1,150}";
        
        return nome.matches(regexTamanhoNome);
    }

}
