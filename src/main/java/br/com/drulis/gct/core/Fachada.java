/**
 * 
 */
package br.com.drulis.gct.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.drulis.gct.dao.ContatoDao;
import br.com.drulis.gct.dao.DaoInterface;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.util.Resultado;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Fachada implements FachadaInterface {

    private Resultado resultado;
    private Map<String, DaoInterface> mapDao;
    private Map<String, Map<String, List<StrategyInterface>>> mapRegrasNegocio;
    private Map<String, List<StrategyInterface>> mapRegrasContato;

    /**
     * 
     */
    public Fachada() {
        this.mapDao = new HashMap<String, DaoInterface>();
        this.mapRegrasNegocio = new HashMap<String, Map<String, List<StrategyInterface>>>();
        this.mapRegrasContato = new HashMap<String, List<StrategyInterface>>();

        ContatoDao contatoDao = new ContatoDao();
        this.mapDao.put(Contato.class.getName(), contatoDao);

        List<StrategyInterface> listRegrasSalvar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> listRegrasAlterar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> listRegrasConsultar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> listRegrasExcluir = new ArrayList<StrategyInterface>();

        this.mapRegrasContato.put("SALVAR", listRegrasSalvar);
        this.mapRegrasContato.put("ALTERAR", listRegrasAlterar);
        this.mapRegrasContato.put("CONSULTAR", listRegrasConsultar);
        this.mapRegrasContato.put("EXCLUIR", listRegrasExcluir);

        this.mapRegrasNegocio.put(Contato.class.getName(), mapRegrasContato);
    }

    @Override
    public Resultado salvar(Entidade entidade) {
        this.resultado = new Resultado();
        String nomeEntidade = entidade.getClass().getName();
        String mensagem = this.executarRegras(entidade, Acao.SALVAR);

        if (mensagem == null) {
            DaoInterface dao = this.mapDao.get(nomeEntidade);

            try {
                dao.inserir(entidade);
                List<Entidade> entidades = new ArrayList<Entidade>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
                System.out.println("obj gravado!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(this.getClass().getName() + Mensagem.ERRO_INSERIR.getDescricao() + "-- Entidade: " + entidade.getClass() + "\n" + e.getMessage());
                resultado.setMensagem(Mensagem.ERRO_INSERIR.getDescricao());
            }
        }

        resultado.setMensagem(mensagem);
        return resultado;
    }

    @Override
    public Resultado consultar(Entidade entidade) {
        this.resultado = new Resultado();
        List<Entidade> listaEntidade = new ArrayList<Entidade>();
        String nomeEntidade = entidade.getClass().getName();
        DaoInterface dao = this.mapDao.get(nomeEntidade);

        System.out.println("Fachada: " + nomeEntidade);

        try {
            listaEntidade = dao.consultar(entidade);
            this.resultado.setEntidades(listaEntidade);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(this.getClass().getSimpleName() + entidade.getClass() + Mensagem.ERRO_EXIBIR.getDescricao() + "\n" + e.getMessage());
            this.resultado.setMensagem(Mensagem.ERRO_EXIBIR.getDescricao());
        }

        return this.resultado;
    }

    @Override
    public Resultado alterar(Entidade entidade) {
        resultado = new Resultado();
        String nomeEntidade = entidade.getClass().getName();
        String mensagem = executarRegras(entidade, Acao.ALTERAR);

        if (mensagem == null) {
            DaoInterface dao = this.mapDao.get(nomeEntidade);

            try {
                dao.alterar(entidade);
                List<Entidade> entidades = new ArrayList<Entidade>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
                System.out.println("obj alterado!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(this.getClass().getName() + entidade.getClass()
                        + ": Não foi possível atualizar o registro!" + e.getMessage());
                resultado.setMensagem("Não foi possível atualizar o registro!");
            }
        }

        resultado.setMensagem(mensagem);
        return resultado;
    }

    @Override
    public Resultado excluir(Entidade entidade) {
        resultado = new Resultado();
        String nomeEntidade = entidade.getClass().getName();
        String mensagem = executarRegras(entidade, Acao.EXCLUIR);

        if (mensagem == null) {
            DaoInterface dao = this.mapDao.get(nomeEntidade);

            try {
                dao.excluir(entidade);
                List<Entidade> entidades = new ArrayList<Entidade>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
                System.out.println("obj excluído!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(this.getClass().getName() + entidade.getClass()
                        + ": Não foi possível excluir o registro!" + e.getMessage());
                resultado.setMensagem("Não foi possível excluir o registro!");
            }
        }

        resultado.setMensagem(mensagem);
        return resultado;
    }

    /**
     * Executa as regras de negocio conforme a ação
     * 
     * @param entidade
     * @param acao
     * @return mensagem
     */
    private String executarRegras(Entidade entidade, Acao acao) {
        String nomeEntidade = entidade.getClass().getName();
        StringBuilder mensagem = new StringBuilder();

        Map<String, List<StrategyInterface>> mapRegrasDaAcao = this.mapRegrasNegocio.get(nomeEntidade);

        if (mapRegrasDaAcao != null) {
            List<StrategyInterface> listRegrasDaAcao = mapRegrasDaAcao.get(acao.getAcao());

            if (listRegrasDaAcao != null) {
                for (StrategyInterface regra : listRegrasDaAcao) {
                    String mensagemRegra = regra.processar(entidade);

                    if (mensagemRegra != null) {
                        mensagem.append(mensagemRegra);
                        mensagem.append("\n");
                    }
                }
            }
        }

        if (mensagem.length() > 0) {
            return mensagem.toString();
        } else {
            return null;
        }
    }

}
