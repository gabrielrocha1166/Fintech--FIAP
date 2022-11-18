package br.com.fiap.teste;

import java.util.Calendar;
import java.util.List;

import br.com.fintech.bean.Movimentacao;
import br.com.fintech.dao.MovimentacaoDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;

public class TesteMovimentacaoDAO {

	public static void main(String[] args) {
		MovimentacaoDAO dao = DAOFactory.getMovimentacaoDAO();
		
//		cadastrar movimentacao
		Movimentacao movimentacao = new Movimentacao(0, "despeza", "conta", "luz dezembro", Calendar.getInstance(), 1500.50);
		try {
			dao.cadastrar(movimentacao);
			System.out.println("Movimentacao cadastrada.");
		}
		
		catch(DBException e) {
			e.printStackTrace();
		}
		
		
		//atualizar movimentacao pelo codigo
		movimentacao = dao.buscar(6);
		movimentacao.setDescricao("casa");
		movimentacao.setSubCategoria("aluguel");
		movimentacao.setValor(5300);
		try {
			dao.atualizar(movimentacao);
			System.out.println("Produto atualizado");
		}
		catch (DBException e) {
			e.printStackTrace();
		}
		
		//listar as movimentacoes
		List<Movimentacao> lista = dao.listar();
			for(Movimentacao item : lista) {
				System.out.println(item.getSubCategoria() + " " + item.getValor());
			}
		
				
		//deletar movimentacao
		try {
			dao.remover(6);
			System.out.println("Movimentacao removida");
		}
		catch(DBException e){
			e.printStackTrace();
			System.out.println("falhou aqui");
		}
		
	}

}
