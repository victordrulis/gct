package br.com.drulis.gct.viewhelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.util.Resultado;

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
