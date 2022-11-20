package br.com.fintech.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.br.fintech.bo.EmailBO;
import br.com.fintech.bean.Cadastro;
import br.com.fintech.dao.CadastroDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.exception.EmailException;
import br.com.fintech.factory.DAOFactory;


/**
 * Servlet implementation class servletLogin
 */
@WebServlet("/servletLogin")
public class servletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CadastroDAO dau;
	private EmailBO bo;
       

    public servletLogin() {
        super();
        dau = DAOFactory.getCadastroDAO();
        bo = new EmailBO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		

		
		try {
			if (dau.validarUsuario(email, senha) == false) {
				request.setAttribute("erro", "USUARIO OU SENHA INVALIDO!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("usuario", email);
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


