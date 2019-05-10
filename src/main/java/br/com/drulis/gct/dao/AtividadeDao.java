package br.com.drulis.gct.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.OcorrenciaTipo;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Usuario;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class AtividadeDao extends DaoEntidade {

    private AtividadeDao usuarioAtribuidoDao;
    private ChamadoDao chamadoDao;
    
    @Override
    public Entidade inserir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Inserindo atividade...");
        PreparedStatement ps = null;
        Atividade atividade = (Atividade) entidade;
        StringBuilder sql = new StringBuilder();
        Timestamp dataInclusao = new Timestamp(System.currentTimeMillis());
        
        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            sql.append("INSERT INTO atividade (titulo, descricao, status, ativo, usuario_atribuido_id, usuario_inclusao_id, ");
            sql.append("data_inclusao, chamado_id, usuarioAtribuido_id, tipo, data_inicio, data_final)");
            sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, atividade.getTitulo());
            ps.setString(2, atividade.getDescricao());
            ps.setInt(3, atividade.getStatus());
            ps.setInt(4, atividade.getAtivo());
            ps.setInt(5, atividade.getUsuarioAtribuido().getId());
            ps.setInt(6, atividade.getUsuarioInclusao().getId());
            ps.setTimestamp(7, dataInclusao);
            ps.setInt(8, atividade.getChamado().getId());
            ps.setInt(9, atividade.getUsuarioAtribuido().getId());
            ps.setInt(10, atividade.getTipo().getId());
