package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CancelarAgendamentoDTO {
		@NotNull
		private Long idConsulta;
		@NotNull(message = "{motivoCancelamento.obrigatorio}")
		MotivoCancelamento motivo; 

}
