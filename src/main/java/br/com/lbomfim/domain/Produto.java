package br.com.lbomfim.domain;

/**
 * @author Lucas Bomfim 
 */

public class Produto {
	
	private Long id;
	
	private String nome;
	
	private String preco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

}
