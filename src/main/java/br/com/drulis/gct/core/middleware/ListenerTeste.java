package br.com.drulis.gct.core.middleware;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ListenerTeste
 *
 */
@WebListener
public class ListenerTeste implements ServletRequestListener, ServletContextListener, ServletContextAttributeListener, ServletRequestAttributeListener {

    /**
     * Default constructor. 
     */
    public ListenerTeste() {
        System.out.println("[" + this.getClass().getSimpleName() + "] : INICIADO!");
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent scab)  { 
         System.out.println("[" + this.getClass().getSimpleName() + "] --->>> attributeAdded: " + scab.getValue());
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
        System.out.println("[" + this.getClass().getSimpleName() + "] --->>> Request: requestDestroyed: " + sre.getServletContext());
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent scab)  { 
        System.out.println("[" + this.getClass().getSimpleName() + "] --->>> attributeRemoved: " + scab.getValue());
    }

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent srae)  { 
        System.out.println("[" + this.getClass().getSimpleName() + "] --->>> Request: attributeRemoved: " + srae.getName());
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
        System.out.println("[" + this.getClass().getSimpleName() + "] --->>> Request: requestInitialized: " + sre.getServletContext());
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
        System.out.println("[" + this.getClass().getSimpleName() + "] --->>> contextDestroyed: " + sce.getServletContext());
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent srae)  { 
        System.out.println("[" + this.getClass().getSimpleName() + "] --->>> Request: attributeAdded: " + srae.getName());
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent srae)  { 
        System.out.println("[" + this.getClass().getSimpleName() + "] --->>> Request: attributeReplaced: " + srae.getName());
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent scab)  { 
        System.out.println("[" + this.getClass().getSimpleName() + "] --->>> attributeReplaced: " + scab.getValue());
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        System.out.println("[" + this.getClass().getSimpleName() + "] --->>> contextInitialized: " + sce.getServletContext());
    }
	
}
