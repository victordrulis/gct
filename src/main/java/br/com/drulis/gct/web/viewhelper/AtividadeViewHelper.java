package br.com.drulis.gct.web.viewhelper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.dominio.Atividade;
import br.com.drulis.gct.dominio.Chamado;
import br.com.drulis.gct.dominio.DominioInterface;
import br.com.drulis.gct.dominio.Mensagem;
import br.com.drulis.gct.dominio.Usuario;
import br.com.drulis.gct.dominio.classificacao.OcorrenciaStatus;
import br.com.drulis.gct.dominio.classificacao.OcorrenciaTipo;
import br.com.drulis.gct.web.command.ConsultarCommand;

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
        ConsultarCommand consultar = new ConsultarCommand();
        String acao = request.getParameter("acao");
        Atividade atividade = new Atividade();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String chamadoId = request.getParameter("chamadoId");
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        String status = request.getParameter("status");
        String tipo = request.getParameter("tipo");
        String dataAbertura = request.getParameter("dataAbertura");
        String usrAtribuido = request.getParameter("usuarioAtribuidoId");
        String ativo = request.getParameter("ativo");
        String usrInclusao = request.getParameter("usuarioInclusaoId");
        String usrAlteracao = request.getParameter("usuarioAlteracaoId");
        
        System.out.println("[" + this.getClass().getSimpleName() + "] --getData, ACAO: " + acao + ", URI: " + request.getRequestURI());

        if(acao == null) {
            acao = Acao.LISTAR.getAcao();
        }
        
        if(!acao.equals(Acao.SALVAR.getAcao()) && !acao.equals(Acao.NOVO.getAcao())) {
            String id = request.getParameter("id");
            
            if(id != null && id != "")
                atividade.setId(Integer.parseInt(id));
        }
        
        if(!acao.equals(Acao.EXCLUIR.getAcao()) && request.getMethod().equals("POST")) {
        	Resultado resultado = new Resultado();
        	Usuario usuarioInclusao = new Usuario(1, null, null, null);
        	Usuario usuarioAlteracao = new Usuario(1, null, null, null);
//        	usuarioInclusao.setId(Integer.parseInt(usrInclusao));
        	usuarioInclusao.setId(1);
            Usuario usuarioAtribuido = new Usuario();
            usuarioAtribuido.setId(Integer.parseInt(usrAtribuido));
            Chamado chamado = new Chamado();
            chamado.setId(Integer.parseInt(chamadoId));
            
            Calendar calendar = Calendar.getInstance();
            

            try {
                resultado = consultar.execute(usuarioInclusao);
                atividade.setUsuarioInclusao(usuarioInclusao);
                
                if(acao.equals(Acao.ALTERAR.getAcao()))
                	atividade.setUsuarioUpdate(usuarioAlteracao);
                
            	resultado = consultar.execute(usuarioAtribuido);
            	atividade.setUsuarioAtribuido((Usuario) resultado.getEntidades().get(0));
            	
            	resultado = consultar.execute(chamado);
            	atividade.setChamado((Chamado) resultado.getEntidades().get(0));
            	
                atividade.setTitulo(titulo);
                atividade.setDescricao(descricao);
                atividade.setTipo(OcorrenciaTipo.getMapa().get((Integer.parseInt(tipo))));
                atividade.setOcorrenciaStatus(OcorrenciaStatus.getMapa().get(Integer.parseUnsignedInt(status)));
                atividade.setDataInicio(dateFormat.parse(dataAbertura));
                atividade.setDataFim(calendar.getTime());
            } catch (ParseException e) {
                System.out.println("[" + this.getClass().getSimpleName() + "] Erro ao obter dados do formulário." + e.getMessage());
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
        Resultado resChamado = new Resultado();
        Resultado resUsuario = new Resultado();
        
        String mensagem = null;
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        
        System.out.println("[" + this.getClass().getSimpleName() + "] setView: Acao = " + acao + ", URI: " + uri);
        
        if(resultado != null) {
            mensagem = resultado.getMensagem();
        }
        
        try {
        	resChamado = consultar.execute(new Chamado());
        	resUsuario = consultar.execute(new Usuario());
        } catch (Exception e) {
        	System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_CONVERTER_DADOS.getDescricao() + ": " + e.getMessage());
        	e.printStackTrace();
        }
        
        request.setAttribute("listaChamado", resChamado.getEntidades());
        request.setAttribute("listaUsuario", resUsuario.getEntidades());
        
        if(acao != null && (acao.equals(Acao.NOVO.getAcao()))) {
            request.getRequestDispatcher("/jsp/atividade/form.jsp").forward(request, response);
        } else {
            switch(resultado.getEntidades().size()) {
            case 0:
                try {
                    resChamado = consultar.execute(new Chamado());
                    resUsuario = consultar.execute(new Usuario());
                } catch (Exception e) {
                    System.out.println("[" + this.getClass().getSimpleName() + "] " + Mensagem.ERRO_CONVERTER_DADOS.getDescricao() + ": " + e.getMessage());
                    e.printStackTrace();
                }

                request.setAttribute("listaChamado", resChamado.getEntidades());
                request.setAttribute("listaUsuario", resUsuario.getEntidades());
                
                request.getRequestDispatcher("/jsp/atividade/form.jsp").forward(request, response);
                break;
            
            case 1:
                request.setAttribute("listaChamado", resChamado.getEntidades());
                request.setAttribute("listaUsuario", resUsuario.getEntidades());
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
