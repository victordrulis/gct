/**
 * 
 */
package br.com.drulis.gct.dominio.negocio;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.core.dao.ContatoDao;
import br.com.drulis.gct.dominio.Contato;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidarNaoExistenciaStrategy implements StrategyInterface {

    @Override
    public String processar(Entidade entidade) {
    	ContatoDao dao = new ContatoDao();
    	Contato con = (Contato) entidade;
    	
        try {

        	for(Entidade e: dao.consultar(entidade)) {
        		Contato res = (Contato) e;
        		
        		if(con.getCpfCnpj().equals(res.getCpfCnpj()))
        			return "O contato já existe para o CPF: " +  res.getCpfCnpj();
        		
        		if(con.getEmail().equalsIgnoreCase(res.getEmail()))
        			return "O contato já existe para o e-mail: " + res.getEmail();
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	return "Erro ao verificar existencia de dados: \n" + e.getCause();
        }
        
        return null;
    }

}
