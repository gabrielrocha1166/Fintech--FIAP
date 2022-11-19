package br.com.fintech.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.bean.Movimentacao;
import br.com.fintech.dao.MovimentacaoDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;


@WebServlet("/movimentacao")
public class MovimentacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MovimentacaoDAO dao;
	
	@Override
    public void init() throws ServletException {
        super.init();
        dao = DAOFactory.getMovimentacaoDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch(acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar(request, response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		
		}
			
	}

	
	
	//excluir movimentacao
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		System.out.println(id);
		try {
			dao.remover(id);
			request.setAttribute("msg", "Movimentação removida!");
		} 
		catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
		
	}
	
		
	
	
	
	
	//cadastrar movimentacao
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String categoria = request.getParameter("categoria");
			String subcategoria = request.getParameter("subcategoria");
			String descricao = request.getParameter("descricao");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			double valor = Double.parseDouble(request.getParameter("valor"));
			
			Movimentacao movimentacao = new Movimentacao (0, categoria, subcategoria, descricao, data, valor);
			dao.cadastrar(movimentacao);
			
			request.setAttribute("msg", "Movimentacao cadastrada!");
		}
		catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		}
		catch(Exception e){
			e.printStackTrace();
			request.setAttribute("erro","Por favor, valide os dados");
		}
		request.getRequestDispatcher("cadastro-movimentacao.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	//editar movimentacao
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String categoria = request.getParameter("categoria");
			String subcategoria = request.getParameter("subcategoria");
			String descricao = request.getParameter("descricao");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			double valor = Double.parseDouble(request.getParameter("valor"));
			
			Movimentacao movimentacao = new Movimentacao (id, categoria, subcategoria, descricao, data, valor);
			dao.atualizar(movimentacao);

			request.setAttribute("msg", "Produto atualizado!");
		} 
		catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		listar(request,response);
	}
	
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		
		case "abrir-form-edicao":
			int id = Integer.parseInt(request.getParameter("id"));
			Movimentacao movimentacao = dao.buscar(id);
			request.setAttribute("movimentacao", movimentacao);
			request.getRequestDispatcher("edicao-movimentacao.jsp").forward(request, response);	
		
		}
		
	}

	
	//listar movimentacoes
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Movimentacao> lista = dao.listar();
		request.setAttribute("movimentacoes", lista);
		request.getRequestDispatcher("lista-movimentacao.jsp").forward(request, response);
	}

}




