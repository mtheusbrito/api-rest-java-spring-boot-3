package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoDTO;


@Component
public class ValidadorHorarioAntecedencia  implements ValidadorAgendamentoDeConsulta{

	
	
	public void validar(AgendamentoDTO agendamentoDTO) {

		var dataConsulta = agendamentoDTO.data();
		var agora = LocalDateTime.now();

		var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

		if (diferencaEmMinutos < 30) {
			throw new ValidacaoException("Consulta deve ser agendada com antecedencia mínima de 30 minutos");

		}
	}

}
