package cn.tedu.store.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import cn.tedu.store.interceptor.LoginInterceptor;

@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//拦截器路径(黑名单)登录才能访问
		List <String> lists=new ArrayList<>();
		lists.add("/**");
		//白名单:在黑名单范围内,却不需要登录
		List<String> patterns=new ArrayList<>();
		patterns.add("/bootstrap3/**");
		patterns.add("/css/**");
		patterns.add("/js/**");
		patterns.add("/images/**");
		patterns.add("/districts/**");
		patterns.add("/goods/**");
		
		patterns.add("/web/register.html");
		patterns.add("/users/reg");
		patterns.add("/web/login.html");
		patterns.add("/users/login");
		patterns.add("/web/index.html");
		patterns.add("/web/product.html");
		//注册拦截器
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns(lists).excludePathPatterns(patterns);
		
	}

}