//            ps.setTimestamp(11, new Timestamp(atividade.getDataAbertura().getTime()));
//            ps.setTimestamp(12, new Timestamp(atividade.getDataFechamento().getTime()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()) {
                atividade.setId(rs.getInt(1));
            }
            sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] " + Mensagem.OK_INSERIR.getDescricao() +" id: " + atividade.getId());
            return atividade;
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
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Alterando atividade...");
        Atividade alterado = (Atividade) entidade;
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps = null;

        try {
            this.conectar();
            sessaoBD.setAutoCommit(false);
            
            sql.append("UPDATE atividade SET ");
            sql.append("usuario_atribuido_id = ?, ");
            sql.append("titulo = ?, ");
            sql.append("descricao = ?, ");
            sql.append("status = ?, ");
            sql.append("ativo = ?, ");
            sql.append("usuario_alteracao_id = ?, ");
            sql.append("data_alteracao = now(), ");
            sql.append("chamado_id = ?, ");
            sql.append("usuarioAtribuido_id = ? ");
            sql.append("WHERE id = ?");
            
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alterado.getUsuarioAtribuido().getId());
            ps.setString(2, alterado.getTitulo());
            ps.setString(3, alterado.getDescricao());
            ps.setInt(5, alterado.getStatus());
            ps.setInt(5, alterado.getAtivo());
            ps.setInt(6, alterado.getUsuarioInclusao().getId());
            ps.setInt(7, alterado.getChamado().getId());
            ps.setInt(8, alterado.getUsuarioAtribuido().getId());
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
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Consultando atividade...");
        PreparedStatement ps = null;
        Atividade atividade = (Atividade) entidade;
        
        List<Entidade> listaAtividades = new ArrayList<Entidade>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT c.*, p.*, cli.*, con.* FROM atividade c ");
        sql.append("LEFT JOIN chamado p ON p.id = c.chamado_id ");
        sql.append("LEFT JOIN usuarioAtribuido cli ON cli.id = c.usuarioAtribuido_id ");
        sql.append("LEFT JOIN contato con ON con.id = cli.contato_id ");
        sql.append("WHERE 1 = 1 ");

        try {
            this.conectar();
            
            if (atividade.getChamado() != null && atividade.getChamado().getId() > 0) {
                sql.append(" AND c.chamado_id = " + atividade.getChamado().getId());
            }
            
            if (atividade.getUsuarioAtribuido() != null && atividade.getUsuarioAtribuido().getId() > 0) {
                sql.append(" AND c.usuarioAtribuido_id = " + atividade.getUsuarioAtribuido().getId());
            }
            
            if (atividade.getUsuarioAtribuido() != null && atividade.getUsuarioAtribuido().getId() > 0) {
                sql.append(" AND c.usuario_atribuido_id = " + atividade.getUsuarioAtribuido().getId());
            }
            
            if (atividade.getUsuarioInclusao() != null && atividade.getUsuarioInclusao().getId() > 0) {
                sql.append(" AND c.usuario_inclusao_id = " + atividade.getUsuarioInclusao().getId());
            }
            
            if (atividade.getId() > 0) {
                sql.append(" AND c.id = " + atividade.getId());
            }

            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Atividade cham = new Atividade();
                Usuario usuarioAtribuido = new Usuario();
                Usuario usuarioInclusao = new Usuario();
                Usuario usuarioAlteracao = new Usuario();
                Usuario usuarioInativacao = new Usuario();
                Contato contato = new Contato();
                Chamado chamado = new Chamado();
                
                usuarioAtribuido.setId(resultado.getInt("c.usuario_atribuido_id"));
                usuarioInclusao.setId(resultado.getInt("c.usuario_inclusao_id"));
                usuarioAlteracao.setId(resultado.getInt("c.usuario_alteracao_id"));
                usuarioInativacao.setId(resultado.getInt("c.usuario_inativacao_id"));

                contato.setId(resultado.getInt("con.id"));
                contato.setNome(resultado.getString("con.nome"));
                contato.setCpfCnpj(resultado.getString("con.cpf_cnpj"));
                contato.setAtivo(resultado.getInt("con.ativo"));
                
                usuarioAtribuido.setContato(contato);
                usuarioAtribuido.setId(resultado.getInt("c.usuarioAtribuido_id"));
                usuarioAtribuido.setAtivo(resultado.getInt("cli.ativo"));
                
                chamado.setId(resultado.getInt("c.chamado_id"));
                chamado.setTitulo(resultado.getString("p.titulo"));
                chamado.setStatus(resultado.getInt("p.chamado_status_id"));
                chamado.setAtivo(resultado.getInt("p.ativo"));
                
                cham.setUsuarioAtribuido(usuarioAtribuido);
                cham.setUsuarioInclusao(usuarioInclusao);
                cham.setUsuarioUpdate(usuarioAlteracao);
                cham.setUsuarioInativacao(usuarioInativacao);
                cham.setUsuarioAtribuido(usuarioAtribuido);
                cham.setChamado(chamado);
                cham.setId(resultado.getInt("c.id"));
                cham.setTitulo(resultado.getString("c.titulo"));
                cham.setStatus(resultado.getInt("c.status"));
                cham.setTipo(OcorrenciaTipo.getMapaTipo().get(resultado.getInt("c.tipo")));
                cham.setDescricao(resultado.getString("c.descricao"));
//                cham.setDataAbertura(resultado.getDate("c.data_inicio"));
//                cham.setDataFechamento(resultado.getDate("c.data_final"));
                cham.setDataInclusao(resultado.getDate("c.data_inclusao"));
                cham.setDataAlteracao(resultado.getDate("c.data_alteracao"));
                cham.setDataInativacao(resultado.getDate("c.data_inativacao"));
                
                listaAtividades.add(cham);
            }
            
            System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        return listaAtividades;
    }

    @Override
    public Boolean excluir(Entidade entidade) throws SQLException {
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] Excluindo id: " + entidade.getId());
        PreparedStatement ps = null;
        Atividade atividade = (Atividade) entidade;
        StringBuilder sql = new StringBuilder();
        
        try {
            this.conectar();
            this.sessaoBD.setAutoCommit(false);
            sql.append("UPDATE atividade SET ativo = 0 WHERE id = ?");
            ps = this.sessaoBD.prepareStatement(sql.toString());
            ps.setInt(1,  atividade.getId());
            ps.executeUpdate();
            this.sessaoBD.commit();
            System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] " + Mensagem.OK_EXCLUIR.getDescricao()+ " - Atividade id: " + atividade.getId());
            return true;
        } catch(SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_EXCLUIR.getDescricao()+ " - Atividade id: " + atividade.getId() + e.getMessage());
            return false;
        }
    }

}
