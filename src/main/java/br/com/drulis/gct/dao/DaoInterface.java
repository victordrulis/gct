/**
 * 
 */
package br.com.drulis.gct.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.drulis.gct.core.Entidade;

/**
 * @author Victor Drulis Oliveira
 * @since 14 de fev de 2019
 * @contact victordrulis@gmail.com
 *
 */
public interface DaoInterface {
    public Entidade inserir(Entidade entidade) throws SQLException;
    public Entidade alterar(Entidade entidade)throws SQLException;
    public List<Entidade> consultar(Entidade entidade)throws SQLException;
    public Boolean excluir(Entidade entidade)throws SQLException;
}
