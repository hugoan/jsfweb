package br.com.jsfweb.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.jsfweb.dao.DAO;
import br.com.jsfweb.model.Autor;
import br.com.jsfweb.model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	Livro livro = new Livro();
	private Long autorId;

	public void gravar() {
		System.out.println("Gravando livro " + livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Livro gravado por:" + autor.getNome());
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		String valor = value.toString();
		if(!valor.startsWith("1")){
			throw new ValidatorException(new FacesMessage("ISBN - Deveria começar com 1"));
		}
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
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
