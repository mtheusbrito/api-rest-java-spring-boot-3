package med.voll.api.controller;

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

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoDTO;
import med.voll.api.medico.MedicoInfoDTO;
import med.voll.api.medico.MedicoListDTO;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.medico.MedicoUpdateDTO;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<MedicoInfoDTO> cadastrar(@RequestBody @Valid MedicoDTO medicoDTO, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(medicoDTO);
		repository.save(medico);
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new MedicoInfoDTO(medico));

	}

	@GetMapping
	public ResponseEntity<Page<MedicoListDTO>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
//		return repository.findAll().stream().map(MedicoListDTO::new).toList();
		var page = repository.findAllByAtivoTrue(paginacao).map(MedicoListDTO::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<MedicoInfoDTO> atualizar(@RequestBody @Valid MedicoUpdateDTO medicoDTO) {
		var medico = repository.getReferenceById(medicoDTO.id());
		medico.atualizarInformacoes(medicoDTO);
		return ResponseEntity.ok(new MedicoInfoDTO(medico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable @NotNull Long id) {
		var medico = repository.getReferenceById(id);
		medico.excluir();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoInfoDTO> show(@PathVariable @NotNull Long id){
		var medico = repository.getReferenceById(id);
		return ResponseEntity.ok(new MedicoInfoDTO(medico));
		
	}
}
