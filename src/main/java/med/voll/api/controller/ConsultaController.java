package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaService;
import med.voll.api.domain.consulta.AgendamentoDTO;
import med.voll.api.domain.consulta.AgendamentoResponseDTO;
import med.voll.api.domain.consulta.CancelarAgendamentoDTO;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-token")
public class ConsultaController {

	@Autowired
	private AgendaService service;

	@PostMapping
	public ResponseEntity<AgendamentoResponseDTO> agendar(@RequestBody @Valid AgendamentoDTO agendamento) {
		AgendamentoResponseDTO dto =  service.agendar(agendamento);
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
