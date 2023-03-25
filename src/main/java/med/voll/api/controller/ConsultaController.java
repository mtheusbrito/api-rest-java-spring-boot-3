package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.AgendaService;
import med.voll.api.domain.consulta.AgendamentoDTO;
import med.voll.api.domain.consulta.AgendamentoInfoDTO;
import med.voll.api.domain.consulta.CancelarAgendamentoDTO;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

	@Autowired
	private AgendaService service;

	@PostMapping
	public ResponseEntity<AgendamentoInfoDTO> agendar(@RequestBody @Valid AgendamentoDTO agendamento) {
		var dto =  service.agendar(agendamento);
		return ResponseEntity
				.ok(dto);
	}
	
	
	@PostMapping("/cancelar")
	@Transactional
	public ResponseEntity<Void> excluir(@RequestBody @Valid CancelarAgendamentoDTO cancelarAgendamento) {
		service.cancelar(cancelarAgendamento);

		return ResponseEntity.noContent().build();
	}
}
