package br.com.jsfweb.bean;

import javax.faces.bean.ManagedBean;

import br.com.jsfweb.model.Livro;

@ManagedBean
public class LivroBean {

	Livro livro = new Livro();

	public void gravar() {
		System.out.println("Gravando livro " + livro.getTitulo());
	}

	public Livro getLivro() {
		return livro;
	}
}
