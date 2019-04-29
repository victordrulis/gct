package br.com.drulis.gct.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.command.ConsultarCommand;
import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.OcorrenciaTipo;
import br.com.drulis.gct.dominio.Produto;
import br.com.drulis.gct.dominio.Usuario;
import br.com.drulis.gct.util.Resultado;

/**
 * 
 * @author Victor Drulis
 * @since 23 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class AtividadeViewHelper implements ViewHelperInterface {

    @Override
    public DominioInterface getData(HttpServletRequest request) {
        String acao = request.getParameter("acao");
        ConsultarCommand consultar = new ConsultarCommand();
        Atividade atividade = new Atividade();
        Chamado chamado = new Chamado();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String chamadoId = request.getParameter("chamadoId");
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String status = request.getParameter("status");
        String tipo = request.getParameter("tipo");
        String dataInicio = request.getParameter("dataInicio");
        String dataFim = request.getParameter("dataFim");
        String usrAtribuido = request.getParameter("usuarioAtribuido");
        String ativo = request.getParameter("ativo");
        
        System.out.println("[" + this.getClass().getSimpleName() + "] --getData, ACAO: " + acao + ", URI: " + request.getRequestURI());

        if(acao == null) {
            acao = Acao.LISTAR.getAcao();
        }
        
        if(!acao.equals(Acao.SALVAR.getAcao()) && !acao.equals(Acao.NOVO.getAcao())) {
            String id = request.getParameter("id");
            
            if(id != null && id != "")
                atividade.setId(Integer.parseInt(id));
        }
        
        if(acao.equals(Acao.SALVAR.getAcao()) || acao.equals(Acao.ALTERAR.getAcao()) || acao.equals(Acao.EDITAR.getAcao()) && request.getMethod().equals("POST")) {
            Resultado resultado = new Resultado();
            Usuario usuarioAtribuido = new Usuario();
            chamado.setId(Integer.parseInt(chamadoId));
            resultado = consultar.execute(chamado);
            
            try {
                usuarioAtribuido.setId(Integer.parseInt(usrAtribuido));
                
                atividade.setUsuarioAtribuido(usuarioAtribuido);
                atividade.setTitulo(titulo);
                atividade.setDescricao(descricao);
                atividade.setTipo(OcorrenciaTipo.valueOf(tipo));
                atividade.setStatus(Integer.parseUnsignedInt(status));
                atividade.setDataInicio(dateFormat.parse(dataInicio));
                atividade.setDataFim(dateFormat.parse(dataFim));
            } catch (ParseException e) {
                System.out.println("[" + this.getClass().getSimpleName() + "] Erro ao obter dados do formulário." + e.getMessage());
                e.printStackTrace();
            }
            if(ativo != null || ativo != "0")
                atividade.setAtivo(1);
        }
        
        if(!acao.equals(Acao.EXCLUIR.getAcao()) && request.getMethod().equals("GET")) {
            try {
                chamado.setId((chamadoId != null) ? Integer.parseInt(chamadoId) : 0);
                
                atividade.setChamado(chamado);
                
                atividade.setStatus((status != null) ? Integer.parseUnsignedInt(status) : 0);
                atividade.setDataInicio((dataInicio != null) ? dateFormat.parse(dataInicio) : new Date());
            } catch (ParseException e) {
                System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_CONVERTER_DADOS.getDescricao() + "; \n" + e.getMessage());
                e.printStackTrace();
            }
            
            if(ativo != null || ativo != "0")
                atividade.setAtivo(1);
        }
        
        return atividade;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ConsultarCommand consultar = new ConsultarCommand();
        Resultado resAtividade = new Resultado();
        Resultado resChamado = new Resultado();
        
        try {
            resChamado = consultar.execute(new Produto());
            resAtividade = consultar.execute(new Chamado());
        } catch (Exception e) {
            System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_CONVERTER_DADOS + ": " + e.getMessage());
            e.printStackTrace();
        }
        
        String mensagem = null;
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        
        System.out.println("[" + this.getClass().getSimpleName() + "] setView: Acao = " + acao + ", URI: " + uri);
        
        if(resultado != null) {
            mensagem = resultado.getMensagem();
        }
        
        if(acao != null && acao.equals(Acao.NOVO.getAcao())) {
            request.setAttribute("listaAtividades", resAtividade.getEntidades());
            request.setAttribute("listaChamados", resChamado.getEntidades());
            request.getRequestDispatcher("/jsp/atividade/form.jsp").forward(request, response);
        } else {
            switch(resultado.getEntidades().size()) {
            case 0:
                request.setAttribute("listaAtividades", resAtividade.getEntidades());
                request.setAttribute("listaChamados", resChamado.getEntidades());
                request.getRequestDispatcher("/jsp/atividade/form.jsp").forward(request, response);
                break;
            
            case 1:
                request.setAttribute("resultado", resultado.getEntidades().get(0));
                
                if(acao != null && acao.equals(Acao.EDITAR.getAcao())) {
                    request.getRequestDispatcher("/jsp/atividade/edit.jsp").forward(request, response);
                    break;
                }
                
                if(acao != null && acao.equals(Acao.EXIBIR.getAcao())) {
                    request.getRequestDispatcher("/jsp/atividade/show.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("resultado", resultado.getEntidades());
                request.getRequestDispatcher("/jsp/atividade/index.jsp").forward(request, response);
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
                request.getRequestDispatcher("/jsp/atividade/index.jsp").forward(request, response);
                break;
            }
        }
        
    }

}
