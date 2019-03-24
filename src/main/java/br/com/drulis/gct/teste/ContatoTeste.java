/**
 * 
 */
package br.com.drulis.gct.teste;

import br.com.drulis.gct.dao.ContatoDao;
import br.com.drulis.gct.dominio.Contato;

/**
 * @author Victor Drulis Oliveira
 * @since 14 de fev de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ContatoTeste {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Contato contato = new Contato();
        ContatoDao dao = new ContatoDao();
        
        contato.setAtivo(1);
        contato.setCpfCnpj("12342342");
        contato.setEmail("teste@teste.com.br");
        contato.setNome("Teste");
        contato.setStatus(1);
        
        dao.inserir(contato);
        dao.consultar(contato);
    }

}
