package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoDTO;
import med.voll.api.domain.medico.MedicoRepository;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta{

	

	@Autowired
	private MedicoRepository repository;
	
	public void validar(AgendamentoDTO agendamentoDTO) {
		if (agendamentoDTO.idMedico() == null){
			return ;
			}
			
			
			var medicoEstaAtivo = repository.findAtivoById(agendamentoDTO.idMedico());
			
			if(!medicoEstaAtivo) {
				
				throw new ValidacaoException("Consulta não pode ser agendada com médico excludo");
				
			}
			
			
		}
		
		
		
		
		
	}

