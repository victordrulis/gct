package br.com.drulis.gct.core.dao;

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
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.dominio.Usuario;
import br.com.drulis.gct.dominio.classificacao.OcorrenciaStatus;
import br.com.drulis.gct.dominio.classificacao.OcorrenciaTipo;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ChamadoDao extends DaoEntidade {

    @Override
    public Entidade inserir(Entidade entidade) throws SQLException {
        PreparedStatement ps = null;
        Chamado chamado = (Chamado) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Inserindo chamado...");
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("INSERT INTO chamado (titulo, descricao, status, ativo, usuario_atribuido_id, usuario_inclusao_id, ");
            sql.append("data_inclusao, produto_id, cliente_id, tipo, data_inicio, data_final)");
            sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, chamado.getTitulo());
            ps.setString(2, chamado.getDescricao());
            ps.setInt(3, chamado.getOcorrenciaStatus().getId());
            ps.setInt(4, chamado.getAtivo());
            ps.setInt(5, chamado.getUsuarioAtribuido().getId());
//            ps.setInt(6, chamado.getUsuarioInclusao().getId());
            ps.setInt(6, 1);
            ps.setTimestamp(7, dataInclusao);
            ps.setInt(8, chamado.getProduto().getId());
            ps.setInt(9, chamado.getCliente().getId());
            ps.setInt(10, chamado.getTipo().getId());
            ps.setTimestamp(11, new Timestamp(chamado.getDataAbertura().getTime()));
            ps.setTimestamp(12, new Timestamp(chamado.getDataFechamento().getTime()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()) {
                chamado.setId(rs.getInt(1));
            }
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] " + Mensagem.OK_INSERIR.getDescricao() +" id: " + chamado.getId());
            return chamado;
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_INSERIR.getDescricao() + "\n: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_INSERIR.getDescricao() + "\n: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Entidade alterar(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Alterando chamado...");
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
            ps.setInt(6, alterado.getUsuarioInclusao().getId());
            ps.setInt(7, alterado.getProduto().getId());
            ps.setInt(8, alterado.getCliente().getId());
            ps.setInt(9, alterado.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            while(rs.next()) {
                alterado.setId(rs.getInt(1));
            }
            
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] " + Mensagem.OK_ATUALIZAR.getDescricao() + ", id: " + alterado.getId());
            return alterado;
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_ATUALIZAR + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_ATUALIZAR + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Entidade> consultar(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Consultando chamado...");
        PreparedStatement ps = null;
        Chamado chamado = (Chamado) entidade;
        UsuarioDao daoUsuario = new UsuarioDao();
        
        List<Entidade> listaChamados = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT c.*, p.*, cli.*, con.* FROM chamado c ");
        sql.append("LEFT JOIN produto p ON p.id = c.produto_id ");
        sql.append("LEFT JOIN cliente cli ON cli.id = c.cliente_id ");
        sql.append("LEFT JOIN contato con ON con.id = cli.contato_id ");
        sql.append("WHERE 1 = 1 ");

        try {
            this.conectar();
            
            if (chamado.getProduto() != null && chamado.getProduto().getId() > 0) {
                sql.append(" AND c.produto_id = " + chamado.getProduto().getId());
            }
            
            if (chamado.getCliente() != null && chamado.getCliente().getId() > 0) {
                sql.append(" AND c.cliente_id = " + chamado.getCliente().getId());
            }
            
            if (chamado.getUsuarioAtribuido() != null && chamado.getUsuarioAtribuido().getId() > 0) {
                sql.append(" AND c.usuario_atribuido_id = " + chamado.getUsuarioAtribuido().getId());
            }
            
            if (chamado.getUsuarioInclusao() != null && chamado.getUsuarioInclusao().getId() > 0) {
                sql.append(" AND c.usuario_inclusao_id = " + chamado.getUsuarioInclusao().getId());
            }
            
            if (chamado.getId() > 0) {
                sql.append(" AND c.id = " + chamado.getId());
            }

            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Chamado cham = new Chamado();
                Usuario usuarioAtribuido = new Usuario();
                Usuario usuarioInclusao = new Usuario();
                Usuario usuarioAlteracao = new Usuario();
                Usuario usuarioInativacao = new Usuario();
                Contato contato = new Contato();
                Cliente cliente = new Cliente();
                Produto produto = new Produto();
                
                usuarioAtribuido.setId(resultado.getInt("c.usuario_atribuido_id"));
                usuarioInclusao.setId(resultado.getInt("c.usuario_inclusao_id"));
                usuarioAlteracao.setId(resultado.getInt("c.usuario_alteracao_id"));
                usuarioInativacao.setId(resultado.getInt("c.usuario_inativacao_id"));

                contato.setId(resultado.getInt("con.id"));
                contato.setNome(resultado.getString("con.nome"));
                contato.setCpfCnpj(resultado.getString("con.cpf_cnpj"));
                contato.setEmail(resultado.getString("con.email"));
                contato.setTel(resultado.getString("con.telefone"));
                contato.setAtivo(resultado.getInt("con.ativo"));
                
                cliente.setContato(contato);
                cliente.setId(resultado.getInt("c.cliente_id"));
                cliente.setSla(resultado.getInt("cli.sla"));
                cliente.setAtivo(resultado.getInt("cli.ativo"));
                
                produto.setId(resultado.getInt("c.produto_id"));
                produto.setTitulo(resultado.getString("p.titulo"));
                produto.setStatus(resultado.getInt("p.produto_status_id"));
                produto.setAtivo(resultado.getInt("p.ativo"));
                
                cham.setUsuarioAtribuido((Usuario) daoUsuario.consultar(usuarioAtribuido).get(0));
                cham.setUsuarioInclusao((Usuario) daoUsuario.consultar(usuarioInclusao).get(0));
                cham.setUsuarioUpdate((Usuario) daoUsuario.consultar(usuarioAlteracao).get(0));
                cham.setUsuarioInativacao((Usuario) daoUsuario.consultar(usuarioInativacao).get(0));
                cham.setCliente(cliente);
                cham.setProduto(produto);
                cham.setId(resultado.getInt("c.id"));
                cham.setTitulo(resultado.getString("c.titulo"));
                cham.setStatus(resultado.getInt("c.status"));
                cham.setAtivo(resultado.getInt("c.ativo"));
                cham.setTipo(OcorrenciaTipo.getMapa().get(resultado.getInt("c.tipo")));
                cham.setOcorrenciaStatus(OcorrenciaStatus.getMapa().get(resultado.getInt("c.status")));
                cham.setDescricao(resultado.getString("c.descricao"));
                cham.setDataAbertura(resultado.getDate("c.data_inicio"));
                cham.setDataFechamento(resultado.getDate("c.data_final"));
                cham.setDataInclusao(resultado.getDate("c.data_inclusao"));
                cham.setDataAlteracao(resultado.getDate("c.data_alteracao"));
                cham.setDataInativacao(resultado.getDate("c.data_inativacao"));
                
                listaChamados.add(cham);
            }
            
            System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaChamados;
    }

    @Override
    public Boolean excluir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Excluindo id: " + entidade.getId());
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
            System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] " + Mensagem.OK_EXCLUIR.getDescricao()+ " - Chamado id: " + chamado.getId());
            return true;
        } catch(SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_EXCLUIR.getDescricao()+ " - Chamado id: " + chamado.getId() + e.getMessage());
            return false;
        }
    }

}
