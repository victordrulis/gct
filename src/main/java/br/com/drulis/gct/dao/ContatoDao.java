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
            ps.setInt(6, contato.getUsuarioInclusao().getId());
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
            System.out.println(this.getClass() + ": Insert ok.--- id: " + contato.getId());
            return contato;
        } catch (SQLException e) {
            System.out.println(this.getClass() + ": Erro SQL ao inserir -> ");
            e.printStackTrace();
            return null;
        } catch (Exception e1) {
            System.out.println("Erro ao consultar -> ");
            e1.printStackTrace();
            return null;
        }
    }

    @Override
    public Entidade alterar(Entidade entidade) {
        // TODO Colocar lógica para update da tabela
        System.out.println(this.getClass() + ": Alterar ok.");
        return null;
    }

    @Override
    public List<Entidade> consultar(Entidade entidade) {
        PreparedStatement ps = null;
        Contato contato = (Contato) entidade;
        List<Entidade> listaContatos = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT c.* " + "FROM contato c " + "WHERE 1 = 1 ");
        
        try {
            this.conectar();

            if (contato.getId() > 0) {
                sql.append(" OR c.contato_id = " + contato.getId());
            }

            ps = conexao.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Contato con = new Contato();
                con.setId(resultado.getInt("c.contato_id"));
                con.setNome(resultado.getString("c.nome"));
                con.setEmail(resultado.getString("c.email"));
                con.setCpfCnpj(resultado.getString("c.cpf_cnpj"));
                con.setDataAlteracao(resultado.getDate("c.data_alteracao"));
                con.setDataInativacao(resultado.getDate("c.data_inativacao"));
                con.setEmail(resultado.getString("c.email"));
                System.out.println("Id: " + con.getId() + " Nome: " + con.getNome() + " cpf/cnpj: " + con.getCpfCnpj());
                listaContatos.add(con);
            }
            ps.close();
            System.out.println(this.getClass() + ": Consulta realizada. Elementos encontrados = " + listaContatos.size());
        } catch (SQLException e) {
            System.out.println(this.getClass() + ": Erro SQL ao consultar -> ");
            e.printStackTrace();
        } catch (Exception e1) {
            System.out.println(this.getClass() + ": Erro ao consultar -> ");
            e1.printStackTrace();
        }
        return listaContatos;
    }

    @Override
    public Boolean excluir(Entidade entidade) {
        // TODO Colocar lógica para excluir
        System.out.println(this.getClass() + ": Excluir ok.");
        return false;
    }
}
