package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoDTO;
import med.voll.api.domain.medico.MedicoListDTO;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.MedicoResponseDTO;
import med.voll.api.domain.medico.MedicoUpdateDTO;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-token")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<MedicoResponseDTO> cadastrar(@RequestBody @Valid MedicoDTO medicoDTO, UriComponentsBuilder uriBuilder) {
		Medico medico = new Medico(medicoDTO);
		repository.save(medico);
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new MedicoResponseDTO(medico));

	}

	@GetMapping
	public ResponseEntity<Page<MedicoListDTO>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
//		return repository.findAll().stream().map(MedicoListDTO::new).toList();
		Page<MedicoListDTO> page = repository.findAllByAtivoTrue(paginacao).map(MedicoListDTO::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<MedicoResponseDTO> atualizar(@RequestBody @Valid MedicoUpdateDTO medicoDTO) {
		Medico medico = repository.getReferenceById(medicoDTO.getId());
		medico.atualizarInformacoes(medicoDTO);
		return ResponseEntity.ok(new MedicoResponseDTO(medico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable @NotNull Long id) {
		Medico medico = repository.getReferenceById(id);
		medico.excluir();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoResponseDTO> show(@PathVariable @NotNull Long id){
		Medico medico = repository.getReferenceById(id);
		return ResponseEntity.ok(new MedicoResponseDTO(medico));
		
	}
}
