/**
 * 
 */
package br.com.drulis.gct.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.drulis.gct.infra.Conexao;

/**
 * @author Victor Drulis Oliveira
 * @since 14 de fev de 2019
 * @contact victordrulis@gmail.com
 *
 */
public abstract class DaoEntidade implements DaoInterface {
  
    protected Connection conexao;
    
    /**
     * 
     */
    protected void conectar() 
    {
        try {

            if(this.conexao == null || this.conexao.isClosed()) {
                this.conexao = Conexao.getConnection();
                System.out.println(this.getClass().getSimpleName() + ": Conexão iniciada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    protected void encerrar() 
    {
        try {
            if(this.conexao != null) {
                this.conexao.close();
                System.out.println(this.getClass().getSimpleName() + ": Conexão encerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
