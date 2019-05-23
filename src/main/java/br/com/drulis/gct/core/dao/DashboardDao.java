/**
 * 
 */
package br.com.drulis.gct.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.util.PdfGenerator;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Usuario;
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
        System.out.println("[" + this.getClass().getSimpleName() + "] Inserir");
        PreparedStatement ps = null;
        Dashboard dashboard = (Dashboard) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("INSERT INTO dashboard (nome, cpf_cnpj, telefone, email, ativo, usuario_inclusao_id, data_inclusao)");
            sql.append(" VALUES (?,?,?,?,?,?,?)");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(5, 1);
            ps.setInt(6, 1);
            ps.setTimestamp(7, dataInclusao);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            while(rs.next()) {
                dashboard.setId(rs.getInt(1));
            }
            
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_INSERIR.getDescricao() +" id: " + dashboard.getId());
            return dashboard;
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_INSERIR.getDescricao() + "\n: " + e);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_INSERIR.getDescricao() + "\n: " + e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Entidade alterar(Entidade entidade) {
        System.out.println("[" + this.getClass().getSimpleName() + "] Alterar");
        Dashboard dashboard = new Dashboard();
        Dashboard alterado = new Dashboard();
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps = null;

        alterado = (Dashboard) entidade;

        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("UPDATE dashboard SET ");
            sql.append("telefone = ?, ");
            sql.append("email = ?, ");
            sql.append("ativo = ?, ");
            sql.append("usuario_alteracao_id = ? ");
            sql.append("WHERE id = ?");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(3, alterado.getAtivo());
            ps.setInt(4, 1);
            ps.setInt(5, alterado.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()) {
                alterado.setId(rs.getInt(1));
            }
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_ATUALIZAR.getDescricao() + ", id: " + dashboard.getId());
            return alterado;
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_ATUALIZAR + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_ATUALIZAR + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Entidade> consultar(Entidade entidade) {
        System.out.println("[" + this.getClass().getSimpleName() + "] Consultar");
        
        PreparedStatement ps = null;
        Dashboard dashboard = (Dashboard) entidade;
        
        if(dashboard.getUsuarioInclusao() == null) {
            dashboard.setUsuarioInclusao(new Usuario());
            dashboard.setUsuarioUpdate(new Usuario());
            dashboard.setUsuarioInativacao(new Usuario());
        }
        
        List<Entidade> listaDashboards = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT c.* FROM dashboard c WHERE 1 = 1 ");
        
        try {
            this.conectar();

            if (dashboard.getId() > 0) {
                sql.append(" AND c.id = " + dashboard.getId());
            }

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

    @Override
    public Boolean excluir(Entidade entidade) {
        PreparedStatement ps = null;
        Dashboard dashboard = (Dashboard) entidade;
        StringBuilder sql = new StringBuilder();
        
        System.out.println("[" + this.getClass().getSimpleName() + "] Excluir id: " + dashboard.getId());
        
        try {
            this.conectar();
            this.sessaoBD.setAutoCommit(false);
            sql.append("UPDATE dashboard SET ativo = 0 WHERE id = ?");
            ps = this.sessaoBD.prepareStatement(sql.toString());
            ps.setInt(1,  dashboard.getId());
            ps.executeUpdate();
            this.sessaoBD.commit();
            return true;
        } catch(SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXCLUIR.getDescricao()+ " --- id: " + dashboard.getId() + e.getMessage());
            return false;
        }
    }
}
