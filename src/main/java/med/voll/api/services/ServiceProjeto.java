package med.voll.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.AuthenticationFacade;

@Service
public class ServiceProjeto {

	
	@Autowired
    private AuthenticationFacade authenticationFacade;
	
	
	
	
	public Usuario usuarioLogado() {
		Authentication authentication = authenticationFacade.getAuthenticateAction();
		
		if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal()  == "anonymousUser") {
			return null;
		}
		
		return (Usuario) authentication.getPrincipal();
	}
}
