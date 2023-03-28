package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.LoginDTO;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioRepository;
import med.voll.api.infra.security.CustomPasswordEncoder;
import med.voll.api.infra.security.TokenDTO;
import med.voll.api.infra.security.TokenService;
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> efetuarLogin (@RequestBody @Valid LoginDTO dados
			) {
		
		var AuthenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		
		
		
		var authentication = manager.authenticate(AuthenticationToken);
		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
//		session.setAttribute("usuarioLogado", (Usuario) authentication.getPrincipal());
		
		
		
		
		return ResponseEntity.ok(new TokenDTO(tokenJWT));
		
	}
}
