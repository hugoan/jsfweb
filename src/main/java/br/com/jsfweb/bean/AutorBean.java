package br.com.jsfweb.bean;

import javax.faces.bean.ManagedBean;

import br.com.jsfweb.dao.DAO;
import br.com.jsfweb.model.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		this.autor = new Autor();
	}
}

