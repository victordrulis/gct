package br.com.drulis.gct.core.negocio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.core.StrategyInterface;

/**
 * @author Victor Drulis Oliveira
 * @since 15 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ContatoListaRegras extends ListaRegras {

    public Map<String, List<StrategyInterface>> getMapaRegras() {
        Map<String, List<StrategyInterface>> mapaRegras = new HashMap<String, List<StrategyInterface>>();
        mapaRegras.put(Acao.CONSULTAR.getAcao(), this.regrasConsultar);
        mapaRegras.put(Acao.SALVAR.getAcao(), this.regrasSalvar);
        mapaRegras.put(Acao.ALTERAR.getAcao(), this.regrasAlterar);
        mapaRegras.put(Acao.EXCLUIR.getAcao(), this.regrasExcluir);
        return mapaRegras;
    }

}
