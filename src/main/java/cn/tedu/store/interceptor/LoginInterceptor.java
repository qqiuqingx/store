package cn.tedu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
/**
 * 登录拦截器
 * @author tarena
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		if (session.getAttribute("uid")==null) {
			response.sendRedirect("/web/login.html");
			return false;
		}
		return true;
	}
	
}
