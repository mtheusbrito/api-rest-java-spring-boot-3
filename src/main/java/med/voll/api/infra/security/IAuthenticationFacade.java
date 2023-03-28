package med.voll.api.infra.security;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {

	
	Authentication getAuthenticateAction();
}
