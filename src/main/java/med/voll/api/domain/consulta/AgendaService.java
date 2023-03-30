package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
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






	public AgendamentoResponseDTO agendar(AgendamentoDTO agendamentoDTO) {

	
		
		if(!pacienteRepository.existsById(agendamentoDTO.getIdPaciente())) {
			throw new ValidacaoException("Id do paciente informado não existe!");
			
		}
		
		
		if(agendamentoDTO.getIdMedico()!= null && !medicoRepository.existsById(agendamentoDTO.getIdMedico())) {
			throw new ValidacaoException("Id do medico informado não existe!");
		}
		
		validadores.forEach(v -> v.validar(agendamentoDTO));
		
		
		Medico medico = escolherMedico(agendamentoDTO);
		
		if(medico == null ) {
			throw new ValidacaoException("Não existe médico disponível nessa data");
		}
		Paciente paciente = pacienteRepository.getReferenceById(agendamentoDTO.getIdPaciente());
	
		Consulta consulta = new Consulta(medico, paciente, agendamentoDTO.getData(), null,Boolean.TRUE);
		
		System.out.println(usuarioLogado().getId());
		repository.save(consulta);
		
		
		return new AgendamentoResponseDTO(consulta);

	
	}



	private Medico escolherMedico(AgendamentoDTO agendamentoDTO) {
		
		if(agendamentoDTO.getIdMedico() != null) {
			
			return medicoRepository.findById(agendamentoDTO.getIdMedico()).get();
		}
		
		if(agendamentoDTO.getEspecialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
		}
		
		return medicoRepository.escolherMedicoAleatorioLivreNaData(agendamentoDTO.getData(), agendamentoDTO.getEspecialidade());
	}

	public void cancelar(CancelarAgendamentoDTO dto) {
		
		Consulta consulta = repository.getReferenceById(dto.getIdConsulta());
		
		if(consulta == null) {
			throw new ValidacaoException("Id da consulta infomada não existe");
		}	
		
		validadorCancelamentoDeConsultas.forEach(v -> v.validar(dto));
		
		
		consulta.cancelar(dto);
		
		
		
		
	}
}
