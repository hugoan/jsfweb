package br.com.jsfweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jsfweb.dao.UsuarioDAO;
import br.com.jsfweb.model.Usuario;

@ManagedBean
@ViewScoped
public class loginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		System.out.println("Fazendo login do usuário " + this.usuario.getEmail());

		boolean existe = new UsuarioDAO().existe(this.usuario);

		if (existe) {
			return "livro?faces-redirect=true";
		}
		return null;
	}

}
