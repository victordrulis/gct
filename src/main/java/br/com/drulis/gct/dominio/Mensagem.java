package br.com.drulis.gct.dominio;

/**
 * Mensagens padrão do sistema.
 * 
 * @author Victor Drulis Oliveira
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public enum Mensagem {
    
    ERRO_NAO_ENCONTRADO("Resultado não encontrado."),
    OK_CONSULTAR("Resultado não encontrado."),
    OK_INSERIR("Resultado não encontrado."),
    OK_ATUALIZAR("Resultado não encontrado."),
    OK_EXCLUIR("Resultado não encontrado."),
    ERRO_EXIBIR("Erro ao exibir registro."),
    ERRO_INSERIR("Erro ao inserir registro."),
    ERRO_ATUALIZAR("Erro ao atualizar registro."),
    ERRO_EXCLUIR("Erro ao excluir registro."),
    ERRO_CONVERTER_DADOS("Erro ao converter dados."),
    ;
    
    private String descricao;

    /**
     * @param descricao
     */
    private Mensagem(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }
    
}
