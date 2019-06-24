package br.com.drulis.gct.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.dominio.classificacao.ProdutoStatus;
import br.com.drulis.gct.dominio.classificacao.ProdutoTipo;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ProdutoDao extends DaoEntidade {

    @Override
    public Entidade inserir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Inserir");
        PreparedStatement ps = null;
        Produto produto = (Produto) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("INSERT INTO produto (titulo, descricao, produto_status_id, ativo, usuario_inclusao_id, data_inclusao, codigo, tipo, versao)");
            sql.append(" VALUES (?,?,?,?,?,?,?,?,?)");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, produto.getTitulo());
            ps.setString(2, produto.getDescricao());
            ps.setInt(3, produto.getProdutoStatus().getId());
            ps.setInt(4, produto.getAtivo());
            ps.setInt(5, 1);
            ps.setTimestamp(6, dataInclusao);
            ps.setString(7, produto.getCodigo());
            ps.setInt(8, produto.getProdutoTipo().getId());
            ps.setString(9, produto.getVersao());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()) {
                produto.setId(rs.getInt(1));
            }
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_INSERIR.getDescricao() +" id: " + produto.getId());
            return produto;
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
        PreparedStatement ps = null;
        Produto produto = (Produto) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataAlteracao = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + this.getClass().getSimpleName() + "] Alterando " + produto.getClass().getSimpleName());
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("UPDATE produto SET ");
            sql.append("titulo = ?, ");
            sql.append("descricao = ?, ");
            sql.append("codigo = ?, ");
            sql.append("versao = ?, ");
            sql.append("tipo = ?, ");
            sql.append("produto_status_id = ?, ");
            sql.append("ativo = ?, ");
            sql.append("usuario_alteracao_id = ?, ");
            sql.append("data_alteracao = ? ");
            sql.append("WHERE id = ?");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, produto.getTitulo());
            ps.setString(2, produto.getDescricao());
            ps.setString(3, produto.getCodigo());
            ps.setString(4, produto.getVersao());
            ps.setInt(5, produto.getProdutoTipo().getId());
            ps.setInt(6, produto.getProdutoStatus().getId());
            ps.setInt(7, produto.getAtivo());
            ps.setInt(8, 1);
            ps.setTimestamp(9, dataAlteracao);
            ps.setInt(10, produto.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            while(rs.next()) {
                produto.setId(rs.getInt(1));
            }
            
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_ATUALIZAR.getDescricao() +" Produto id: " + produto.getId());
        } catch(SQLException e) {
        	System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_ATUALIZAR.getDescricao() + ", mensagem: " + e.getMessage() );
        	e.printStackTrace();
        } catch(Exception e) {
        	System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_ATUALIZAR.getDescricao() + ", mensagem: " + e.getMessage() );
        	e.printStackTrace();
        }
		return produto;
            
    }

    @Override
    public List<Entidade> consultar(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Consultar");
        PreparedStatement ps = null;
        Produto produto = (Produto) entidade;
        List<Entidade> listaProdutos = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT p.* FROM produto p ");
        sql.append("WHERE 1 = 1 ");
        
        try {
            this.conectar();

            if (produto.getId() > 0) {
                sql.append(" AND p.id = " + produto.getId());
            }

            sql.append(" ORDER BY p.ativo, p.titulo");
            
            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Produto prod = new Produto();
                
                prod.setId(resultado.getInt("p.id"));
                prod.setTitulo(resultado.getString("p.titulo"));
                prod.setCodigo(resultado.getString("p.codigo"));
                prod.setDescricao(resultado.getString("p.descricao"));
                prod.setProdutoStatus(ProdutoStatus.getMapa().get(resultado.getInt("p.produto_status_id")));
                prod.setProdutoTipo(ProdutoTipo.getMapa().get(resultado.getInt("p.tipo")));
                prod.setVersao(resultado.getString("p.versao"));
                prod.setAtivo(resultado.getInt("p.ativo"));
                prod.setDataInclusao(resultado.getDate("p.data_inclusao"));
                prod.setDataAlteracao(resultado.getDate("p.data_alteracao"));
                prod.setDataInativacao(resultado.getDate("p.data_inativacao"));
                
                listaProdutos.add(prod);
            }
            
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaProdutos;
    }

    @Override
    public Boolean excluir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] Excluir");
        return null;
    }

	@Override
	public List<Entidade> historico(Entidade entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
