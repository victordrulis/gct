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
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.Contato;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Usuario;
import br.com.drulis.gct.dominio.classificacao.OcorrenciaStatus;
import br.com.drulis.gct.dominio.classificacao.OcorrenciaTipo;

/**
 * @author Victor Drulis Oliveira
 * @since 31 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class AtividadeDao extends DaoEntidade {

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
            sql.append("data_inclusao, chamado_id, tipo, data_inicio, data_final)");
            sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, atividade.getTitulo());
            ps.setString(2, atividade.getDescricao());
            ps.setInt(3, atividade.getOcorrenciaStatus().getId());
            ps.setInt(4, atividade.getAtivo());
            ps.setInt(5, atividade.getUsuarioAtribuido().getId());
            ps.setInt(6, atividade.getUsuarioInclusao().getId());
            ps.setTimestamp(7, dataInclusao);
            ps.setInt(8, atividade.getChamado().getId());
            ps.setInt(9, atividade.getTipo().getId());
            ps.setTimestamp(10, new Timestamp(atividade.getDataInicio().getTime()));
            ps.setTimestamp(11, new Timestamp(atividade.getDataFim().getTime()));
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
            sql.append("tipo = ?, ");
            sql.append("chamado_id = ?, ");
            sql.append("ativo = ?, ");
            sql.append("data_alteracao = ?, ");
            sql.append("usuario_alteracao_id = ? ");
            sql.append("WHERE id = ?");
            
            ps = sessaoBD.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alterado.getUsuarioAtribuido().getId());
            ps.setString(2, alterado.getTitulo());
            ps.setString(3, alterado.getDescricao());
            ps.setInt(4, alterado.getOcorrenciaStatus().getId());
            ps.setInt(5, alterado.getTipo().getId());
            ps.setInt(6, alterado.getChamado().getId());
            ps.setInt(7, alterado.getAtivo());
            ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            ps.setInt(9, alterado.getUsuarioUpdate().getId());
            ps.setInt(10, alterado.getId());
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
        UsuarioDao daoUsuario = new UsuarioDao();
        
        List<Atividade> listaAtividades = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT a.*, c.*, u.*, con.* FROM atividade a ");
        sql.append("LEFT JOIN chamado c ON c.id = a.chamado_id ");
        sql.append("LEFT JOIN usuario u ON u.usuario_id = a.usuario_atribuido_id ");
        sql.append("LEFT JOIN contato con ON con.id = u.contato_id ");

        try {
            this.conectar();
            sql.append(" ORDER BY a.data_inclusao DESC");
            ps = sessaoBD.prepareStatement(sql.toString());
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Atividade ativ = new Atividade();
                Usuario usuarioAtribuido = new Usuario();
                Usuario usuarioInclusao = new Usuario();
                Usuario usuarioAlteracao = new Usuario();
                Usuario usuarioInativacao = new Usuario();
                Contato contato = new Contato();
                Chamado chamado = new Chamado();
                
                usuarioAtribuido.setId(resultado.getInt("a.usuario_atribuido_id"));
                usuarioInclusao.setId(resultado.getInt("a.usuario_inclusao_id"));
                usuarioAlteracao.setId(resultado.getInt("a.usuario_alteracao_id"));
                usuarioInativacao.setId(resultado.getInt("a.usuario_inativacao_id"));

                contato.setId(resultado.getInt("con.id"));
                contato.setNome(resultado.getString("con.nome"));
                contato.setCpfCnpj(resultado.getString("con.cpf_cnpj"));
                contato.setEmail(resultado.getString("con.email"));
                contato.setAtivo(resultado.getInt("con.ativo"));
                
                usuarioAtribuido.setContato(contato);
                usuarioAtribuido.setLogin(resultado.getString("u.login"));
                usuarioAtribuido.setAtivo(resultado.getInt("u.ativo"));
                
                chamado.setUsuarioInclusao(new Usuario(resultado.getInt("c.usuario_inclusao_id"), null, null, null));
                chamado.setId(resultado.getInt("c.id"));
                chamado.setTitulo(resultado.getString("c.titulo"));
                chamado.setStatus(resultado.getInt("c.status"));
                chamado.setAtivo(resultado.getInt("c.ativo"));
                
                ativ.setUsuarioAtribuido((Usuario) daoUsuario.consultar(usuarioAtribuido).get(0));
                ativ.setUsuarioInclusao((Usuario) daoUsuario.consultar(usuarioInclusao).get(0));
                ativ.setUsuarioUpdate((Usuario) daoUsuario.consultar(usuarioAlteracao).get(0));
                ativ.setUsuarioInativacao((Usuario) daoUsuario.consultar(usuarioInativacao).get(0));
                ativ.setUsuarioAtribuido((Usuario) daoUsuario.consultar(usuarioAtribuido).get(0));
                ativ.setChamado(chamado);
                ativ.setId(resultado.getInt("a.id"));
                ativ.setTitulo(resultado.getString("a.titulo"));
                ativ.setStatus(resultado.getInt("a.status"));
                ativ.setAtivo(resultado.getInt("a.ativo"));
                ativ.setTipo(OcorrenciaTipo.getMapa().get(resultado.getInt("a.tipo")));
                ativ.setOcorrenciaStatus(OcorrenciaStatus.getMapa().get(resultado.getInt("a.status")));
                ativ.setDescricao(resultado.getString("a.descricao"));
                ativ.setDataInicio(resultado.getDate("c.data_inicio"));
                ativ.setDataFim(resultado.getDate("c.data_final"));
                ativ.setDataInclusao(resultado.getDate("a.data_inclusao"));
                ativ.setDataAlteracao(resultado.getDate("a.data_alteracao"));
                ativ.setDataInativacao(resultado.getDate("a.data_inativacao"));
                
                listaAtividades.add(ativ);
            }
            
            System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] " + Mensagem.OK_CONSULTAR.getDescricao());
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_NAO_ENCONTRADO.getDescricao()+ "\n" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] [ERRO] " + Mensagem.ERRO_EXIBIR.getDescricao() + e.getMessage());
            e.printStackTrace();
        }
        
        if(atividade.getId() > 0)
        	return listaAtividades.stream().filter(a -> a.getId() == atividade.getId()).collect(Collectors.toList());
        
        if(atividade.getChamado() != null && atividade.getChamado().getId() != 0)
        	return listaAtividades.stream().filter(a -> a.getChamado().equals(atividade.getChamado())).collect(Collectors.toList());
        
        if(atividade.getOcorrenciaStatus() != null)
        	return listaAtividades.stream().filter(a -> a.getOcorrenciaStatus().equals(atividade.getOcorrenciaStatus())).collect(Collectors.toList());
        
        if(atividade.getTipo() != null)
        	return listaAtividades.stream().filter(a -> a.getTipo().equals(atividade.getTipo())).collect(Collectors.toList());
        
        return listaAtividades.stream().collect(Collectors.toList());
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
