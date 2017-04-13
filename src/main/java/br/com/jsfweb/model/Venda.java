package br.com.jsfweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Venda {

	@Id 
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Livro livro;
	
	private Integer quantidade;

	public Venda(Livro livro, Integer quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
	}
	
	public Venda(){
		
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
