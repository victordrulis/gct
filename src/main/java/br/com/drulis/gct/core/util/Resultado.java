package br.com.drulis.gct.core.util;

import java.util.ArrayList;
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
    
    public Resultado() {
        this.entidades = new ArrayList<Entidade>();
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Entidade> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<Entidade> entidades) {
        this.entidades = entidades;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public Entidade getEntidade() {
        return entidade;
    }
    
    public void addEntidade(Entidade entidade) {
        this.entidades.add(entidade);
    }
    
    public void removeEntidade(Entidade entidade) {
        this.entidades.remove(entidade);
    }
}
