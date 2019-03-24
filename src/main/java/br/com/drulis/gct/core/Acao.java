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

    EXIBIR("Exibir"),
    CONSULTAR("Consultar"),
    LISTAR("Listar"),
    SALVAR("Salvar"),
    ALTERAR("Alterar"),
    EXCLUIR("Excluir"),
    CANCELAR("Cancelar"), 
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
