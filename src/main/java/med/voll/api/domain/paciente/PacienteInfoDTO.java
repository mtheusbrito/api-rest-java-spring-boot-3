package med.voll.api.domain.paciente;

public record PacienteInfoDTO(Long id, String nome, String email, String cpf, String logradouro, String bairro,
		String cep, String complemento, String numero, String uf, String cidade, String telefone, Boolean ativo) {

	public PacienteInfoDTO(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getLogradouro(),
				paciente.getBairro(), paciente.getCep(), paciente.getComplemento(), paciente.getNumero(),
				paciente.getUf(), paciente.getCidade(), paciente.getTelefone(), paciente.getAtivo());

	}

}
