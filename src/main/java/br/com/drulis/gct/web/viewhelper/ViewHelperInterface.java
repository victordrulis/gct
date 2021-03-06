package br.com.drulis.gct.web.viewhelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.dominio.DominioInterface;

/**
 * @author Victor Drulis Oliveira
 * @since 2 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public interface ViewHelperInterface {
    public DominioInterface getData(HttpServletRequest request);
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
