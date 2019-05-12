package br.com.drulis.gct.dominio;

import br.com.drulis.gct.core.Entidade;

/**
 * @author Victor Drulis Oliveira
 * @since 2 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Usuario extends Entidade {
    private Contato contato;
    private String login;
    private String senha;
    
    public Usuario() {}

    /**
     * @param contato
     * @param login
     * @param senha
     */
    public Usuario(Contato contato, String login, String senha) {
        super();
        this.contato = contato;
        this.login = login;
        this.senha = senha;
    }
    
    public Usuario(Integer id, Contato contato, String login, String senha) {
        super();
        this.id = id;
        this.contato = contato;
        this.login = login;
        this.senha = senha;
    }

    /**
     * @return the contato
     */
    public Contato getContato() {
        return contato;
    }

    /**
     * @param contato the contato to set
     */
    public void setContato(Contato contato) {
        this.contato = contato;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
