package controle;

import modelo.Usuario;

public class Validacao {

	public boolean verificarEmailUsuario(Usuario usuario) {
		return ListaUsuario.usuarios.stream().anyMatch(us -> us.getEmail().equals(usuario.getEmail()));
	}

	public boolean verificarCPFUsuario(Usuario usuario) {
		return ListaUsuario.usuarios.stream().anyMatch(us -> us.getCpf().equals(usuario.getCpf()));
	}

	public boolean validarCPFUsuario(Usuario usuario) {
		return usuario.getCpf().matches("(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)");
	}

	public boolean validarLogin(Usuario usuario) {
		if (ListaUsuario.usuarios.stream().anyMatch(us -> us.getEmail().equals(usuario.getEmail()))
				&& ListaUsuario.usuarios.stream().anyMatch(us -> us.getSenha().equals(usuario.getSenha())))
			return true;
		return false;
	}
}
