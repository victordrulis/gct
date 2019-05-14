/**
 * 
 */
package br.com.drulis.gct.core.negocio;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.StrategyInterface;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Produto;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ValidarCampoNaoVazio implements StrategyInterface {

    @Override
    public String processar(Entidade entidade) {
    	String classe = entidade.getClass().getSimpleName();
    	String campo = null;
        
    	switch(classe) {
		case "Contato":
			Contato contato = (Contato) entidade;
			campo = "nome";
			if(contato.getNome() == null)
				return "Campo " + campo + " não pode ser vazio.";
			break;
		
		case "Atividade":
			Atividade atividade = (Atividade) entidade;
			campo = "título";
			if(atividade.getTitulo() == null)
				return "Campo " + campo + " não pode ser vazio.";
			break;
			
		case "Chamado":
			Chamado chamado = (Chamado) entidade;
			campo = "título";
			if(chamado.getTitulo() == null)
				return "Campo " + campo + " não pode ser vazio.";
			break;
		
		case "Produto":
			Produto produto = (Produto) entidade;
			campo = "título";
			if(produto.getTitulo() == null)
				return "Campo " + campo + " não pode ser vazio.";
			break;
		default:
			break;
		}
    	
        return null;
    }

}
