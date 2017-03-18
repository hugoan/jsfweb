package br.com.jsfweb.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.validator.ValidatorException;

import br.com.jsfweb.dao.DAO;
import br.com.jsfweb.model.Autor;
import br.com.jsfweb.model.Livro;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	private Long autorId;

	public void carregarAutorPelaId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	public void removerAutor(Autor autor) {

		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();

		for (Livro livro : livros) {
			if (livro.getAutores().equals(autor)) {
				throw new ValidatorException(new FacesMessage(
						"O autor " + autor.getNome() + " não pode ser excluido, pois contém livro cadastrado."));
			} else {
				new DAO<Autor>(Autor.class).remove(autor);
			}
		}
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void alterarAutor(Autor autor) {
		this.autor = autor;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public Long getAutorId() {
		return autorId;
	}
}
