package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotBlank;

public record PacienteDTO(
		
		@NotBlank(message = "{nome.obrigatorio}")
		String nome,
		
		@NotBlank(message = "{email.obrigatorio}")
		String email,
		
		
		@NotBlank(message = "{cpf.obrigatorio}")
		String cpf,
		
		@NotBlank(message = "{logradouro.obrigatorio}")
		String logradouro,
		
		@NotBlank(message = "{bairro.obrigatorio}")
		String bairro,
		
		@NotBlank(message = "{cep.obrigatorio}")
		String cep,
		
		@NotBlank(message = "{complemento.obrigatorio}")
		String complemento,
		
		@NotBlank(message = "{numero.obrigatorio}")
		String numero,
		
		@NotBlank(message = "{uf.obrigatorio}")
		String uf,
		
		@NotBlank(message = "{cidade.obrigatorio}")
		String cidade,
		
		@NotBlank(message = "{telefone.obrigatorio}")
		String telefone
		) {

}
