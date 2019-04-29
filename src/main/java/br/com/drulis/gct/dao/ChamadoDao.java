package br.com.drulis.gct.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.dominio.Usuario;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ChamadoDao extends DaoEntidade {

    @Override
    public Entidade inserir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Inserir");
        PreparedStatement ps = null;
        Chamado chamado = (Chamado) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("INSERT INTO chamado (titulo, descricao, status, ativo, usuario_atribuido_id, usuario_inclusao_id, data_inclusao, produto_id, cliente_id)");
            sql.append(" VALUES (?,?,?,?,?,?,?,?,?)");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, chamado.getTitulo());
            ps.setString(2, chamado.getDescricao());
            ps.setInt(3, chamado.getStatus());
            ps.setInt(4, chamado.getAtivo());
            ps.setInt(5, chamado.getUsuarioAtribuido().getId());
            ps.setInt(6, 1);
            ps.setTimestamp(7, dataInclusao);
            ps.setInt(8, chamado.getProduto().getId());
            ps.setInt(9, chamado.getCliente().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()) {
                chamado.setId(rs.getInt(1));
            }
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_INSERIR.getDescricao() +" id: " + chamado.getId());
            return chamado;
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_INSERIR.getDescricao() + "\n: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_INSERIR.getDescricao() + "\n: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Entidade alterar(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Alterar");
        Chamado alterado = (Chamado) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps = null;

        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            
            sql.append("UPDATE chamado SET ");
            sql.append("usuario_atribuido_id = ?, ");
            sql.append("titulo = ?, ");
            sql.append("descricao = ?, ");
            sql.append("status = ?, ");
            sql.append("ativo = ?, ");
            sql.append("usuario_alteracao_id = ?, ");
            sql.append("data_alteracao = now(), ");
            sql.append("produto_id = ?, ");
            sql.append("cliente_id = ? ");
            sql.append("WHERE id = ?");
            
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alterado.getUsuarioAtribuido().getId());
            ps.setString(2, alterado.getTitulo());
            ps.setString(3, alterado.getDescricao());
            ps.setInt(5, alterado.getStatus());
            ps.setInt(5, alterado.getAtivo());
            ps.setInt(6, 1);
            ps.setInt(7, alterado.getProduto().getId());
            ps.setInt(8, alterado.getCliente().getId());
            ps.setInt(9, alterado.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            while(rs.next()) {
                alterado.setId(rs.getInt(1));
            }
            
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_ATUALIZAR.getDescricao() + ", id: " + alterado.getId());
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
    public List<Entidade> consultar(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Consultar");
        PreparedStatement ps = null;
        Chamado chamado = (Chamado) entidade;
        List<Entidade> listaChamados = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT c.* FROM chamado c WHERE 1 = 1 ");
        
        try {
            this.conectar();

            if (chamado.getId() > 0) {
                sql.append(" OR c.id = " + chamado.getId());
            }
            
            if (chamado.getProduto().getId() > 0) {
                sql.append(" OR c.produto_id = " + chamado.getProduto().getId());
            }
            
            if (chamado.getCliente().getId() > 0) {
                sql.append(" OR c.cliente_id = " + chamado.getCliente().getId());
            }
            
            if (chamado.getUsuarioAtribuido().getId() > 0) {
                sql.append(" OR c.usuario_atribuido_id = " + chamado.getUsuarioAtribuido().getId());
            }

            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Chamado cham = new Chamado();
                Usuario usuarioAtribuido = new Usuario();
                Cliente cliente = new Cliente();
                Produto produto = new Produto();
                
                usuarioAtribuido.setId(resultado.getInt("c.usuario_atribuido_id"));
                cliente.setId(resultado.getInt("c.cliente_id"));
                produto.setId(resultado.getInt("c.produto_id"));
                
                cham.setUsuarioAtribuido(usuarioAtribuido);
                cham.setCliente(cliente);
                cham.setProduto(produto);
                cham.setId(resultado.getInt("c.id"));
                cham.setTitulo(resultado.getString("c.titulo"));
                cham.setDescricao(resultado.getString("c.descricao"));
                cham.setDataInclusao(resultado.getDate("c.data_inclusao"));
                cham.setDataAlteracao(resultado.getDate("c.data_alteracao"));
                cham.setDataInativacao(resultado.getDate("c.data_inativacao"));
                
                listaChamados.add(cham);
            }
            
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaChamados;
    }

    @Override
    public Boolean excluir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Excluir id: " + entidade.getId());
        PreparedStatement ps = null;
        Chamado chamado = (Chamado) entidade;
        StringBuilder sql = new StringBuilder();
        
        try {
            this.conectar();
            this.sessaoBD.setAutoCommit(false);
            sql.append("UPDATE chamado SET ativo = 0 WHERE id = ?");
            ps = this.sessaoBD.prepareStatement(sql.toString());
            ps.setInt(1,  chamado.getId());
            ps.executeUpdate();
            this.sessaoBD.commit();
            return true;
        } catch(SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXCLUIR.getDescricao()+ " - Chamado id: " + chamado.getId() + e.getMessage());
            return false;
        }
    }

}
