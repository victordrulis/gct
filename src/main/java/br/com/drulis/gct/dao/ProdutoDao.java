package br.com.drulis.gct.dao;

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

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ProdutoDao extends DaoEntidade {

    @Override
    public Entidade inserir(Entidade entidade) throws SQLException {
        System.out.println(this.getClass().getSimpleName() + ": Inserir");
        PreparedStatement ps = null;
        Produto produto = (Produto) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("INSERT INTO produto (status,ativo, usuario_inclusao_id, data_inclusao)");
            sql.append(" VALUES (?,?,?,?,?,?)");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, produto.getStatus());
            ps.setInt(2, produto.getAtivo());
            ps.setInt(3, 1);
            ps.setTimestamp(4, dataInclusao);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            System.out.println(this.getClass().getSimpleName() + "ResultSet: " + rs.getFetchSize());
            while(rs.next()) {
                produto.setId(rs.getInt(1));
            }
            sessaoBD.commit();
            System.out.println(this.getClass().getSimpleName() + Mensagem.OK_INSERIR.getDescricao() +" id: " + produto.getId());
            return produto;
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
        Produto produto = (Produto) entidade;
        List<Entidade> listaProdutos = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT p.*, ps.* FROM produto p LEFT JOIN produto_status ps ON ps.id = p.produto_status_id WHERE 1 = 1 ");
        
        try {
            this.conectar();

            if (produto.getId() > 0) {
                sql.append(" AND p.id = " + produto.getId());
            }

            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Produto prod = new Produto();
                
                prod.setId(resultado.getInt("p.id"));
                prod.setTitulo(resultado.getString("p.titulo"));
                prod.setDescricao(resultado.getString("p.descricao"));
                prod.setStatus(resultado.getInt("p.produto_status_id"));
                prod.setVersao(resultado.getString("p.versao"));
                prod.setDataInclusao(resultado.getDate("p.data_inclusao"));
                prod.setDataAlteracao(resultado.getDate("p.data_alteracao"));
                prod.setDataInativacao(resultado.getDate("p.data_inativacao"));
                
                System.out.println("Id: " + prod.getId());
                
                listaProdutos.add(prod);
            }
            
            System.out.println(this.getClass().getSimpleName() + ": " + Mensagem.OK_CONSULTAR.getDescricao() + "\n   -- Elementos encontrados = " + listaProdutos.size());
        } catch (SQLException e) {
            System.out.println(this.getClass().getSimpleName() + ": " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(this.getClass().getSimpleName() + ": " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaProdutos;
    }

    @Override
    public Boolean excluir(Entidade entidade) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}
