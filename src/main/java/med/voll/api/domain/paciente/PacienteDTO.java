package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotBlank;
import med.voll.api.domain.endereco.EnderecoDTO;

public record PacienteDTO(
		
		@NotBlank(message = "{nome.obrigatorio}")
		String nome,
		
		@NotBlank(message = "{email.obrigatorio}")
		String email,
		
		
		@NotBlank(message = "{cpf.obrigatorio}")
		String cpf,
		
		
		@NotBlank(message = "{telefone.obrigatorio}")
		String telefone,
		
		@NotBlank(message = "{logradouro.obrigatorio}")
		EnderecoDTO endenreco
		) {



}
