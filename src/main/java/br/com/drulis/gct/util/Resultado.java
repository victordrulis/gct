package br.com.drulis.gct.util;

import java.util.List;

import br.com.drulis.gct.core.Entidade;

/**
 * @author Victor Drulis Oliveira
 * @since 14 de fev de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Resultado {

    public String mensagem;
    public List<Entidade> entidades;
    public Entidade entidade;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Entidade> getEntidades() {
        System.out.println(this.getClass().getSimpleName() + ": getEntidades");
        return entidades;
    }

    public void setEntidades(List<Entidade> entidades) {
        System.out.println(this.getClass().getSimpleName() + ": setEntidades " + entidades.size());
        this.entidades = entidades;
    }

    public void setEntidade(Entidade entidade) {
        System.out.println(this.getClass().getSimpleName() + ": setEntidade " + entidade.getClass().getSimpleName());
        this.entidade = entidade;
    }

    public Entidade getEntidade() {
        System.out.println(this.getClass().getSimpleName() + ": getEntidade");
        return entidade;
    }
}
