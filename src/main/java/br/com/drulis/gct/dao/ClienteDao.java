package br.com.drulis.gct.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Mensagem;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ClienteDao extends DaoEntidade {

    @Override
    public Entidade inserir(Entidade entidade) throws SQLException {
        System.out.println(this.getClass().getSimpleName() + ": Inserir");
        PreparedStatement ps = null;
        Cliente cliente = (Cliente) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("INSERT INTO cliente (contato_id, sla, status,ativo, usuario_inclusao_id, data_inclusao)");
            sql.append(" VALUES (?,?,?,?,?,?)");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cliente.getContato().getId());
            ps.setInt(2, cliente.getSla());
            ps.setInt(3, cliente.getStatus());
            ps.setInt(4, cliente.getAtivo());
            ps.setInt(5, 1);
            ps.setTimestamp(6, dataInclusao);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            System.out.println(this.getClass().getSimpleName() + "ResultSet: " + rs.getFetchSize());
            while(rs.next()) {
                cliente.setId(rs.getInt(1));
            }
            sessaoBD.commit();
            System.out.println(this.getClass().getSimpleName() + Mensagem.OK_INSERIR.getDescricao() +" id: " + cliente.getId());
            return cliente;
        } catch (SQLException e) {
            System.out.println(this.getClass().getSimpleName() + Mensagem.ERRO_INSERIR.getDescricao() + "\n: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println(this.getClass().getSimpleName() + Mensagem.ERRO_INSERIR.getDescricao() + "\n: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Entidade alterar(Entidade entidade) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Entidade> consultar(Entidade entidade) throws SQLException {
        PreparedStatement ps = null;
        Cliente cliente = (Cliente) entidade;
        List<Entidade> listaClientes = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT cli.*, c.* FROM cliente cli LEFT JOIN contato c ON c.contato_id = cli.contato_id WHERE 1 = 1 ");
        
        try {
            this.conectar();

            if (cliente.getId() > 0) {
                sql.append(" AND cli.cliente_id = " + cliente.getId());
            }

            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Cliente cli = new Cliente();
                Contato con = new Contato();
                
                con.setId(resultado.getInt("c.contato_id"));
                con.setNome(resultado.getString(("c.nome")));
                con.setEmail(resultado.getString(("c.Email")));
                
                cli.setContato(con);
                cli.setId(resultado.getInt("cli.cliente_id"));
                cli.setSla(resultado.getInt("cli.sla"));
                cli.setDataInclusao(resultado.getDate("cli.data_inclusao"));
                cli.setDataAlteracao(resultado.getDate("cli.data_alteracao"));
                cli.setDataInativacao(resultado.getDate("cli.data_inativacao"));
                
                System.out.println("Id: " + cli.getId() + ", Nome: " + cli.getContato().getNome() + ", SLA: " + cli.getSla());
                
                listaClientes.add(cli);
            }
            
            System.out.println(this.getClass().getSimpleName() + ": " + Mensagem.OK_CONSULTAR.getDescricao() + "\n   -- Elementos encontrados = " + listaClientes.size());
        } catch (SQLException e) {
            System.out.println(this.getClass().getSimpleName() + ": " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(this.getClass().getSimpleName() + ": " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaClientes;
    }

    @Override
    public Boolean excluir(Entidade entidade) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
