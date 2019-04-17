package br.com.drulis.gct.core.middleware;

/**
 * Tratamento para evitar DDOS
 * 
 * @author Victor Drulis Oliveira
 * @since 16 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class LoginsPorMinuto extends Middleware {

    private int requestPerMinute;
    private int request;
    private long currentTime;

    public LoginsPorMinuto(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }
    
    /**
     * Verifica se houveram mais requisições que o permitido
     */
    @Override
    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;
        
        if (request > requestPerMinute) {
            System.out.println("[INFO] Tentativas de login excedidas: " + email);
            Thread.currentThread().interrupt();
        }
        
        return checkNext(email, password);
    }

}
