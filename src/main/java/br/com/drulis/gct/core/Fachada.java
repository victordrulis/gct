/**
 * 
 */
package br.com.drulis.gct.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.drulis.gct.core.dao.AtividadeDao;
import br.com.drulis.gct.core.dao.ChamadoDao;
import br.com.drulis.gct.core.dao.ClienteDao;
import br.com.drulis.gct.core.dao.ContatoDao;
import br.com.drulis.gct.core.dao.ContratoDao;
import br.com.drulis.gct.core.dao.DaoInterface;
import br.com.drulis.gct.core.dao.DashboardDao;
import br.com.drulis.gct.core.dao.ProdutoDao;
import br.com.drulis.gct.core.dao.UsuarioDao;
import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Contrato;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.dominio.Usuario;
import br.com.drulis.gct.dominio.dashboard.Dashboard;
import br.com.drulis.gct.dominio.negocio.RegrasFactory;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Fachada implements FachadaInterface {

    private Resultado resultado;
    private RegrasFactory rnf;
    private Map<String, DaoInterface> mapDao;
    private Map<String, Map<String, List<StrategyInterface>>> mapRegrasNegocio;
    private Map<String, List<StrategyInterface>> mapRegrasContato;
    private Map<String, List<StrategyInterface>> mapRegrasUsuario;
    private Map<String, List<StrategyInterface>> mapRegrasContrato;
    private Map<String, List<StrategyInterface>> mapRegrasCliente;
    private Map<String, List<StrategyInterface>> mapRegrasProduto;
    private Map<String, List<StrategyInterface>> mapRegrasAtividade;
    private Map<String, List<StrategyInterface>> mapRegrasChamado;
    private Map<String, List<StrategyInterface>> mapRegrasDashboard;

    public Fachada() {
        this.mapDao = new HashMap<String, DaoInterface>();
        this.mapRegrasNegocio = new HashMap<String, Map<String, List<StrategyInterface>>>();
        this.mapRegrasContato = new HashMap<String, List<StrategyInterface>>();
        this.mapRegrasUsuario = new HashMap<String, List<StrategyInterface>>();
        this.mapRegrasContrato = new HashMap<String, List<StrategyInterface>>();
        this.mapRegrasCliente = new HashMap<String, List<StrategyInterface>>();
        this.mapRegrasProduto = new HashMap<String, List<StrategyInterface>>();
        this.mapRegrasAtividade = new HashMap<String, List<StrategyInterface>>();
        this.mapRegrasChamado = new HashMap<String, List<StrategyInterface>>();
        this.mapRegrasDashboard = new HashMap<String, List<StrategyInterface>>();

        this.rnf = new RegrasFactory();
        
        this.mapDao.put(Contato.class.getName(), new ContatoDao());
        this.mapDao.put(Usuario.class.getName(), new UsuarioDao());
        this.mapDao.put(Contrato.class.getName(), new ContratoDao());
        this.mapDao.put(Cliente.class.getName(), new ClienteDao());
        this.mapDao.put(Produto.class.getName(), new ProdutoDao());
        this.mapDao.put(Atividade.class.getName(), new AtividadeDao());
        this.mapDao.put(Chamado.class.getName(), new ChamadoDao());
        this.mapDao.put(Dashboard.class.getName(), new DashboardDao());

        List<StrategyInterface> listRegrasSalvar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> listRegrasAlterar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> listRegrasConsultar = new ArrayList<StrategyInterface>();
        List<StrategyInterface> listRegrasExcluir = new ArrayList<StrategyInterface>();
        
        this.mapRegrasContato.put("salvar", Arrays.asList(rnf.getValidarCpfCnpj(), rnf.getValidarEmail(), rnf.getValidarNaoExistencia()));
        this.mapRegrasContato.put("alterar", Arrays.asList(rnf.getValidarExistencia(), rnf.getValidarAtivo()));
        this.mapRegrasContato.put("consultar", Arrays.asList(rnf.getValidarRangeDeDatas()));
        this.mapRegrasContato.put("excluir",  Arrays.asList(rnf.getValidarExistencia(), rnf.getValidarAtivo()));
        
        this.mapRegrasUsuario.put("salvar", listRegrasSalvar);
        this.mapRegrasUsuario.put("alterar", Arrays.asList(rnf.getValidarAtivo()));
        this.mapRegrasUsuario.put("consultar", listRegrasConsultar);
        this.mapRegrasUsuario.put("excluir", listRegrasExcluir);
        
        this.mapRegrasCliente.put("salvar", listRegrasSalvar);
        this.mapRegrasCliente.put("alterar", Arrays.asList(rnf.getValidarAtivo()));
        this.mapRegrasCliente.put("consultar", listRegrasConsultar);
        this.mapRegrasCliente.put("excluir", Arrays.asList(rnf.getValidarAtivo()));
        
        this.mapRegrasProduto.put("salvar", listRegrasSalvar);
        this.mapRegrasProduto.put("alterar", Arrays.asList(rnf.getValidarAtivo()));
        this.mapRegrasProduto.put("consultar", listRegrasConsultar);
        this.mapRegrasProduto.put("excluir", listRegrasExcluir);

        this.mapRegrasContrato.put("salvar", listRegrasSalvar);
        this.mapRegrasContrato.put("alterar", listRegrasAlterar);
        this.mapRegrasContrato.put("consultar", listRegrasConsultar);
        this.mapRegrasContrato.put("excluir", listRegrasExcluir);
        
        this.mapRegrasAtividade.put("salvar", Arrays.asList(rnf.getValidarChamadoAtivo()));
        this.mapRegrasAtividade.put("alterar", Arrays.asList(rnf.getValidarAtivo()));
        this.mapRegrasAtividade.put("consultar", Arrays.asList(rnf.getValidarRangeDeDatas()));
        this.mapRegrasAtividade.put("excluir", Arrays.asList(rnf.getValidarAtivo()));
        this.mapRegrasAtividade.put("historico", Arrays.asList());
        
        this.mapRegrasChamado.put("salvar", Arrays.asList(rnf.getValidarClienteAtivo(), rnf.getValidarProdutoAtivo(), rnf.getValidarUsuarioAtivo()));
        this.mapRegrasChamado.put("alterar", Arrays.asList(rnf.getValidarExistencia(), rnf.getValidarAtivo()));
        this.mapRegrasChamado.put("consultar", Arrays.asList(rnf.getValidarRangeDeDatas()));
        this.mapRegrasChamado.put("excluir", Arrays.asList(rnf.getValidarAtivo()));
        this.mapRegrasChamado.put("historico", Arrays.asList());
        
        this.mapRegrasDashboard.put("nr", null);
        
        this.mapRegrasNegocio.put(Contato.class.getName(), mapRegrasContato);
        this.mapRegrasNegocio.put(Usuario.class.getName(), mapRegrasUsuario);
        this.mapRegrasNegocio.put(Contrato.class.getName(), mapRegrasContrato);
        this.mapRegrasNegocio.put(Cliente.class.getName(), mapRegrasCliente);
        this.mapRegrasNegocio.put(Produto.class.getName(), mapRegrasProduto);
        this.mapRegrasNegocio.put(Atividade.class.getName(), mapRegrasAtividade);
        this.mapRegrasNegocio.put(Chamado.class.getName(), mapRegrasChamado);
        this.mapRegrasNegocio.put(Dashboard.class.getName(), mapRegrasDashboard);
        
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
                System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] " + entidade.getClass().getSimpleName() + " salvo. ID: " + resultado.getEntidades().get(0).getId());
                mensagem = "Dados salvos com sucesso.";
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
                mensagem = "Dados alterados com sucesso.";
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
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Excluindo registros de " + entidade.getClass().getSimpleName());

        if (mensagem == null) {
            DaoInterface dao = this.mapDao.get(nomeEntidade);

            try {
                dao.excluir(entidade);
                List<Entidade> entidades = new ArrayList<Entidade>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
                System.out.println("[" + this.getClass().getSimpleName() + "] Registro excluído: " + entidade.getClass().getSimpleName() + ", Id: " + entidade.getId());
                mensagem = "Excluido com sucesso.";
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("[" + this.getClass().getName() + entidade.getClass() + "] " + Mensagem.ERRO_EXCLUIR.getDescricao() + ":\n" + e.getMessage());
                resultado.setMensagem(Mensagem.ERRO_EXCLUIR.getDescricao() + ": " + e.getMessage());
            }
        }

        System.out.println("[" + this.getClass().getSimpleName() + "] Registro excluído: " + entidade.getClass().getSimpleName() + ", Id: " + entidade.getId() + " - " + mensagem);
        resultado.setMensagem(mensagem);
        return resultado;
    }
    
    public Resultado historico(Entidade entidade) {
        this.resultado = new Resultado();
        List<Entidade> listaEntidade = new ArrayList<Entidade>();
        String nomeEntidade = entidade.getClass().getName();
        DaoInterface dao = this.mapDao.get(nomeEntidade);

        System.out.println("[" + this.getClass().getSimpleName() + "] Histórico de registros de " + entidade.getClass().getSimpleName());

        try {
            listaEntidade = dao.historico(entidade);
            this.resultado.setEntidades(listaEntidade);
            System.out.println("[" + this.getClass().getSimpleName() + "] Registro encontrados do tipo " + entidade.getClass().getSimpleName() + ": " + listaEntidade.size());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + "\n" + e.getMessage());
            this.resultado.setMensagem(Mensagem.ERRO_EXIBIR.getDescricao());
        }

        return this.resultado;
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

        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] " + mapRegrasDaAcao.getClass().getSimpleName());
        
        if (mapRegrasDaAcao != null) {
            List<StrategyInterface> listRegrasDaAcao = mapRegrasDaAcao.get(acao.getAcao());

            if (listRegrasDaAcao != null) {
                for (StrategyInterface regra : listRegrasDaAcao) {
                    String mensagemRegra = regra.processar(entidade);

                    if (mensagemRegra != null) {
                        mensagem.append(mensagemRegra);
                        mensagem.append("\n");
                        System.out.println("[" + this.getClass().getSimpleName() + "] [WARNING] " + mensagem);
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
