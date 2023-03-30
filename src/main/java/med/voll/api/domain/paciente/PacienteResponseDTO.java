package med.voll.api.domain.paciente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseDTO {
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String logradouro;
	private String bairro;

	private String cep;
	private String complemento;
	private String numero;
	private String uf;
	private String cidade;
	private String telefone;
	private Boolean ativo;

	public PacienteResponseDTO(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(),
				paciente.getEndereco().getLogradouro(), paciente.getEndereco().getBairro(),
				paciente.getEndereco().getCep(), paciente.getEndereco().getComplemento(),
				paciente.getEndereco().getNumero(), paciente.getEndereco().getUf(), paciente.getEndereco().getCidade(),
				paciente.getTelefone(), paciente.getAtivo());

	}

}
