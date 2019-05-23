package br.com.drulis.gct.core.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Victor Drulis Oliveira
 * @since 14 de fev de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ConexaoTeste {
    
    /**
     * Conecta ao banco de dados
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException
    {
        String driver    = "com.mysql.cj.jdbc.Driver";
        String url       = "jdbc:mysql://192.168.0.2:3306/banco";
        String user      = "";
        String password  = "";
        url = "jdbc:mysql://192.168.0.2:3306/banco?useTimezone=true&serverTimezone=UTC";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch(SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        return null;
    }

}
