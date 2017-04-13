package br.com.jsfweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
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
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	Livro livro = new Livro();
	private Long autorId;

	private List<Livro> livros;

	@Inject
	LivroDao livroDao;
	
	@Inject
	AutorDao autorDao;
	
	@Inject
	FacesContext context;

	@Transacional
	public void gravar() {
		System.out.println("Gravando livro " + livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor"));
		}

		if (this.livro.getId() == null) {
			livroDao.adiciona(this.livro);
			this.livros = livroDao.listaTodos();
		} else {
			livroDao.atualiza(this.livro);
		}
	}

	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Livro gravado por:" + autor.getNome());
	}

	@Transacional
	public void removeLivro(Livro livro) {
		livroDao.remove(livro);
		this.getLivros().remove(livro);
	}

	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public void alteraLivro(Livro livro) {
		this.livro = this.livroDao.buscaPorId(livro.getId());
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN - Deveria começar com 1"));
		}
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Livro> getLivros() {
		if (this.livros == null) {
			this.livros = livroDao.listaTodos();
		}
		return livros;
	}

	public String formAutor() {
		System.out.println("Chamanda o formulario do Autor");
		return "autor?faces-redirect=true";
	}

	public List<Autor> getAutores() {
		return autorDao.listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
}
