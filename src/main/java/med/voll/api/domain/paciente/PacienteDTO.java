package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.endereco.EnderecoDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

	@NotBlank(message = "{nome.obrigatorio}")
	private String nome;

	@NotBlank(message = "{email.obrigatorio}")
	private String email;

	@NotBlank(message = "{cpf.obrigatorio}")
	private String cpf;

	@NotBlank(message = "{telefone.obrigatorio}")
	private String telefone;

	@NotBlank(message = "{logradouro.obrigatorio}")
	private EnderecoDTO endenreco;
}