/**
 * 
 */
package br.com.drulis.gct.dominio.negocio;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.core.dao.ContatoDao;

/**
 * @author Victor Drulis Oliveira
 * @since 22 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidarExistenciaStrategy implements StrategyInterface {

    @Override
    public String processar(Entidade entidade) {
        ContatoDao dao = new ContatoDao();
         return dao.consultar(entidade).size() != 0 ? null : "Contato já existe." ;
    }

}
