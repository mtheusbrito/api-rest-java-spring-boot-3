package med.voll.api.domain.consulta.validacoes;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.AgendamentoDTO;
import med.voll.api.domain.consulta.ConsultaRepository;


@Component
public class ValidarPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta{

	
	@Autowired
	private ConsultaRepository repository;
	
	public void validar(AgendamentoDTO agendamentoDTO) {
		LocalDateTime primeiroHorario = agendamentoDTO.getData().withHour(7);
		LocalDateTime ultimoHorario = agendamentoDTO.getData().withHour(18);
		
		boolean pacientePossuiOutroConsultaNoDia = repository.existsByPacienteIdAndAtivoAndDataBetween(agendamentoDTO.getIdPaciente(),Boolean.TRUE, primeiroHorario, ultimoHorario);
		
		if(pacientePossuiOutroConsultaNoDia) {
			throw new ValidationException("Paciente já possui uma consulta agendada nesse dia!");
		}
	}
}
