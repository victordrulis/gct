/**
 * 
 */
package br.com.drulis.gct.dominio.negocio;

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

	private Map<String, DaoInterface> daos;
	
	public ValidarNaoExistenciaStrategy() {
		this.daos = new HashMap<>();
		this.daos.put(Contato.class.getName(), new ContatoDao());
		this.daos.put(Usuario.class.getName(), new UsuarioDao());
        this.daos.put(Contrato.class.getName(), new ContratoDao());
        this.daos.put(Cliente.class.getName(), new ClienteDao());
        this.daos.put(Produto.class.getName(), new ProdutoDao());
        this.daos.put(Atividade.class.getName(), new AtividadeDao());
        this.daos.put(Chamado.class.getName(), new ChamadoDao());
	}
	
    @Override
    public String processar(Entidade entidade) {
        try {
        	DaoInterface dao = daos.get(entidade.getClass().getName());
	        if(dao.consultar(entidade).size() > 0)
	        	return "Já existem registros com os dados informados.";
        } catch (Exception e) {
        	e.printStackTrace();
        	return "Erro ao verificar existencia de dados: " + e.getMessage();
        }
        
        return null;
    }

}
