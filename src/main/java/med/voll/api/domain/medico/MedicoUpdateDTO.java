package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.endereco.EnderecoDTO;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicoUpdateDTO {
	@NotNull
	private Long id;
	private String nome;
	private String email;
	private String crm;
	private String telefone;
	private Especialidade especialidade;
	private EnderecoDTO endereco;
	private Boolean ativo;

}