package med.voll.api.domain.medico;

public record MedicoListDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

	public MedicoListDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
}
