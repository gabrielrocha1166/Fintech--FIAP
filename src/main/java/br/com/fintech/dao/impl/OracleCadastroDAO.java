package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fintech.bean.Cadastro;
import br.com.fintech.dao.CadastroDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.singleton.ConnectionManager;


public class OracleCadastroDAO implements CadastroDAO {

	private Connection connect = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	@Override
	public void cadastrarUsuario(Cadastro cadastro) throws DBException {

		try {
			connect = ConnectionManager.getInstance().getConnection();
			pst = connect.prepareStatement(
					"INSERT INTO T_CADASTRO " + "(DS_EMAIL, DS_SENHA, NM_USUARIO)" + "VALUES (?, ?, ?)");
			pst.setString(1, cadastro.getEmail());
			pst.setString(2, cadastro.getSenha());
			pst.setString(3, cadastro.getNome());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("ERRO AO CADASTRAR!");
		} finally {
			try {
				connect.close();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public boolean validarUsuario(String email, String senha) throws DBException {
			
		boolean autenticado = false;
		try {
			connect = ConnectionManager.getInstance().getConnection();
			pst = connect.prepareStatement(
					"SELECT DS_EMAIL, DS_SENHA FROM T_CADASTRO WHERE DS_EMAIL= ? AND DS_SENHA= ?");
			pst.setString(1, email);
			pst.setString(2, senha);
			rs = pst.executeQuery();
			if (rs.next()) {
				rs.getString("DS_EMAIL");
				rs.getString("DS_SENHA");
				autenticado = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("ERRO AO AUTENTICAR!");
		} finally {
			try {
				connect.close();
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return autenticado;
	}



}
