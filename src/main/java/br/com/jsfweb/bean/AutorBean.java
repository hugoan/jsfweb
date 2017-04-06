package br.com.jsfweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsfweb.dao.AutorDao;
import br.com.jsfweb.dao.LivroDao;
import br.com.jsfweb.model.Autor;
import br.com.jsfweb.model.Livro;
import br.com.jsfweb.tx.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Autor autor = new Autor();
	private Long autorId;
	
	@Inject
	private AutorDao autorDao;

	@Inject
	LivroDao livroDao;

	public void carregarAutorPelaId() {
		this.autor = this.autorDao.buscaPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}

	@Transacional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			this.autorDao.adiciona(this.autor);
		} else {
			this.autorDao.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	@Transacional
	public void removerAutor(Autor autor) {

		List<Livro> livros = livroDao.listaTodos();

		for (Livro livro : livros) {
			if (livro.getAutores().equals(autor)) {
				throw new ValidatorException(new FacesMessage(
						"O autor " + autor.getNome() + " não pode ser excluido, pois contém livro cadastrado."));
			} else {
				this.autorDao.remove(autor);
			}
		}
	}

	public List<Autor> getAutores() {
		return this.autorDao.listaTodos();
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
