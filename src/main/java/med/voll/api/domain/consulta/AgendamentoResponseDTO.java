package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record AgendamentoResponseDTO(
		Long id, 
		Long idMedico, 
		Long idPaciente, 
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
		LocalDateTime data) {

	public AgendamentoResponseDTO(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());// TODO Auto-generated constructor stub
	}

}
