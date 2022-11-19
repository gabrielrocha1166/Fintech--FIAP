package br.com.fintech.dao;

import br.com.fintech.bean.Cadastro;
import br.com.fintech.exception.DBException;

public interface CadastroDAO {

	void cadastrarUsuario(Cadastro cadastro) throws DBException;
	boolean validarUsuario(String email, String senha) throws DBException;

}
