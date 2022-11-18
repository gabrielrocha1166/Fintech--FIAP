package br.com.fintech.factory;

import br.com.fintech.dao.MovimentacaoDAO;
import br.com.fintech.dao.impl.OracleMovimentacaoDAO;

public class DAOFactory {
	
	public static MovimentacaoDAO getMovimentacaoDAO() {
		return new OracleMovimentacaoDAO();
	}

}