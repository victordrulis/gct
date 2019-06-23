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
import java.util.stream.Collectors;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Usuario;

/**
 * @author Victor Drulis Oliveira
 * @since 14 de fev de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ContatoDao extends DaoEntidade {

    @Override
    public Entidade inserir(Entidade entidade) {
        System.out.println("[" + this.getClass().getSimpleName() + "] Inserir");
        PreparedStatement ps = null;
        Contato contato = (Contato) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("INSERT INTO contato (nome, cpf_cnpj, telefone, email, ativo, usuario_inclusao_id, data_inclusao)");
            sql.append(" VALUES (?,?,?,?,?,?,?)");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getCpfCnpj());
            ps.setString(3, contato.getTel());
            ps.setString(4, contato.getEmail());
            ps.setInt(5, 1);
//            ps.setInt(6, contato.getUsuarioInclusao().getId());
            ps.setInt(6, 1);
            ps.setTimestamp(7, dataInclusao);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            while(rs.next()) {
                contato.setId(rs.getInt(1));
            }
            
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_INSERIR.getDescricao() +" id: " + contato.getId());
            return contato;
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
        Contato contato = new Contato();
        Contato alterado = new Contato();
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps = null;

        alterado = (Contato) entidade;

        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("UPDATE contato SET ");
            sql.append("telefone = ?, ");
            sql.append("email = ?, ");
            sql.append("ativo = ?, ");
            sql.append("usuario_alteracao_id = ? ");
            sql.append("WHERE id = ?");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alterado.getTel());
            ps.setString(2, alterado.getEmail());
            ps.setInt(3, alterado.getAtivo());
            ps.setInt(4, 1);
            ps.setInt(5, alterado.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()) {
                alterado.setId(rs.getInt(1));
            }
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_ATUALIZAR.getDescricao() + ", id: " + contato.getId());
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
        Contato contato = (Contato) entidade;
        
        List<Contato> listaContatos = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c.* FROM contato c");

        try {
            this.conectar();
            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Contato con = new Contato();
                con.setId(resultado.getInt("c.id"));
                con.setNome(resultado.getString("c.nome"));
                con.setEmail(resultado.getString("c.email"));
                con.setCpfCnpj(resultado.getString("c.cpf_cnpj"));
                con.setEmail(resultado.getString("c.email"));
                con.setTel(resultado.getString("c.telefone"));
                con.setAtivo(resultado.getInt("c.ativo"));
                con.setDataInclusao(resultado.getDate("c.data_inclusao"));
                con.setDataAlteracao(resultado.getDate("c.data_alteracao"));
                con.setDataInativacao(resultado.getDate("c.data_inativacao"));
                con.setUsuarioInclusao(new Usuario(resultado.getInt("c.usuario_inclusao_id"),null,null, null));
                con.setUsuarioUpdate(new Usuario(resultado.getInt("c.usuario_alteracao_id"),null,null, null));
                con.setUsuarioInativacao(new Usuario(resultado.getInt("c.usuario_inativacao_id"),null,null, null));
                
                listaContatos.add(con);
            }
            
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }

        if(contato.getId() > 0)
			return listaContatos.stream().filter(c -> c.getId() == contato.getId()).collect(Collectors.toList());

        if(contato.getCpfCnpj() != null)
    		return listaContatos.stream().filter(c -> c.getCpfCnpj().equals(contato.getCpfCnpj())).collect(Collectors.toList());
        
        if(contato.getEmail() != null)
        	return listaContatos.stream().filter(c -> c.getEmail().equalsIgnoreCase(contato.getEmail())).collect(Collectors.toList());
        
        return listaContatos.stream().collect(Collectors.toList());
    }

    @Override
    public Boolean excluir(Entidade entidade) {
        PreparedStatement ps = null;
        Contato contato = (Contato) entidade;
        StringBuilder sql = new StringBuilder();
        
        System.out.println("[" + this.getClass().getSimpleName() + "] Excluir id: " + contato.getId());
        
        try {
            this.conectar();
            this.sessaoBD.setAutoCommit(false);
            sql.append("UPDATE contato SET ativo = 0 WHERE id = ?");
            ps = this.sessaoBD.prepareStatement(sql.toString());
            ps.setInt(1,  contato.getId());
            ps.executeUpdate();
            this.sessaoBD.commit();
            return true;
        } catch(SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXCLUIR.getDescricao()+ " --- id: " + contato.getId() + e.getMessage());
            return false;
        }
    }
}
