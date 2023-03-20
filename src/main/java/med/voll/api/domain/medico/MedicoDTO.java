package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.EnderecoDTO;

public record MedicoDTO(
@NotBlank(message = "{nome.obrigatorio}")
String nome, 

@NotBlank(message = "{telefone.obrigatorio}")
String telefone,

@NotBlank(message = "{email.obrigatorio}")
@Email(message = "{email.invalido}")
String email, 

@NotBlank(message = "{crm.obrigatorio}")
@Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
String crm, 

@NotNull(message = "{especialidade.obrigatoria}")
Especialidade especialidade,

@NotNull(message = "{endereco.obrigatorio}")
@Valid
EnderecoDTO endereco
) {

}
