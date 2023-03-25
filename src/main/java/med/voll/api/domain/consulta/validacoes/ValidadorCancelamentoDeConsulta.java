package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.CancelarAgendamentoDTO;

public interface ValidadorCancelamentoDeConsulta {

	
	void validar(CancelarAgendamentoDTO dto);
}
