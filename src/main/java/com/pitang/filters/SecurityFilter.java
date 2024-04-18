package com.pitang.filters;

import com.google.gson.JsonObject;
import com.pitang.services.LoginService;
import com.pitang.utis.ErrorMessagesConstants;
import com.pitang.utis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends GenericFilterBean {

	private final LoginService loginService;

	private final SecurityUtils securityUtils;

	@Autowired
	public SecurityFilter(LoginService loginService, SecurityUtils securityUtils) {
		this.loginService = loginService;
		this.securityUtils = securityUtils;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {

		HttpServletResponse res = securityUtils.fillAccessControlHeader((HttpServletResponse) response);

		try {

			HttpServletRequest req = (HttpServletRequest) request;

			if (RequestMethod.OPTIONS.name().equalsIgnoreCase(req.getMethod())) {
				res.setStatus(HttpServletResponse.SC_OK);
				return;
			}

			Authentication auth = loginService.getAuthentication(req);
			SecurityContextHolder.getContext().setAuthentication(auth);
			chain.doFilter(request, response);

		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			res.setContentType("application/json; charset=utf-8");
			final JsonObject error = new JsonObject();
			res.getWriter().write(error.toString());
		}
	}
}