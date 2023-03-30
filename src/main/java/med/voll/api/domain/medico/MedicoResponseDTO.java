package med.voll.api.domain.medico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.endereco.Endereco;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicoResponseDTO {
	private Long id;
	private String nome;
	private String email;
	private String crm;
	private String telefone;
	private	Especialidade especialidade;
	private Endereco endereco;
	private Boolean ativo;


	public MedicoResponseDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
				medico.getEspecialidade(), medico.getEndereco(), medico.getAtivo());
	}
}
