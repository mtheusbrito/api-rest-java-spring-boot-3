package med.voll.api.domain.consulta.validacoes;

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
		var primeiroHorario = agendamentoDTO.data().withHour(7);
		var ultimoHorario = agendamentoDTO.data().withHour(18);
		var pacientePossuiOutroConsultaNoDia = repository.existsByPacienteIdAndAtivoAndDataBetween(agendamentoDTO.idPaciente(),Boolean.TRUE, primeiroHorario, ultimoHorario);
		
		if(pacientePossuiOutroConsultaNoDia) {
			throw new ValidationException("Paciente já possui uma consulta agendada nesse dia!");
		}
	}
}
