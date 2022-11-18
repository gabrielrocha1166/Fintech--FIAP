package br.com.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fintech.bean.Movimentacao;
import br.com.fintech.dao.MovimentacaoDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.singleton.ConnectionManager;

public class OracleMovimentacaoDAO implements MovimentacaoDAO{

	private Connection conexao;
	
	
	//METODO PARA CADASTRAR UMA MOVIMENTACAO
	@Override
	public void cadastrar(Movimentacao movimentacao) throws DBException{
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_MOV (ID_MOV, DS_CTG, DS_SUB_CTG, DS_MOV, DT_DATA, DS_VALOR) VALUES(SQ_T_MOV.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, movimentacao.getCategoria());
			stmt.setString(2, movimentacao.getSubCategoria());
			stmt.setString(3, movimentacao.getDescricao());
			java.sql.Date data = new java.sql.Date(movimentacao.getData().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setDouble(5, movimentacao.getValor());
			stmt.executeUpdate();
			
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		} 
		finally {
			try {
				stmt.close();
				conexao.close();
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}	
		}	
	
	}
	
	//METODO PARA ALTERAR DADOS DE UMA MOVIMENTACAO
	@Override
	public void atualizar(Movimentacao movimentacao) throws DBException{
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_MOV SET DS_CTG = ?, DS_SUB_CTG = ?, DS_MOV = ?, DT_DATA = ?, DS_VALOR = ? WHERE ID_MOV = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, movimentacao.getCategoria());
			stmt.setString(2, movimentacao.getSubCategoria());
			stmt.setString(3, movimentacao.getDescricao());
			java.sql.Date data = new java.sql.Date(movimentacao.getData().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setDouble(5, movimentacao.getValor());
			stmt.setInt(6, movimentacao.getId());
			stmt.executeUpdate();
			
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		} 
		finally {
			try {
				stmt.close();
				conexao.close();
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}	
		}	
	
	}
	
	
	//METODO PARA DELETAR UMA MOVIMENTACAO
	@Override
	public void remover (int codigo) throws DBException{
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_MOV WHERE ID_MOV = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
			
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		} 
		finally {
			try {
				stmt.close();
				conexao.close();
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}	
		}	
	
	}
	
	
	//METODO PARA LISTAR MOVIMENTACAO
	@Override
	public  Movimentacao buscar(int id){
		Movimentacao movimentacao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_MOV WHERE ID_MOV = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()){
				int codigo = rs.getInt("ID_MOV");
				String categoria = rs.getString("DS_CTG");
				String subCategoria = rs.getString("DS_SUB_CTG");
				String descricao = rs.getString("DS_MOV");
				java.sql.Date data = rs.getDate("DT_DATA");
				Calendar dataMovimentacao = Calendar.getInstance();		
				dataMovimentacao.setTimeInMillis(data.getTime());
				double valor = rs.getDouble("DS_VALOR");
				movimentacao = new Movimentacao(codigo, categoria, subCategoria, descricao, dataMovimentacao, valor);
			}
			
		}
			
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return movimentacao;	
	
	}
	
	
    //LISTAR MOVIMENTACOES
	@Override
	public List<Movimentacao> listar(){
		List<Movimentacao> lista = new ArrayList<Movimentacao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_MOV");
			rs = stmt.executeQuery();
		
			//Percorre todos os registros encontrados
			while (rs.next()) {
				int codigo = rs.getInt("ID_MOV");
				String categoria = rs.getString("DS_CTG");
				String subCategoria = rs.getString("DS_SUB_CTG");
				String descricao = rs.getString("DS_MOV");
				java.sql.Date data = rs.getDate("DT_DATA");
				Calendar dataMovimentacao = Calendar.getInstance();		
				dataMovimentacao.setTimeInMillis(data.getTime());
				double valor = rs.getDouble("DS_VALOR");
				
				Movimentacao movimentacao = new Movimentacao(codigo, categoria, subCategoria, descricao, dataMovimentacao, valor);
				lista.add(movimentacao);
			
			}
			
		}
			
			catch (SQLException e) {
				e.printStackTrace();
			} 
			finally {
				try {
					stmt.close();
					rs.close();
					conexao.close();
					} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return lista;
	
			
	}
			
		
}

