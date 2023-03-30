package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteDTO;
import med.voll.api.domain.paciente.PacienteResponseDTO;
import med.voll.api.domain.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-token")
public class PacienteController {

	
	@Autowired
	private PacienteRepository repository;
	
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<PacienteResponseDTO> cadastrar(@RequestBody @Valid PacienteDTO pacienteDTO, UriComponentsBuilder uriBuilder) {
		Paciente paciente = new Paciente(pacienteDTO);
		repository.save(paciente);
		URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PacienteResponseDTO(paciente));

	} 
	
	
	@GetMapping
	public ResponseEntity<Page<PacienteResponseDTO>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
//		return repository.findAll().stream().map(MedicoListDTO::new).toList();
		Page<PacienteResponseDTO> page = repository.findAllByAtivoTrue(paginacao).map(PacienteResponseDTO::new);
		return ResponseEntity.ok(page);
	}
}
