package med.voll.api.controller;

import java.net.URI;

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
import med.voll.api.domain.usuario.UsuarioResponseDTO;
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
	public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioDTO dados,  UriComponentsBuilder uriBuilder) {
		Usuario usuario = new Usuario(dados, customPasswordEncoder.encode(dados.getSenha()));
		repository.save(usuario);
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioResponseDTO(usuario));
	}

}
