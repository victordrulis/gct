/**
 * 
 */
package br.com.drulis.gct.core;

import br.com.drulis.gct.core.util.Resultado;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public interface FachadaInterface {
    public Resultado salvar(Entidade entidade);

    public Resultado consultar(Entidade entidade);

    public Resultado alterar(Entidade entidade);

    public Resultado excluir(Entidade entidade);
}
