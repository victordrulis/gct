/**
 * 
 */
package br.com.drulis.gct.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.drulis.gct.core.negocio.RegrasFactory;
import br.com.drulis.gct.dao.ClienteDao;
import br.com.drulis.gct.dao.ContatoDao;
import br.com.drulis.gct.dao.ContratoDao;
import br.com.drulis.gct.dao.DaoInterface;
import br.com.drulis.gct.dao.ProdutoDao;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Contrato;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.util.Resultado;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Fachada implements FachadaInterface {

    private Resultado resultado;
    private RegrasFactory regrasFactory;
    private Map<String, DaoInterface> mapDao;
    private Map<String, Map<String, List<StrategyInterface>>> mapRegrasNegocio;
    private Map<String, List<StrategyInterface>> mapRegrasContato;
    private Map<String, List<StrategyInterface>> mapRegrasContrato;
    private Map<String, List<StrategyInterface>> mapRegrasCliente;
    private Map<String, List<StrategyInterface>> mapRegrasProduto;

    public Fachada() {
        this.mapDao = new HashMap<String, DaoInterface>();
        this.mapRegrasNegocio = new HashMap<String, Map<String, List<StrategyInterface>>>();
        this.mapRegrasContato = new HashMap<String, List<StrategyInterface>>();
        this.mapRegrasContrato = new HashMap<String, List<StrategyInterface>>();
        this.mapRegrasCliente = new HashMap<String, List<StrategyInterface>>();
        this.mapRegrasProduto = new HashMap<String, List<StrategyInterface>>();

        this.regrasFactory = new RegrasFactory();
        
        ContatoDao contatoDao = new ContatoDao();
        ContratoDao contratoDao = new ContratoDao();
        ClienteDao clienteDao = new ClienteDao();
        ProdutoDao produtoDao = new ProdutoDao();
        this.mapDao.put(Contato.class.getName(), contatoDao);
        this.mapDao.put(Contrato.class.getName(), contratoDao);
        this.mapDao.put(Cliente.class.getName(), clienteDao);
        this.mapDao.put(Produto.class.getName(), produtoDao);

        List<StrategyInterface> listRegrasSalvar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> listRegrasAlterar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> listRegrasConsultar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> listRegrasExcluir = new ArrayList<StrategyInterface>();
        List<StrategyInterface> contatoRegrasSalvar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> contatoRegrasAlterar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> contatoRegrasConsultar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> contatoRegrasExcluir = new ArrayList<StrategyInterface>();

        contatoRegrasConsultar.add(this.regrasFactory.getValidarFormatoData());
        contatoRegrasConsultar.add(this.regrasFactory.getValidarNãoVazio());
        contatoRegrasSalvar.add(this.regrasFactory.getValidarCpfCnpj());
        contatoRegrasSalvar.add(this.regrasFactory.getValidarEmail());
        contatoRegrasSalvar.add(this.regrasFactory.getValidarNaoExistencia());
        contatoRegrasAlterar.add(this.regrasFactory.getValidarExistencia());
        contatoRegrasAlterar.add(this.regrasFactory.getValidarAtivo());
        contatoRegrasAlterar.add(this.regrasFactory.getValidarTelefoneComDDD());
        contatoRegrasExcluir.add(this.regrasFactory.getValidarAtivo());
        this.mapRegrasContato.put("SALVAR", contatoRegrasSalvar);
        this.mapRegrasContato.put("ALTERAR", contatoRegrasAlterar);
        this.mapRegrasContato.put("CONSULTAR", contatoRegrasConsultar);
        this.mapRegrasContato.put("EXCLUIR", contatoRegrasExcluir);
        
        this.mapRegrasCliente.put("SALVAR", listRegrasSalvar);
        this.mapRegrasCliente.put("ALTERAR", listRegrasAlterar);
        this.mapRegrasCliente.put("CONSULTAR", listRegrasConsultar);
        this.mapRegrasCliente.put("EXCLUIR", listRegrasExcluir);
        
        this.mapRegrasProduto.put("SALVAR", listRegrasSalvar);
        this.mapRegrasProduto.put("ALTERAR", listRegrasAlterar);
        this.mapRegrasProduto.put("CONSULTAR", listRegrasConsultar);
        this.mapRegrasProduto.put("EXCLUIR", listRegrasExcluir);

        this.mapRegrasContrato.put("SALVAR", listRegrasSalvar);
        this.mapRegrasContrato.put("ALTERAR", listRegrasAlterar);
        this.mapRegrasContrato.put("CONSULTAR", listRegrasConsultar);
        this.mapRegrasContrato.put("EXCLUIR", listRegrasExcluir);
        
        this.mapRegrasNegocio.put(Contato.class.getName(), mapRegrasContato);
        this.mapRegrasNegocio.put(Contrato.class.getName(), mapRegrasContrato);
        this.mapRegrasNegocio.put(Cliente.class.getName(), mapRegrasCliente);
        this.mapRegrasNegocio.put(Produto.class.getName(), mapRegrasProduto);
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
                System.out.println("[" + this.getClass().getSimpleName() + "] " + entidade.getClass().getSimpleName() + " salvo. ID: " + resultado.getEntidades().get(0).getId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(this.getClass().getSimpleName() + Mensagem.ERRO_INSERIR.getDescricao() + "-- Entidade: " + entidade.getClass() + "\n" + e.getMessage());
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

        System.out.println("[" + this.getClass().getSimpleName() + "] Consultando registros de " + entidade.getClass().getSimpleName());

        try {
            listaEntidade = dao.consultar(entidade);
            this.resultado.setEntidades(listaEntidade);
            System.out.println("[" + this.getClass().getSimpleName() + "] Registro encontrados do tipo " + entidade.getClass().getSimpleName() + ": " + listaEntidade.size());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + "\n" + e.getMessage());
            this.resultado.setMensagem(Mensagem.ERRO_EXIBIR.getDescricao());
        }

        return this.resultado;
    }

    @Override
    public Resultado alterar(Entidade entidade) {
        resultado = new Resultado();
        String nomeEntidade = entidade.getClass().getName();
        String mensagem = executarRegras(entidade, Acao.ALTERAR);
        System.out.println("[" + this.getClass().getSimpleName() + "] Alterando registros de " + entidade.getClass().getSimpleName());

        if (mensagem == null) {
            DaoInterface dao = this.mapDao.get(nomeEntidade);

            try {
                dao.alterar(entidade);
                List<Entidade> entidades = new ArrayList<Entidade>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
                System.out.println("[" + this.getClass().getSimpleName() + "] Registro alterado: " + entidade.getClass().getSimpleName() + ", Id: " + entidade.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("[" + this.getClass().getName() + entidade.getClass() + "] " + Mensagem.ERRO_ATUALIZAR.getDescricao() + ":\n" + e.getMessage());
                resultado.setMensagem(Mensagem.ERRO_ATUALIZAR.getDescricao());
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
        System.out.println("[" + this.getClass().getSimpleName() + "] Excluindo registros de " + entidade.getClass().getSimpleName());

        if (mensagem == null) {
            DaoInterface dao = this.mapDao.get(nomeEntidade);

            try {
                dao.excluir(entidade);
                List<Entidade> entidades = new ArrayList<Entidade>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
                System.out.println("[" + this.getClass().getSimpleName() + "] Registro excluído: " + entidade.getClass().getSimpleName() + ", Id: " + entidade.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("[" + this.getClass().getName() + entidade.getClass() + "] " + Mensagem.ERRO_EXCLUIR.getDescricao() + ":\n" + e.getMessage());
                resultado.setMensagem(Mensagem.ERRO_EXCLUIR.getDescricao());
            }
        }

        System.out.println("[" + this.getClass().getSimpleName() + "] Registro excluído: " + entidade.getClass().getSimpleName() + ", Id: " + entidade.getId() + " - " + mensagem);
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

        System.out.println("[" + this.getClass().getSimpleName() + "] " + mapRegrasDaAcao.getClass().getSimpleName());
        
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
