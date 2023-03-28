package med.voll.api.infra.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade{

	@Override
	public Authentication getAuthenticateAction() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
