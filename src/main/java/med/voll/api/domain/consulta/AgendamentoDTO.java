package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.medico.Especialidade;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendamentoDTO {
		
		
		private Long idMedico;
		
		@NotNull
		private Long idPaciente;
		
		@NotNull
		@Future
		private LocalDateTime data;
		
		Especialidade especialidade; 
}
