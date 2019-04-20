package br.com.drulis.gct.core.middleware;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Faz o controle de acesso dada as permissões dos usuários
 * 
 * @author Victor Drulis Oliveira
 * @contact victordrulis@gmail.com
 * 
 */
//@WebFilter({"/*"})
public class LogginFilter implements Filter {

    private static final Set<String> PATHS_PERMITIDOS = Collections.unmodifiableSet(
                new HashSet<>(Arrays.asList("", "/login", "/logout", "/registrar"))
            );

    /**
     * Construtor padrão 
     */
    public LogginFilter() {}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    System.out.println("[" + this.getClass().getSimpleName() + "]  --------------------------------");

	    HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession sessao = req.getSession(false);
        String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", ""); 

        boolean isLogado = (sessao != null && sessao.getAttribute("idUsuario") != null);
        boolean isPathPermitido = PATHS_PERMITIDOS.contains(path);

        if (isLogado || isPathPermitido) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/login");
        }
	}

	/**
	 * 
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Criar uma classe que implemente FilterConfig e acrescentar as classes 
	    // de consulta de usuários
	}

}
