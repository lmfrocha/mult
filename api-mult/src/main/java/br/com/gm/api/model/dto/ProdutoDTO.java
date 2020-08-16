package br.com.gm.api.model.dto;

import br.com.gm.api.model.enums.Categoria;

public class ProdutoDTO {

	private Integer id;
	
	private String nome;
	
	private Categoria categoria;
	
	private Double valor;
	
	public ProdutoDTO() {}
	
	public ProdutoDTO(Integer id, String nome, Categoria categoria, Double valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
