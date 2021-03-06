package br.com.drulis.gct.web.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.core.util.Resultado;
import br.com.drulis.gct.web.command.AlterarCommand;
import br.com.drulis.gct.web.command.CommandInterface;
import br.com.drulis.gct.web.command.ConsultarCommand;
import br.com.drulis.gct.web.command.ExcluirCommand;
import br.com.drulis.gct.web.command.HistoricoCommand;
import br.com.drulis.gct.web.command.InserirCommand;
import br.com.drulis.gct.web.viewhelper.AtividadeViewHelper;
import br.com.drulis.gct.web.viewhelper.ChamadoViewHelper;
import br.com.drulis.gct.web.viewhelper.ClienteViewHelper;
import br.com.drulis.gct.web.viewhelper.ConfigViewHelper;
import br.com.drulis.gct.web.viewhelper.ContatoViewHelper;
import br.com.drulis.gct.web.viewhelper.ContratoViewHelper;
import br.com.drulis.gct.web.viewhelper.DashboardViewHelper;
import br.com.drulis.gct.web.viewhelper.ItemViewHelper;
import br.com.drulis.gct.web.viewhelper.LoginViewHelper;
import br.com.drulis.gct.web.viewhelper.ProdutoViewHelper;
import br.com.drulis.gct.web.viewhelper.UsuarioViewHelper;
import br.com.drulis.gct.web.viewhelper.ViewHelperInterface;

/**
 * Servlet implementation
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static HashMap<String, ViewHelperInterface> listaViewHelper;
    private static HashMap<String, CommandInterface> listaCommand;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        listaViewHelper  = new HashMap<String, ViewHelperInterface>();
        listaCommand = new HashMap<String, CommandInterface>();
        
        listaViewHelper.put("/gct/atividade", new AtividadeViewHelper());
        listaViewHelper.put("/gct/home", new ChamadoViewHelper());
        listaViewHelper.put("/gct/config", new ConfigViewHelper());
        listaViewHelper.put("/gct/contato", new ContatoViewHelper());
        listaViewHelper.put("/gct/contrato", new ContratoViewHelper());
        listaViewHelper.put("/gct/chamado", new ChamadoViewHelper());
        listaViewHelper.put("/gct/cliente", new ClienteViewHelper());
        listaViewHelper.put("/gct/dashboard", new DashboardViewHelper());
        listaViewHelper.put("/gct/item", new ItemViewHelper());
        listaViewHelper.put("/gct/produto", new ProdutoViewHelper());
        listaViewHelper.put("/gct/usuario", new UsuarioViewHelper());
        listaViewHelper.put("/gct/login", new LoginViewHelper());
        
        listaCommand.put(Acao.ALTERAR.getAcao(), new AlterarCommand());
        listaCommand.put(Acao.CONSULTAR.getAcao(), new ConsultarCommand());
        listaCommand.put(Acao.EXCLUIR.getAcao(), new ExcluirCommand());
        listaCommand.put(Acao.EXIBIR.getAcao(), new ConsultarCommand());
        listaCommand.put(Acao.EDITAR.getAcao(), new ConsultarCommand());
        listaCommand.put(Acao.LISTAR.getAcao(), new ConsultarCommand());
        listaCommand.put(Acao.NOVO.getAcao(), new ConsultarCommand());
        listaCommand.put(Acao.SALVAR.getAcao(), new InserirCommand());
        listaCommand.put(Acao.HISTORICO.getAcao(), new HistoricoCommand());
        
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Resultado resultado = new Resultado();
        String uri = request.getRequestURI();
        String acao = request.getParameter("acao");
        
        // TODO adicionar metodos para capturar o usuario e colocar na sessao
        session.setAttribute("idUsuario", "1");
        
        if (acao == null) {
            acao = Acao.LISTAR.getAcao();
            request.setAttribute("acao", acao);
        }
        
        System.out.println("[" + this.getClass().getSimpleName() + "] doGet, URI: " + uri + ", Acao: " + acao);
        
        ViewHelperInterface viewHelper = listaViewHelper.get(uri);
        Entidade entidade = (Entidade) viewHelper.getData(request);
        CommandInterface command = listaCommand.get(acao);
        resultado = command.execute(entidade);
        viewHelper.setView(resultado, request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[" + this.getClass().getSimpleName() + "] [INFO] URI: " + request.getRequestURI() + "; Method: " + request.getMethod());
        this.doGet(request, response);
    }

}
