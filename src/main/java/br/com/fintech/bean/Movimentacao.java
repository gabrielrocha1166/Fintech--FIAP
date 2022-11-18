package br.com.fintech.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Movimentacao {
	
	private int	   id;
	private String categoria;
	private String subCategoria;
	private String descricao;
	private Calendar data;
	private double valor;
	
	public Movimentacao() {
		super();
	}
	
	public Movimentacao(int id, String categoria, String subCategoria, String descricao, Calendar data, double valor) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.subCategoria = subCategoria;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
//	public String toString() {
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		return "Movimento [id=" + id 
//				+ ", categoria=" + categoria 
//				+ ", subCategoria=" + subCategoria 
//				+ ", descricao="+ descricao 
//				+ ", data=" + sdf.format(data.getTime())
//				+ ", valor=" + valor 
//				+ "]";
//	}
	
	
}
