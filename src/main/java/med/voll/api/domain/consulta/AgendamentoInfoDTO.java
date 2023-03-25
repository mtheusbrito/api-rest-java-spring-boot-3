package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record AgendamentoInfoDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

	public AgendamentoInfoDTO(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());// TODO Auto-generated constructor stub
	}

}
