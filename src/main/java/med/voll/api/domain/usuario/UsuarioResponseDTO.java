package med.voll.api.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
	private Long id; 
	private String login;
	private String senha;


	public UsuarioResponseDTO(Usuario usuario) {
		this(usuario.getId(), usuario.getLogin(), usuario.getSenha());
	}

}
