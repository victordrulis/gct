package br.com.drulis.gct.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.util.Resultado;

/**
 * 
 * @author Victor Drulis
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class ProdutoViewHelper implements ViewHelperInterface {

    @Override
    public DominioInterface getData(HttpServletRequest request) {
        String acao = request.getParameter("acao");
        Produto produto = new Produto();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String produtoId = request.getParameter("listaProdutoId");
        String titulo = request.getParameter("titulo");
        String statusProduto = request.getParameter("statusProduto");
        String dataInicioContrato = request.getParameter("dataInicioContrato");
        
        System.out.println(this.getClass().getSimpleName() + ": --getData, ACAO: " + acao + ", URI: " + request.getRequestURI());

        if(acao == null) {
            acao = Acao.LISTAR.getAcao();
        }
        
        if(!acao.equals(Acao.SALVAR.getAcao()) && !acao.equals(Acao.NOVO.getAcao())) {
            String id = request.getParameter("id");
            
            if(id != null && id != "")
                produto.setId(Integer.parseInt(id));
        }
        
        if(!acao.equals(Acao.EXCLUIR.getAcao()) || acao.equals(Acao.SALVAR.getAcao()) && request.getMethod().equals("GET")) {
            
            try {
                produto.setId((produtoId != null) ? Integer.parseInt(produtoId) : 0);
                
                produto.setTitulo((titulo != null) ? titulo : "Erro");
                produto.setStatus((statusProduto != null) ? Integer.parseUnsignedInt(statusProduto) : 0);
                produto.setDataInativacao((dataInicioContrato != null) ? dateFormat.parse(dataInicioContrato) : new Date());
            } catch (ParseException e) {
                System.out.println(this.getClass().getSimpleName() + ": " + Mensagem.ERRO_CONVERTER_DADOS.getDescricao() + "; \n" + e.getMessage());
                e.printStackTrace();
            }
            
            if(request.getParameter("ativo") != null)
                produto.setAtivo(1);
        }
        
        if(acao.equals(Acao.SALVAR.getAcao()) || acao.equals(Acao.ALTERAR.getAcao()) || acao.equals(Acao.EDITAR.getAcao()) && request.getMethod().equals("POST")) {
            
//            produto.setDuracaoContrato(duracaoContrato);
            
            if(request.getParameter("ativo") != null)
                produto.setAtivo(1);
        }
        
        return produto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Produto> listProduto = new ArrayList<Produto>();
        
        String mensagem = null;
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        
        System.out.println(this.getClass().getSimpleName() + " -- setView: Acao = " + acao + ", URI: " + uri);
        
        if(resultado != null) {
            mensagem = resultado.getMensagem();
            listProduto = (List<Produto>) (Object) resultado.getEntidades();
        }
        
        if(acao != null && acao.equals(Acao.NOVO.getAcao())) {
            request.setAttribute("listaProduto", listProduto);
            request.getRequestDispatcher("/jsp/produto/form.jsp").forward(request, response);
        } else {
            switch(listProduto.size()) {
            case 0:
                request.setAttribute("listaProduto", listProduto);
                request.getRequestDispatcher("/jsp/produto/form.jsp").forward(request, response);
                break;
            
            case 1:
                request.setAttribute("resultado", listProduto.get(0));
                
                if(acao != null && acao.equals(Acao.EDITAR.getAcao())) {
                    request.getRequestDispatcher("/jsp/produto/edit.jsp").forward(request, response);
                    break;
                }
                
                if(acao != null && acao.equals(Acao.EXIBIR.getAcao())) {
                    request.getRequestDispatcher("/jsp/produto/show.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("resultado", listProduto);
                request.getRequestDispatcher("/jsp/produto/index.jsp").forward(request, response);
                break;
            
            default:
                if(mensagem != null && !mensagem.equals("")) {
                    request.setAttribute("mensagem", mensagem);
                    request.getRequestDispatcher("mensagem.jsp").forward(request, response);
                    break;
                }
                
                if(listProduto == null || listProduto.size() < 1) {
                    request.setAttribute("mensagem", Mensagem.ERRO_NAO_ENCONTRADO.getDescricao());
                    request.getRequestDispatcher("mensagem.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("resultado", listProduto);
                request.getRequestDispatcher("/jsp/produto/index.jsp").forward(request, response);
                break;
            }
        }
        
    }

}
