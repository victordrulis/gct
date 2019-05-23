package br.com.drulis.gct.web.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.dominio.Cliente;
import br.com.drulis.gct.dominio.Contrato;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.web.command.ConsultarCommand;

/**
 * 
 * @author Victor Drulis
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ContratoViewHelper implements ViewHelperInterface {

    @Override
    public DominioInterface getData(HttpServletRequest request) {
        ConsultarCommand consultar = new ConsultarCommand();
        Contrato contrato = new Contrato();
        Cliente cliente = new Cliente();
        Produto produto = new Produto();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        String acao = request.getParameter("acao");
        String produtoId = request.getParameter("produtoId");
        String clienteId = request.getParameter("clienteId");
        String status = request.getParameter("status");
        String contratoDataInicio = request.getParameter("dataInicio");
        String contratoDataFim = request.getParameter("dataFim");
        String ativo = request.getParameter("ativo");
        
        System.out.println("[" + this.getClass().getSimpleName() + "] --getData, ACAO: " + acao + ", URI: " + request.getRequestURI());

        if(acao == null) {
            acao = Acao.LISTAR.getAcao();
        }
        
        if(!acao.equals(Acao.SALVAR.getAcao()) && !acao.equals(Acao.NOVO.getAcao())) {
            String id = request.getParameter("id");
            
            if(id != null && id != "")
                contrato.setId(Integer.parseInt(id));
        }
        
        if(acao.equals(Acao.SALVAR.getAcao()) || acao.equals(Acao.ALTERAR.getAcao()) || acao.equals(Acao.EDITAR.getAcao()) && request.getMethod().equals("POST")) {
            Resultado resultadoCliente = new Resultado();
            Resultado resultadoProduto = new Resultado();
            
            produto.setId(Integer.parseInt(produtoId));
            cliente.setId(Integer.parseInt(clienteId));
            
            resultadoCliente = consultar.execute(cliente);
            resultadoProduto = consultar.execute(produto);
            
            try {
                contrato.setProduto((Produto) resultadoProduto.getEntidades().get(0));
                contrato.setCliente((Cliente) resultadoCliente.getEntidades().get(0));
                contrato.setStatus((status != null) ? Integer.parseUnsignedInt(status) : 0);
                contrato.setDataInicio((contratoDataInicio != null) ? dateFormat.parse(contratoDataInicio) : new Date());
                contrato.setDataFim((contratoDataFim != null) ? dateFormat.parse(contratoDataFim) : new Date());
                contrato.setStatus(Integer.parseUnsignedInt(status));

            } catch(ParseException e) {
                System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_CONVERTER_DADOS.getDescricao() + "; \n" + e.getMessage());
                e.printStackTrace();
            }
            if(ativo != null || ativo != "0" || ativo != "off")
                contrato.setAtivo(1);
        }
        
        if(!acao.equals(Acao.EXCLUIR.getAcao()) && request.getMethod().equals("GET")) {
            try {
                produto.setId((produtoId != null) ? Integer.parseInt(produtoId) : 0);
                
                contrato.setProduto(produto);
                
                contrato.setStatus((status != null) ? Integer.parseUnsignedInt(status) : 0);
                contrato.setDataInicio((contratoDataInicio != null) ? dateFormat.parse(contratoDataInicio) : new Date());
                contrato.setDataFim((contratoDataFim != null) ? dateFormat.parse(contratoDataFim) : new Date());
            } catch (ParseException e) {
                System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_CONVERTER_DADOS.getDescricao() + "; \n" + e.getMessage());
                e.printStackTrace();
            }
            
            if(ativo != null || ativo != "0")
                contrato.setAtivo(1);
        }
        
        return contrato;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConsultarCommand consultar = new ConsultarCommand();
        Resultado resProduto = new Resultado();
        Resultado resCliente = new Resultado();
        String mensagem = null;
        
        try {
            resCliente = consultar.execute(new Cliente());
            resProduto = consultar.execute(new Produto());
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_CONVERTER_DADOS + ": " + e.getMessage());
            e.printStackTrace();
        }
        
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        
        System.out.println("[" + this.getClass().getSimpleName() + "] setView: Acao = " + acao + ", URI: " + uri);
        
        if(resultado != null) {
            mensagem = resultado.getMensagem();
        }
        
        if(acao != null && acao.equals(Acao.NOVO.getAcao())) {
            request.setAttribute("resultadoCliente", resCliente.getEntidades());
            request.setAttribute("resultadoProduto", resProduto.getEntidades());
            request.getRequestDispatcher("/jsp/contrato/form.jsp").forward(request, response);
        } else {
            switch(resultado.getEntidades().size()) {
            case 0:
                request.setAttribute("resultadoCliente", resCliente.getEntidades());
                request.setAttribute("resultadoProduto", resProduto.getEntidades());
                request.getRequestDispatcher("/jsp/contrato/form.jsp").forward(request, response);
                break;
            
            case 1:
                request.setAttribute("resultado", resultado.getEntidades().get(0));
                
                if(acao != null && acao.equals(Acao.EDITAR.getAcao())) {
                    request.getRequestDispatcher("/jsp/contrato/edit.jsp").forward(request, response);
                    break;
                }
                
                if(acao != null && acao.equals(Acao.EXIBIR.getAcao())) {
                    request.getRequestDispatcher("/jsp/contrato/show.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("resultado", resultado.getEntidades());
                request.getRequestDispatcher("/jsp/contrato/index.jsp").forward(request, response);
                break;
            
            default:
                if(mensagem != null && !mensagem.equals("")) {
                    request.setAttribute("mensagem", mensagem);
                    request.getRequestDispatcher("mensagem.jsp").forward(request, response);
                    break;
                }
                
                if(resultado.getEntidades() == null || resultado.getEntidades().size() < 1) {
                    request.setAttribute("mensagem", Mensagem.ERRO_NAO_ENCONTRADO.getDescricao());
                    request.getRequestDispatcher("mensagem.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("resultado", resultado.getEntidades());
                request.getRequestDispatcher("/jsp/contrato/index.jsp").forward(request, response);
                break;
            }
        }
        
    }

}
