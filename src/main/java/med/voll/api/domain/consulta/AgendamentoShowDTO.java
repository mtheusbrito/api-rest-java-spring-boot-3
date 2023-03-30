package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoShowDTO {

	private Long id;
	private Long idMedico;
	private Long idPaciente;
	private LocalDateTime data;
}
