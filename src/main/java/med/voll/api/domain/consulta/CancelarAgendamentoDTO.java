package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CancelarAgendamentoDTO(
		@NotNull
		Long idConsulta, 
		
		@NotNull(message = "{motivoCancelamento.obrigatorio}")
		MotivoCancelamento motivo ){

}
