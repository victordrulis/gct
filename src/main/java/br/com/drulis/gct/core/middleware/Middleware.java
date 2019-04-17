package br.com.drulis.gct.core.middleware;

/**
 * Base para classes que fazem parte da cadeia de responsabilidade 
 * 
 * @author Victor Drulis Oliveira
 * @since 16 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public abstract class Middleware {
    
    /**
     * Contém próximo processamento da cadeia.
     */
    private Middleware next;
    
    /**
     * Constrói a cadeia de objetos de middlware
     *  
     * @param next
     * @return
     */
    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }
    
    /**
     * Subclasses devem implementar este método.
     */
    public abstract boolean check(String email, String password);
    
    /**
     * Executa o processo de verifição se existe mais algum objeto na cadeia. Se for o último, termina.
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
