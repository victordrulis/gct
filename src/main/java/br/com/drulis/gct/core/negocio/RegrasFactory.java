/**
 * 
 */
package br.com.drulis.gct.core.negocio;

import br.com.drulis.gct.core.StrategyInterface;

/**
 * Responsável por criar instancias das strategies que validam as regras de negócio.
 *  
 * @author Victor Drulis Oliveira
 * @since 22 de abr de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class RegrasFactory {
    
    public StrategyInterface getValidarExistencia() {
        return new ValidarExistenciaStrategy();
    }
    
    public StrategyInterface getValidarNaoExistencia() {
        return new ValidarNaoExistenciaStrategy();
    }
    
    public StrategyInterface getValidarRangeDeDatas() {
        return new ValidarRangeDeDatas();
    }
    
    public StrategyInterface getValidarFormatoData() {
        return new ValidarFormatoData();
    }
    
    public StrategyInterface getValidarNaoVazio() {
        return new ValidarCampoNaoVazio();
    }
    
    public StrategyInterface getValidarNumeroPositivo() {
        return new ValidarNumeroPositivo();
    }
    
    public StrategyInterface getValidarEmail() {
        return new ValidarEmail();
    }
    
    public StrategyInterface getValidarEstado() {
        return new ValidarEstado();
    }
    
    public StrategyInterface getValidarTelefoneComDDD() {
        return new ValidarTelefoneComDDD();
    }
    
    public StrategyInterface getValidarCpfCnpj() {
        return new ValidarCpfCnpj();
    }
    
    public StrategyInterface getValidarStatus() {
        return new ValidarStatus();
    }
    
    public StrategyInterface getValidarAtivo() {
        return new ValidarAtivo();
    }
    
    public StrategyInterface getValidarInativo() {
        return new ValidarInativo();
    }
    
    public StrategyInterface getValidarNomeUsuario() {
        return new ValidarNomeUsuario();
    }
    
    public StrategyInterface getValidarSenhaUsuario() {
        return new ValidarSenhaUsuario();
    }
    
    public StrategyInterface getValidarPermissoes() {
        return new ValidarPermissoes();
    }
    
    public StrategyInterface getValidarClienteAtivo() {
    	return new ValidarClienteAtivo();
    }
    
    public StrategyInterface getValidarProdutoAtivo() {
    	return new ValidarProdutoAtivo();
    }
}
