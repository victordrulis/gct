/**
 * 
 */
package br.com.drulis.gct.dominio;

import java.util.Date;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.classificacao.OcorrenciaTipo;

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
    private Date dataInicio;
    private Date dataFim;
    private OcorrenciaTipo tipo;
    
    public Atividade(Chamado chamado, Usuario usuarioAtribuido, String descricao, Date dataInicioExecucao, String titulo,
            Date dataFimExecucao, OcorrenciaTipo tipo, Integer ativo) {
        super();
        this.chamado = chamado;
        this.usuarioAtribuido = usuarioAtribuido;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicioExecucao;
        this.dataFim = dataFimExecucao;
        this.tipo = tipo;
        this.ativo = ativo;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicioExecucao) {
        this.dataInicio = dataInicioExecucao;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFimExecucao) {
        this.dataFim = dataFimExecucao;
    }

    public OcorrenciaTipo getTipo() {
        return tipo;
    }

    public void setTipo(OcorrenciaTipo tipo) {
        this.tipo = tipo;
    }

}
