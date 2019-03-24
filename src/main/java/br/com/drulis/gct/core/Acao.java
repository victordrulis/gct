package br.com.drulis.gct.core;

/**
 * Ações padrão
 * 
 * @author Victor Drulis Oliveira
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public enum Acao {

    EXIBIR("exibir"),
    CONSULTAR("consultar"),
    LISTAR("listar"),
    SALVAR("salvar"),
    ALTERAR("alterar"),
    EXCLUIR("excluir"),
    CANCELAR("cancelar"), 
    ;
    
    private String acao;

    /**
     * @param acao
     */
    private Acao(String acao) {
        this.acao = acao;
    }

    /**
     * @return the acao
     */
    public String getAcao() {
        return acao;
    }
    
}
