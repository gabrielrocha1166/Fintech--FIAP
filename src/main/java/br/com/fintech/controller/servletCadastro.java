package br.com.fintech.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.bean.Cadastro;
import br.com.fintech.dao.CadastroDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;


/**
 * Servlet implementation class servletCadastro
 */
@WebServlet("/servletCadastro")
public class servletCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CadastroDAO dau;
    
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public servletCadastro() throws ServletException {
        super.init();
        dau = DAOFactory.getCadastroDAO();
        // TODO Auto-generated constructor stub
    }
    
    /* Validar email. */
    public boolean validateEmail(String email) {
    	final String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    	final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    /* Validar igualdade entre as senhas. */
    public boolean validatePasswordEqual(String senha, String confirmarSenha) {
    	return (confirmarSenha.equals(senha));
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
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String confirmarSenha =  request.getParameter("confirmarSenha");
		try {
			if (validateEmail(email) == false) {
				request.setAttribute("erro", "EMAIL INVALIDO, VERIFIQUE SEUS DADOS!");
				request.getRequestDispatcher("cadastro.jsp").forward(request, response);
			} else if (validatePasswordEqual(senha, confirmarSenha) == false) {
				request.setAttribute("erro", "SENHAS INCOMPATÃ?VEIS, VERIFIQUE SEUS DADOS!");
				request.getRequestDispatcher("cadastro.jsp").forward(request, response);
			} else {
				Cadastro cadastro = new Cadastro(nome, email, senha);
				dau.cadastrarUsuario(cadastro);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "ERRO AO CADASTRAR, TENTE NOVAMENTE!");
			request.getRequestDispatcher("cadastro.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "ERRO AO CADASTRAR, VALIDE SEUS DADOS!");
			request.getRequestDispatcher("cadastro.jsp").forward(request, response);
		}
	}
}
















//if (validateEmptyInf(nome, email, senha) == false 
//&& validateLenghtInf(nome, email, senha) == false 
//&& validatePasswordEqual(senha, confirmarSenha) == true) {
//dau.cadastrarUsuario(cadastro);
//request.getRequestDispatcher("login.jsp").forward(request, response);
//} else {
//request.setAttribute("erro", "POR FAVOR, VÃ?LIDE SEUS DADOS");
//request.getRequestDispatcher("cadastro.jsp").forward(request, response);
//}