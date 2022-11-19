package br.com.fintech.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.dao.CadastroDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;


/**
 * Servlet implementation class servletLogin
 */
@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CadastroDAO dau;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletLogin() {
        super();
        dau = DAOFactory.getCadastroDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* TODO Auto-generated method stub
		doGet(request, response); */
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		try {
			if (dau.validarUsuario(email, senha) == false) {
				request.setAttribute("erro", "USUARIO OU SENHA INVALIDO!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "ERRO AO INICIAR SESSÃO, TENTE NOVAMENTE!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("erro", "ERRO AO INICIAR SESSÃO, VALIDE SEUS DADOS!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
