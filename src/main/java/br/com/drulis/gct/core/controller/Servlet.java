package br.com.drulis.gct.core.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.drulis.gct.command.AlterarCommand;
import br.com.drulis.gct.command.CommandInterface;
import br.com.drulis.gct.command.ConsultarCommand;
import br.com.drulis.gct.command.ExcluirCommand;
import br.com.drulis.gct.command.InserirCommand;
import br.com.drulis.gct.core.Acao;
import br.com.drulis.gct.core.Entidade;
import br.com.drulis.gct.util.Resultado;
import br.com.drulis.gct.viewhelper.AtividadeViewHelper;
import br.com.drulis.gct.viewhelper.ChamadoViewHelper;
import br.com.drulis.gct.viewhelper.ClienteViewHelper;
import br.com.drulis.gct.viewhelper.ConfigViewHelper;
import br.com.drulis.gct.viewhelper.ContatoViewHelper;
import br.com.drulis.gct.viewhelper.DashboardViewHelper;
import br.com.drulis.gct.viewhelper.ItemViewHelper;
import br.com.drulis.gct.viewhelper.ProdutoViewHelper;
import br.com.drulis.gct.viewhelper.UsuarioViewHelper;
import br.com.drulis.gct.viewhelper.ViewHelperInterface;

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
        listaViewHelper.put("/gct/chamado", new ChamadoViewHelper());
        listaViewHelper.put("/gct/cliente", new ClienteViewHelper());
        listaViewHelper.put("/gct/dashboard", new DashboardViewHelper());
        listaViewHelper.put("/gct/item", new ItemViewHelper());
        listaViewHelper.put("/gct/produto", new ProdutoViewHelper());
        listaViewHelper.put("/gct/usuario", new UsuarioViewHelper());
        
        listaCommand.put(Acao.ALTERAR.getAcao(), new AlterarCommand());
        listaCommand.put(Acao.CONSULTAR.getAcao(), new ConsultarCommand());
        listaCommand.put(Acao.EXCLUIR.getAcao(), new ExcluirCommand());
        listaCommand.put(Acao.EXIBIR.getAcao(), new ConsultarCommand());
        listaCommand.put(Acao.EDITAR.getAcao(), new ConsultarCommand());
        listaCommand.put(Acao.LISTAR.getAcao(), new ConsultarCommand());
        listaCommand.put(Acao.NOVO.getAcao(), new ConsultarCommand());
        listaCommand.put(Acao.SALVAR.getAcao(), new InserirCommand());
        
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
        session.setAttribute("usuario", "admin");
        
        if (acao == null) {
            acao = Acao.LISTAR.getAcao();
            request.setAttribute("acao", acao);
        }
        
        System.out.println(this.getClass().getSimpleName() + ": doGet, URI: " + uri + ", Acao: " + acao);
        
        ViewHelperInterface viewHelper = listaViewHelper.get(uri);
        CommandInterface command = listaCommand.get(acao);
        Entidade entidade = (Entidade) viewHelper.getData(request);
        resultado = command.execute(entidade);
        viewHelper.setView(resultado, request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(this.getClass() + ": doPost");
        this.doGet(request, response);
    }

}
