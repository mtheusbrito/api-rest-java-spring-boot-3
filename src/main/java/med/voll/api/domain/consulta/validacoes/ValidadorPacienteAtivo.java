package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoDTO;
import med.voll.api.domain.paciente.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

	
	@Autowired
	private PacienteRepository repository;

	public void validar(AgendamentoDTO agendamentoDTO) {

		boolean pacienteEstaAtivo = repository.findAtivoById(agendamentoDTO.getIdPaciente());
		if(!pacienteEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído!");
		}
		
	}
}
