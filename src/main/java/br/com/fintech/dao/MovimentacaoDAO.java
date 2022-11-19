package br.com.fintech.dao;

import java.util.List;

import br.com.fintech.bean.Movimentacao;
import br.com.fintech.exception.DBException;

public interface MovimentacaoDAO {

	void cadastrar(Movimentacao movimentacao) throws DBException;
	void atualizar(Movimentacao movimentacao) throws DBException;
	void remover(int codigo) throws DBException;
	Movimentacao buscar(int id);
	List<Movimentacao> listar();
	
}


