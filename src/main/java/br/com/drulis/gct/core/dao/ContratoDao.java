package br.com.drulis.gct.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Contrato;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ContratoDao extends DaoEntidade {

    @Override
    public Entidade inserir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Inserir");
        PreparedStatement ps = null;
        Contrato contrato = (Contrato) entidade;
        StringBuilder sql = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("INSERT INTO contrato (cliente_id, produto_id, status, ativo, data_inicio, data_fim, usuario_inclusao_id, data_inclusao)");
            sql.append(" VALUES (?,?,?,?,?,?,?, now())");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, contrato.getCliente().getId());
            ps.setInt(2, contrato.getProduto().getId());
            ps.setInt(3, contrato.getStatus());
            ps.setInt(4, contrato.getAtivo());
            ps.setString(5, dateFormat.format(contrato.getDataInicio()));
            ps.setString(6, dateFormat.format(contrato.getDataFim()));
            ps.setInt(7, 1);
//            ps.setTimestamp(6, dataInclusao);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            while(rs.next()) {
                contrato.setId(rs.getInt(1));
            }
            
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_INSERIR.getDescricao() +" id: " + contrato.getId());
            return contrato;
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
        Contrato alterado = (Contrato) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps = null;

        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            
            sql.append("UPDATE contrato SET ");
            sql.append("status = ?, ");
            sql.append("ativo = ?, ");
            sql.append("usuario_alteracao_id = ?, ");
            sql.append("data_alteracao = now() ");
            sql.append("WHERE id = ?");
            
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alterado.getStatus());
            ps.setInt(2, alterado.getAtivo());
            ps.setInt(3, 1);
            ps.setInt(4, alterado.getId());
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
        Contrato contrato = (Contrato) entidade;
        ProdutoDao daoProduto = new ProdutoDao();
        List<Entidade> listaContratos = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT ctt.*, cli.*, p.* FROM contrato ctt ");
        sql.append("LEFT JOIN produto p ON p.id = ctt.produto_id ");
        sql.append("LEFT JOIN cliente cli ON cli.id = ctt.cliente_id ");
        sql.append("WHERE 1 = 1 ");
        
        try {
            this.conectar();

            if (contrato.getId() > 0) {
                sql.append(" AND ctt.id = " + contrato.getId());
            }
            
            if (contrato.getCliente() != null && contrato.getProduto() != null) { 
            	if (contrato.getCliente().getId() > 0) {
	                sql.append(" AND cli.id = " + contrato.getCliente().getId());
	            }
	            
	            if (contrato.getProduto().getId() > 0) {
	                sql.append(" AND p.id = " + contrato.getProduto().getId());
	            }
            }

            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Cliente cli = new Cliente();
                Produto prod = new Produto();
                Contrato c = new Contrato();
                
                prod.setId(resultado.getInt("p.id"));
                /*
                prod.setTitulo(resultado.getString(("p.titulo")));
                prod.setDescricao(resultado.getString(("p.descricao")));
                prod.setCodigo(resultado.getString("p.codigo"));
                prod.setVersao(resultado.getString(("p.versao")));
                */
                cli.setId(resultado.getInt("cli.id"));
                cli.setContato(new Contato(resultado.getInt("cli.contato_id")));
                cli.setSla(resultado.getInt("cli.sla"));
                cli.setStatus(resultado.getInt("cli.status"));
                cli.setAtivo(resultado.getInt("cli.ativo"));
                
                c.setCliente(cli);
                c.setProduto((Produto) daoProduto.consultar(prod).get(0));
                c.setDataInicio(resultado.getDate("ctt.data_inicio"));
                c.setDataFim(resultado.getDate("ctt.data_fim"));
                c.setDataInclusao(resultado.getDate("ctt.usuario_inclusao_id"));
                c.setDataAlteracao(resultado.getDate("ctt.usuario_alteracao_id"));
                c.setDataInativacao(resultado.getDate("ctt.usuario_exclusao_id"));
                c.setDataInclusao(resultado.getDate("ctt.data_inclusao"));
                c.setDataAlteracao(resultado.getDate("ctt.data_alteracao"));
                c.setDataInativacao(resultado.getDate("ctt.data_exclusao"));
                
                listaContratos.add(c);
            }
            
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaContratos;
    }

    @Override
    public Boolean excluir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Excluir id: " + entidade.getId());
        PreparedStatement ps = null;
        Contrato contrato = (Contrato) entidade;
        StringBuilder sql = new StringBuilder();
        
        try {
            this.conectar();
            this.sessaoBD.setAutoCommit(false);
            sql.append("UPDATE contrato SET ativo = 0 WHERE id = ?");
            ps = this.sessaoBD.prepareStatement(sql.toString());
            ps.setInt(1,  contrato.getId());
            ps.executeUpdate();
            this.sessaoBD.commit();
            return true;
        } catch(SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXCLUIR.getDescricao()+ " - Contrato id: " + contrato.getId() + e.getMessage());
            return false;
        }
    }

	@Override
	public List<Entidade> historico(Entidade entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
