package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.services.ServiceProjeto;

@Service
public class AgendaService  extends ServiceProjeto{

@Autowired
private ConsultaRepository repository;


@Autowired 
private MedicoRepository medicoRepository;


@Autowired
private PacienteRepository pacienteRepository;

@Autowired
private List<ValidadorAgendamentoDeConsulta> validadores;

@Autowired
private List<ValidadorCancelamentoDeConsulta> validadorCancelamentoDeConsultas;






	public AgendamentoInfoDTO agendar(AgendamentoDTO agendamentoDTO) {

	
		
		if(!pacienteRepository.existsById(agendamentoDTO.idPaciente())) {
			throw new ValidacaoException("Id do paciente informado não existe!");
			
		}
		
		
		if(agendamentoDTO.idMedico()!= null && !medicoRepository.existsById(agendamentoDTO.idMedico())) {
			throw new ValidacaoException("Id do medico informado não existe!");
		}
		
		validadores.forEach(v -> v.validar(agendamentoDTO));
		
		
		var medico = escolherMedico(agendamentoDTO);
		
		if(medico == null ) {
			throw new ValidacaoException("Não existe médico disponível nessa data");
		}
		var paciente = pacienteRepository.getReferenceById(agendamentoDTO.idPaciente());
	
		var consulta = new Consulta(medico, paciente, agendamentoDTO.data(), null,Boolean.TRUE);
		
		System.out.println(usuarioLogado().getId());
		repository.save(consulta);
		
		
		return new AgendamentoInfoDTO(consulta);

	
	}



	private Medico escolherMedico(AgendamentoDTO agendamentoDTO) {
		
		if(agendamentoDTO.idMedico() != null) {
			
			return medicoRepository.findById(agendamentoDTO.idMedico()).get();
		}
		
		if(agendamentoDTO.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
		}
		
		return medicoRepository.escolherMedicoAleatorioLivreNaData(agendamentoDTO.data(), agendamentoDTO.especialidade());
	}

	public void cancelar(CancelarAgendamentoDTO dto) {
		
		var consulta = repository.getReferenceById(dto.idConsulta());
		
		if(consulta == null) {
			throw new ValidacaoException("Id da consulta infomada não existe");
		}	
		
		validadorCancelamentoDeConsultas.forEach(v -> v.validar(dto));
		
		
		consulta.cancelar(dto);
		
		
		
		
	}
}
