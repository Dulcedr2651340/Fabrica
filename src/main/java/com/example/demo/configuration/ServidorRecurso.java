package com.example.demo.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
@ComponentScan
public class ServidorRecurso extends ResourceServerConfigurerAdapter{

	private static final String RESOURCE_ID = "resource_id";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override 
	public void configure(HttpSecurity http) throws Exception {  //indicamos que recursos vamos a emplear
		http.anonymous().disable()
		.authorizeRequests().antMatchers("/producto/**").access("hasRole('USER')")
		.antMatchers("/linea/**").access("hasRole('USER')")
		.antMatchers("/fabrica/**").access("hasRole('USER')")
		.antMatchers("/piezas/**").access("hasRole('USER')")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
	
	

}
