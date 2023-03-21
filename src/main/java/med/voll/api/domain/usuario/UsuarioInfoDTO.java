package med.voll.api.domain.usuario;

public record UsuarioInfoDTO(Long id, String login, String senha) {

	public UsuarioInfoDTO(Usuario usuario) {
		this(usuario.getId(), usuario.getLogin(), usuario.getSenha());
	}

}
