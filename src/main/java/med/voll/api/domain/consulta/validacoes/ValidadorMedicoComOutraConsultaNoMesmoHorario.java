package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoDTO;
import med.voll.api.domain.consulta.ConsultaRepository;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

	
	@Autowired
	private ConsultaRepository repository;
	
	
	public void validar(AgendamentoDTO agendamentoDTO) {
		
		
		boolean medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndAtivo(agendamentoDTO.getIdMedico(), agendamentoDTO.getData(), Boolean.TRUE);
		
		if(medicoPossuiOutraConsultaNoMesmoHorario) {
			throw new ValidacaoException("Medico já possui outra consutla agendada nesse mesmo horário");
			
			
			
			
		}
	}
}
