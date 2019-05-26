/**
 * 
 */
package br.com.drulis.gct.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.dominio.Usuario;
import br.com.drulis.gct.dominio.classificacao.ProdutoTipo;
import br.com.drulis.gct.dominio.dashboard.Dashboard;

/**
 * @author Victor Drulis Oliveira
 * @since 14 de fev de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class DashboardDao extends DaoEntidade {

    @Override
    public Entidade inserir(Entidade entidade) {
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Inserir... SEM AÇÃO");
        return null;
    }

    @Override
    public Entidade alterar(Entidade entidade) {
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Alterar ... SEM AÇÃO");
        return null;
    }
    
    @Override
    public Boolean excluir(Entidade entidade) {
        System.out.println("[" + this.getClass().getSimpleName() + "] Excluir... SEM AÇÃO");
        return true;
    }

    @Override
    public List<Entidade> consultar(Entidade entidade) {
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Consulta padrão");
        Dashboard dash = (Dashboard) entidade;
        
        if(dash.getEntidade().getClass() == Atividade.class)
        	return consultarAtividades(dash);

        if(dash.getEntidade().getClass() == Produto.class)
        	return consultarProdutos(dash);
        
        if(dash.getEntidade().getClass() == Cliente.class)
        	return consultarClientes(dash);
        
        return consultarChamados(dash);
    }

    /**
     * Busca chamados ativos com status Abertos e Em Execução, por cliente e produto ou não
     * 
     * @param entidade
     * @return
     */
    public List<Entidade> consultarChamados(Entidade entidade) {
    	System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Consulta de chamados na data e status");
    	PreparedStatement ps = null;
        Dashboard dashboard = (Dashboard) entidade;
        
        List<Entidade> listaDashboards = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT c.* FROM chamado c");
        
        try {
        	sql.append(" WHERE c.status IN (1, 2) AND c.ativo = 1");

        	// TODO
            if (dashboard.getId() > 0) {
                sql.append(" AND c.produto_id = " + dashboard.getId());
            }
            
            // TODO
            if (dashboard.getId() > 0) {
                sql.append(" AND c.cliente_id = " + dashboard.getId());
            }

            this.conectar();
            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Dashboard con = new Dashboard();
                con.setId(resultado.getInt("c.id"));
                con.setAtivo(resultado.getInt("c.ativo"));
                con.setDataInclusao(resultado.getDate("c.data_inclusao"));
                con.setDataAlteracao(resultado.getDate("c.data_alteracao"));
                con.setDataInativacao(resultado.getDate("c.data_inativacao"));
                con.setUsuarioInclusao(new Usuario(resultado.getInt("c.usuario_inclusao_id"),null,null, null));
                con.setUsuarioUpdate(new Usuario(resultado.getInt("c.usuario_alteracao_id"),null,null, null));
                con.setUsuarioInativacao(new Usuario(resultado.getInt("c.usuario_inativacao_id"),null,null, null));
                
                listaDashboards.add(con);
            }
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaDashboards;
    }
    
    /**
     * Busca clientes ativos com status Em produção e Em Homologação e Em Testes
     * 
     * @param entidade
     * @return
     */
    public List<Entidade> consultarClientes(Entidade entidade) {
    	System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Consulta de clientes na data e status");
    	PreparedStatement ps = null;
        Dashboard dashboard = (Dashboard) entidade;
        
        List<Entidade> listaDashboards = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();

        // Busca chamados ativos com status Abertos e Em Execução
        sql.append("SELECT c.* FROM cliente c");
        
        try {
        	sql.append(" WHERE c.status IN (1, 2) AND c.ativo = 1");

        	// TODO
            if (dashboard.getId() > 0) {
                sql.append(" AND c.id = " + dashboard.getId());
            }
            
            this.conectar();
            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Dashboard con = new Dashboard();
                con.setId(resultado.getInt("c.id"));
                con.setAtivo(resultado.getInt("c.ativo"));
                con.setDataInclusao(resultado.getDate("c.data_inclusao"));
                con.setDataAlteracao(resultado.getDate("c.data_alteracao"));
                con.setDataInativacao(resultado.getDate("c.data_inativacao"));
                con.setUsuarioInclusao(new Usuario(resultado.getInt("c.usuario_inclusao_id"),null,null, null));
                con.setUsuarioUpdate(new Usuario(resultado.getInt("c.usuario_alteracao_id"),null,null, null));
                con.setUsuarioInativacao(new Usuario(resultado.getInt("c.usuario_inativacao_id"),null,null, null));
                
                listaDashboards.add(con);
            }
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaDashboards;
    }
    
    /**
     * Busca atividades ativas por todos os status, por chamado
     * 
     * @param entidade
     * @return
     */
    public List<Entidade> consultarAtividades(Entidade entidade) {
    	System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Consulta de atividades na data e status");
    	PreparedStatement ps = null;
        Dashboard dashboard = (Dashboard) entidade;
        
        List<Entidade> listaDashboards = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT a.* FROM atividade a");
        
        try {
        	sql.append(" WHERE a.ativo = 1");

        	// TODO
            if (dashboard.getId() > 0) {
                sql.append(" AND a.chamado_id = " + dashboard.getId());
            }
            
            this.conectar();
            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Dashboard con = new Dashboard();
                con.setId(resultado.getInt("c.id"));
                con.setAtivo(resultado.getInt("c.ativo"));
                con.setDataInclusao(resultado.getDate("c.data_inclusao"));
                con.setDataAlteracao(resultado.getDate("c.data_alteracao"));
                con.setDataInativacao(resultado.getDate("c.data_inativacao"));
                con.setUsuarioInclusao(new Usuario(resultado.getInt("c.usuario_inclusao_id"),null,null, null));
                con.setUsuarioUpdate(new Usuario(resultado.getInt("c.usuario_alteracao_id"),null,null, null));
                con.setUsuarioInativacao(new Usuario(resultado.getInt("c.usuario_inativacao_id"),null,null, null));
                
                listaDashboards.add(con);
            }
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaDashboards;
    }
    
    /**
     * Busca chamados ativos com status Abertos e Em Execução, por cliente e produto ou não
     * 
     * @param entidade
     * @return
     */
    public List<Entidade> consultarProdutos(Entidade entidade) {
    	System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Consulta de chamados na data e status");
    	PreparedStatement ps = null;
        Dashboard dashboard = (Dashboard) entidade;
        
        List<Entidade> listaDashboards = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT produto_status_id, count(*) qtd FROM produto WHERE ativo = 1 GROUP BY produto_status_id  ORDER BY produto_status_id ASC");
        
        try {

            this.conectar();
            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            Map<ProdutoTipo, Integer> mapaTipoProdutos = new HashMap<ProdutoTipo, Integer>();
            
            while (resultado.next())
                mapaTipoProdutos.put(ProdutoTipo.getMapa().get(resultado.getInt("produto_status_id")), resultado.getInt("qtd"));

            dashboard.setMapaTipoProdutos(mapaTipoProdutos);
            listaDashboards.add(dashboard);
            
            mapaTipoProdutos.forEach((tipo, qtde) -> {
            	System.out.println(tipo.getDescricao() + ": " + qtde);
            });
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaDashboards;
    }
}