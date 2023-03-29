package med.voll.api.domain.paciente;

public record PacienteResponseDTO(Long id, String nome, String email, String cpf, String logradouro, String bairro,
		String cep, String complemento, String numero, String uf, String cidade, String telefone, Boolean ativo) {

	public PacienteResponseDTO(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco().getLogradouro(),
				paciente.getEndereco().getBairro(), paciente.getEndereco().getCep(), paciente.getEndereco().getComplemento(), paciente.getEndereco().getNumero(),
				paciente.getEndereco().getUf(), paciente.getEndereco().getCidade(), paciente.getTelefone(), paciente.getAtivo());

	}

}
