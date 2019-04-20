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
  
    protected Connection sessaoBD;
    protected Conexao conexao;
    
    protected void conectar() 
    {
        conexao = Conexao.getInstance();
        try {
            if(this.sessaoBD == null || this.sessaoBD.isClosed()) {
                this.sessaoBD = Conexao.getSessao();
                System.out.println("[" + this.getClass().getSimpleName() + "] Conexão iniciada");
            }
        } catch (SQLException e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] Erro ao conectar: \n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
