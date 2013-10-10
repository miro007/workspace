
package pl.com.stream.topfirma.partner.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pl.com.stream.topfirma.partner.UserContext;

public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

		HttpSession session = request.getSession();
		String requestURI = request.getRequestURI();
		UserContext userContext =
				WebApplicationContextUtils.getWebApplicationContext(session.getServletContext()).getBean(UserContext.class);
		if (!userContext.isAuthorized() & !requestURI.endsWith("login")) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return false;
		}
		return true;
	}
}
