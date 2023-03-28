package med.voll.api.infra.database;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;

import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.AuthenticationFacade;

public class AuditorAwareImpl implements AuditorAware<Usuario> {

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Override
	public Optional<Usuario> getCurrentAuditor() {
		
		Authentication authentication = authenticationFacade.getAuthenticateAction();
		
		if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal()  == "anonymousUser") {
			return null;
		}
		
		return Optional.ofNullable((Usuario) authentication.getPrincipal());

	}
}
