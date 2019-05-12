package br.com.drulis.gct.dominio;

import java.util.Date;
import java.util.List;

import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.dominio.classificacao.OcorrenciaTipo;

public class Chamado extends Entidade {
    private static final long serialVersionUID = 1L;
    
    private Usuario usuarioAtribuido;
    private Cliente cliente;
    private Produto produto;
    private List<Atividade> listaAtividades;
    private OcorrenciaTipo tipo;
    private String titulo;
    private String descricao;
    private Date dataAbertura;
    private Date dataFechamento;
    
    public Chamado(Usuario usuarioAtribuido, Cliente cliente, Produto produto, List<Atividade> listaAtividades, String titulo,
            OcorrenciaTipo tipo, String descricao, Date dataAbertura, Date dataFechamento) {
        super();
        this.usuarioAtribuido = usuarioAtribuido;
        this.cliente = cliente;
        this.produto = produto;
        this.listaAtividades = listaAtividades;
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
    }
    
    public Chamado() {}

    public Usuario getUsuarioAtribuido() {
        return usuarioAtribuido;
    }

    public void setUsuarioAtribuido(Usuario usuarioAtribuido) {
        this.usuarioAtribuido = usuarioAtribuido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Atividade> getListaAtividades() {
        return listaAtividades;
    }

    public void setListaAtividades(List<Atividade> listaAtividades) {
        this.listaAtividades = listaAtividades;
    }

    public OcorrenciaTipo getTipo() {
        return tipo;
    }

    public void setTipo(OcorrenciaTipo tipo) {
        this.tipo = tipo;
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

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

}
