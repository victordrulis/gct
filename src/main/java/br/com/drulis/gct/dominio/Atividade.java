/**
 * 
 */
package br.com.drulis.gct.dominio;

import java.util.Date;

import br.com.drulis.gct.core.Entidade;

/**
 * @author Victor Drulis Oliveira
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Atividade extends Entidade {
    private static final long serialVersionUID = 1L;
    
    private Chamado chamado;
    private Usuario usuarioAtribuido;
    private String titulo;
    private String descricao;
    private Date dataInicioExecucao;
    private Date dataFimExecucao;
    private OcorrenciaTipo atividadeTipo;
    
    public Atividade(Chamado chamado, Usuario usuarioAtribuido, String descricao, Date dataInicioExecucao, String titulo,
            Date dataFimExecucao, OcorrenciaTipo atividadeTipo) {
        super();
        this.chamado = chamado;
        this.usuarioAtribuido = usuarioAtribuido;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicioExecucao = dataInicioExecucao;
        this.dataFimExecucao = dataFimExecucao;
        this.atividadeTipo = atividadeTipo;
    }
    
    public Atividade() {}

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }

    public Usuario getUsuarioAtribuido() {
        return usuarioAtribuido;
    }

    public void setUsuarioAtribuido(Usuario usuarioAtribuido) {
        this.usuarioAtribuido = usuarioAtribuido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicioExecucao() {
        return dataInicioExecucao;
    }

    public void setDataInicioExecucao(Date dataInicioExecucao) {
        this.dataInicioExecucao = dataInicioExecucao;
    }

    public Date getDataFimExecucao() {
        return dataFimExecucao;
    }

    public void setDataFimExecucao(Date dataFimExecucao) {
        this.dataFimExecucao = dataFimExecucao;
    }

    public OcorrenciaTipo getAtividadeTipo() {
        return atividadeTipo;
    }

    public void setAtividadeTipo(OcorrenciaTipo atividadeTipo) {
        this.atividadeTipo = atividadeTipo;
    }

}
