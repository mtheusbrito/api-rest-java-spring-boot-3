package med.voll.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

		@NotBlank String login, @NotBlank String senha) {

}
