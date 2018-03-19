package com.dad.urjcacademy.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Configuration
public class CSRFHandlerConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CSRFHandlerInterceptor());
	}
	
}


class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {
	
	final AntPathRequestMatcher [] matchers = {
		new AntPathRequestMatcher("/login"),
		new AntPathRequestMatcher("/root")
	};
	
	@Override
	public void postHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		if(isRequestMatchers(request)) {
			System.out.println("ACCESS OK");
			modelAndView.addObject("token", token.getToken());
		}
		
		
	}
	
	public boolean isRequestMatchers(HttpServletRequest request) {
		for(AntPathRequestMatcher matcher: matchers) {
			if(matcher.matches(request)) {
				return true;
			}
		}
		return false;
	}
	
}
