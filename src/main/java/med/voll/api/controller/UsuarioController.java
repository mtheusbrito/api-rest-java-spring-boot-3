package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioDTO;
import med.voll.api.domain.usuario.UsuarioInfoDTO;
import med.voll.api.domain.usuario.UsuarioRepository;
import med.voll.api.infra.security.CustomPasswordEncoder;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-token")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired 
	private CustomPasswordEncoder customPasswordEncoder;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid UsuarioDTO dados,  UriComponentsBuilder uriBuilder) {
		var usuario = new Usuario(dados, customPasswordEncoder.encode(dados.senha()));
		repository.save(usuario);
		var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioInfoDTO(usuario));
	}

}
