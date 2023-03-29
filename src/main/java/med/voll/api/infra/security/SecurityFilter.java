package med.voll.api.infra.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository repository;

	public final List<AntPathRequestMatcher> publicRoutes = Arrays.asList(new AntPathRequestMatcher("/login"),
			new AntPathRequestMatcher("/swagger-ui.html"));

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

	
//		ignorar header Authorization caso venha presente em rotas públicas
		Boolean publicRoute = Arrays.asList(getPublicRoutes()).stream().anyMatch(r -> r.matches(request));
		
			var tokenJWT = recuperarToken(request);
			
			if(tokenJWT != null ) {
				
				if(!publicRoute) {
					var subject = tokenService.getSubject(tokenJWT);
					var usuario = repository.findByLogin(subject);
					var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
				}
				
				
			}
//		}

		filterChain.doFilter(request, response);

	}

	public RequestMatcher[] getPublicRoutes() {
		RequestMatcher[] routes = new RequestMatcher[] { 
				new AntPathRequestMatcher("/login", "POST"),
				new AntPathRequestMatcher("/v3/api-docs/**"),
				new AntPathRequestMatcher("/swagger-ui.html"),
				new AntPathRequestMatcher("/swagger-ui/index.html"),
				new AntPathRequestMatcher("/swagger-ui/**")
				
		};

		return routes;
	}

	private String recuperarToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}
		return null;

	}

}
