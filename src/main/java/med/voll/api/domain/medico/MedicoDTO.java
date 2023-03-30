package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.endereco.EnderecoDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
	@NotBlank(message = "{nome.obrigatorio}")
	private String nome;

	@NotBlank(message = "{telefone.obrigatorio}")
	private String telefone;

	@NotBlank(message = "{email.obrigatorio}")
	@Email(message = "{email.invalido}")
	private String email;

	@NotBlank(message = "{crm.obrigatorio}")
	@Pattern(regexp = "\\d{4;6}", message = "{crm.invalido}")
	private String crm;

	@NotNull(message = "{especialidade.obrigatoria}")
	private Especialidade especialidade;

	@NotNull(message = "{endereco.obrigatorio}")
	@Valid
	private EnderecoDTO endereco;
}
