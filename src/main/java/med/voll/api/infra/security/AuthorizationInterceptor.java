package med.voll.api.infra.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("AuthorizationInterceptor preHandle");
//		if (isPublicRoute(request)) {
//			// Remove o header Authorization da requisição
////			request.removeAttribute("Authorization");
//		}
		return true;

	}

	private boolean isPublicRoute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

}