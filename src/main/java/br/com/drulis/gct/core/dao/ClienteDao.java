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
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Contrato;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.dominio.Usuario;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ClienteDao extends DaoEntidade {

    @Override
    public Entidade inserir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Inserir");
        PreparedStatement ps = null;
        Cliente cliente = (Cliente) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("INSERT INTO cliente (contato_id, sla, status, ativo, usuario_inclusao_id, data_inclusao)");
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
            while(rs.next()) {
                cliente.setId(rs.getInt(1));
            }
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_INSERIR.getDescricao() +" id: " + cliente.getId());
            return cliente;
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
        Cliente alterado = (Cliente) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps = null;

        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            
            sql.append("UPDATE cliente SET ");
            sql.append("contato_id = ?, ");
            sql.append("sla = ?, ");
            sql.append("status = ?, ");
            sql.append("ativo = ?, ");
            sql.append("usuario_alteracao_id = ?, ");
            sql.append("data_alteracao = now() ");
            sql.append("WHERE id = ?");
            
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alterado.getContato().getId());
            ps.setInt(2, alterado.getSla());
            ps.setInt(3, alterado.getStatus());
            ps.setInt(4, alterado.getAtivo());
            ps.setInt(5, 1);
            ps.setInt(6, alterado.getId());
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

    @SuppressWarnings("unchecked")
	@Override
    public List<Entidade> consultar(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Consultar");
        PreparedStatement ps = null;
        Cliente cliente = (Cliente) entidade;
        ContratoDao daoContrato = new ContratoDao();
        List<Cliente> listaClientes = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT cli.*, c.* FROM cliente cli ");
        sql.append("LEFT JOIN contato c ON c.id = cli.contato_id ");
        
        try {
            this.conectar();
            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Cliente cli = new Cliente();
                Contato con = new Contato();
                
                con.setId(resultado.getInt("c.id"));
                con.setNome(resultado.getString(("c.nome")));
                con.setCpfCnpj(resultado.getString(("c.cpf_cnpj")));
                con.setTel(resultado.getString(("c.telefone")));
                con.setEmail(resultado.getString(("c.email")));
                con.setAtivo(resultado.getInt("c.ativo"));
                
                cli.setContato(con);
                cli.setId(resultado.getInt("cli.id"));
                cli.setSla(resultado.getInt("cli.sla"));
                cli.setAtivo(resultado.getInt("cli.ativo"));
                cli.setUsuarioInclusao(new Usuario(resultado.getInt("cli.usuario_inclusao_id"), null, null, null));
                cli.setUsuarioUpdate(new Usuario(resultado.getInt("cli.usuario_alteracao_id"), null, null, null));
                cli.setUsuarioInativacao(new Usuario(resultado.getInt("cli.usuario_inativacao_id"), null, null, null));
                cli.setDataInclusao(resultado.getDate("cli.data_inclusao"));
                cli.setDataAlteracao(resultado.getDate("cli.data_alteracao"));
                cli.setDataInativacao(resultado.getDate("cli.data_inativacao"));
                
                Contrato contrato = new Contrato(cli, new Produto(), null, null, null, null);
                
                cli.setListaContrato(new ArrayList<Contrato>());
                cli.getListaContrato().addAll((List<Contrato>) (Object) daoContrato.consultar(contrato));
                
                listaClientes.add(cli);
            }
            
            if(cliente.getId() > 0)
            	return listaClientes.stream().filter(c -> c.getId() == cliente.getId()).collect(Collectors.toList());
            
            if(cliente.getAtivo() == 1)
            	return listaClientes.stream().filter(c -> c.getAtivo() == cliente.getAtivo()).collect(Collectors.toList());
            
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaClientes.stream().collect(Collectors.toList());
    }

    @Override
    public Boolean excluir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Excluir id: " + entidade.getId());
        PreparedStatement ps = null;
        Cliente cliente = (Cliente) entidade;
        StringBuilder sql = new StringBuilder();
        
        try {
            this.conectar();
            this.sessaoBD.setAutoCommit(false);
            sql.append("UPDATE cliente SET ativo = 0 WHERE id = ?");
            ps = this.sessaoBD.prepareStatement(sql.toString());
            ps.setInt(1,  cliente.getId());
            ps.executeUpdate();
            this.sessaoBD.commit();
            return true;
        } catch(SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXCLUIR.getDescricao()+ " - Cliente id: " + cliente.getId() + e.getMessage());
            return false;
        }
    }

	@Override
	public List<Entidade> historico(Entidade entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
