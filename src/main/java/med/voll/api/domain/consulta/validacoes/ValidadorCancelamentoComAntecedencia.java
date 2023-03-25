package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.CancelarAgendamentoDTO;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;

@Component
public class ValidadorCancelamentoComAntecedencia implements ValidadorCancelamentoDeConsulta {

	
	@Autowired
	private ConsultaRepository repository;
	
	public void validar(CancelarAgendamentoDTO dto) {
		// TODO Auto-generated method stub
		LocalDateTime dataAtual = LocalDateTime.now();
		Consulta consulta = repository.findById(dto.idConsulta()).get();
		System.out.println(consulta.getData());
		var diferencaEmHoras = Duration.between(dataAtual, consulta.getData()).toHours();
		
		

		if(diferencaEmHoras < 24) {
			throw new ValidacaoException("Uma consulta somente poderá ser cancelada com antecedência mínima de 24 horas.");
		}
	}

}
