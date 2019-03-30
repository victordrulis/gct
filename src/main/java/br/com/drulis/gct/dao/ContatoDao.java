/**
 * 
 */
package br.com.drulis.gct.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Mensagem;

/**
 * @author Victor Drulis Oliveira
 * @since 14 de fev de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ContatoDao extends DaoEntidade {

    @Override
    public Entidade inserir(Entidade entidade) {
        System.out.println(this.getClass() + ": Inserir");
        PreparedStatement ps = null;
        Contato contato = (Contato) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        
        try {
            this.conectar();
            conexao.setAutoCommit(false);
            sql.append("INSERT INTO contato (nome, cpf_cnpj, telefone, email, ativo, usuario_inclusao_id, data_inclusao)");
            sql.append(" VALUES (?,?,?,?,?,?,?)");
            ps = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getCpfCnpj());
            ps.setString(3, contato.getTel());
            ps.setString(4, contato.getEmail());
            ps.setInt(5, contato.getAtivo());
//            ps.setInt(6, contato.getUsuarioInclusao().getId());
            ps.setInt(6, 1);
            ps.setTimestamp(7, dataInclusao);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            System.out.println("ResultSet: " + rs.getFetchSize());
            while(rs.next()) {
                contato.setId(rs.getInt(1));
            }
            conexao.commit();
            ps.close();
            rs.close();
            System.out.println(this.getClass().getName() + Mensagem.OK_INSERIR.getDescricao() +" id: " + contato.getId());
            return contato;
        } catch (SQLException e) {
            System.out.println(this.getClass().getName() + Mensagem.ERRO_INSERIR.getDescricao() + "\n: " + e);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + Mensagem.ERRO_INSERIR.getDescricao() + "\n: " + e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Entidade alterar(Entidade entidade) {
        System.out.println(this.getClass().getName() + ": Alterando Contato");
        Contato contato = new Contato();
        Contato alterado = new Contato();
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps = null;

        alterado = (Contato) entidade;
        
        if(entidade.getId() > 0 && entidade.getDataInclusao() != null) {
            contato = (Contato) this.consultar(entidade).get(0);
        }
        
        if(contato.getId() == alterado.getId() && contato.getCpfCnpj() == alterado.getCpfCnpj()) {
            
        }
        
        try {
            this.conectar();
            conexao.setAutoCommit(false);
            sql.append("UPDATE contato SET ");
            sql.append("telefone = ? ");
            sql.append("email = ? ");
            sql.append("ativo = ? ");
            sql.append(" WHERE contato_id = ? ");
            ps = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contato.getTel());
            ps.setString(2, contato.getEmail());
            ps.setInt(3, contato.getAtivo());
            ps.setInt(4, contato.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            System.out.println(this.getClass().getName() + "   -- ResultSet: " + rs.getFetchSize());
            conexao.commit();
            ps.close();
            rs.close();
            System.out.println(this.getClass().getName() + ": " + Mensagem.OK_ATUALIZAR.getDescricao() + "id: " + contato.getId());
            return contato;
        } catch (SQLException e) {
            System.out.println(this.getClass().getName() + Mensagem.ERRO_ATUALIZAR + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println(this.getClass().getName() + Mensagem.ERRO_ATUALIZAR + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Entidade> consultar(Entidade entidade) {
        PreparedStatement ps = null;
        Contato contato = (Contato) entidade;
        List<Entidade> listaContatos = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT c.* FROM contato c WHERE 1 = 1 ");
        
        try {
            this.conectar();

            if (contato.getId() > 0) {
                sql.append(" AND c.contato_id = " + contato.getId());
            }

            ps = conexao.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Contato con = new Contato();
                con.setId(resultado.getInt("c.contato_id"));
                con.setNome(resultado.getString("c.nome"));
                con.setEmail(resultado.getString("c.email"));
                con.setCpfCnpj(resultado.getString("c.cpf_cnpj"));
                con.setDataInclusao(resultado.getDate("c.data_inclusao"));
                con.setDataAlteracao(resultado.getDate("c.data_alteracao"));
                con.setDataInativacao(resultado.getDate("c.data_inativacao"));
                con.setEmail(resultado.getString("c.email"));
                con.setTel(resultado.getString("c.telefone"));
                System.out.println("Id: " + con.getId() + ", Nome: " + con.getNome() + ", cpf/cnpj: " + con.getCpfCnpj());
                listaContatos.add(con);
            }
            ps.close();
            System.out.println(this.getClass() + ": " + Mensagem.OK_CONSULTAR.getDescricao() + "\n   -- Elementos encontrados = " + listaContatos.size());
        } catch (SQLException e) {
            System.out.println(this.getClass() + ": " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(this.getClass() + ": " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaContatos;
    }

    @Override
    public Boolean excluir(Entidade entidade) {
        PreparedStatement ps = null;
        Contato contato = (Contato) entidade;
        StringBuilder sql = new StringBuilder();
        
        System.out.println(this.getClass() + ": -- Excluir id: " + contato.getId());
        
        try {
            this.conectar();
            this.conexao.setAutoCommit(false);
            sql.append("DELETE FROM contato WHERE id = ?");
            ps = this.conexao.prepareStatement(sql.toString());
            ps.setInt(1,  contato.getId());
            ps.executeUpdate();
            this.conexao.commit();
            ps.close();
            return true;
        } catch(SQLException e) {
            System.out.println(Mensagem.ERRO_EXCLUIR.getDescricao()+ " --- id: " + contato.getId() + e.getMessage());
            return false;
        }
    }
}
