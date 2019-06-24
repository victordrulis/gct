/**
 * 
 */
package br.com.drulis.gct.dominio.negocio;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.core.dao.AtividadeDao;
import br.com.drulis.gct.core.dao.ChamadoDao;
import br.com.drulis.gct.core.dao.ClienteDao;
import br.com.drulis.gct.core.dao.ContatoDao;
import br.com.drulis.gct.core.dao.ContratoDao;
import br.com.drulis.gct.core.dao.DaoInterface;
import br.com.drulis.gct.core.dao.ProdutoDao;
import br.com.drulis.gct.core.dao.UsuarioDao;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Contrato;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.dominio.Usuario;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidarNaoExistenciaStrategy implements StrategyInterface {

	private Map<String, DaoInterface> mapDao;

	public ValidarNaoExistenciaStrategy() {
		this.mapDao = new HashMap<>();
		this.mapDao.put(Contato.class.getName(), new ContatoDao());
        this.mapDao.put(Usuario.class.getName(), new UsuarioDao());
        this.mapDao.put(Contrato.class.getName(), new ContratoDao());
        this.mapDao.put(Cliente.class.getName(), new ClienteDao());
        this.mapDao.put(Produto.class.getName(), new ProdutoDao());
        this.mapDao.put(Atividade.class.getName(), new AtividadeDao());
        this.mapDao.put(Chamado.class.getName(), new ChamadoDao());
	}

    @Override
    public String processar(Entidade entidade) {
    	DaoInterface dao = this.mapDao.get(entidade.getClass().getName());

    	try {
			return dao.consultar(entidade).size() <= 0 ? null : "Existem registros para os dados informados.";
		} catch (SQLException e) {
			System.out.println("[" + this.getClass().getSimpleName() + "] Erro de validação de existência: " + e.getCause());
			e.printStackTrace();
		}
    	
        return "Erro de validação de não existência.";
    }

}
