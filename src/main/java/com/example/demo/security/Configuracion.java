package com.example.demo.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@ComponentScan
public class Configuracion extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user01").password(codifica().encode("123456")).roles("USER")
		.and().withUser("user02").password(codifica().encode("123456")).roles("USER")
		.and().withUser("user03").password(codifica().encode("123456")).roles("USER");
	}
		
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable();
	}
	
	@Bean
	@Order(0)
	public FilterRegistrationBean filterRegistrationBean() {
		UrlBasedCorsConfigurationSource codigo = new UrlBasedCorsConfigurationSource();
		CorsConfiguration configura = new CorsConfiguration();
		configura.setAllowCredentials(true);
		configura.addAllowedOrigin("*");
		configura.addAllowedHeader("*");
		configura.addAllowedMethod("*");
		codigo.registerCorsConfiguration("/**", configura);
		return new FilterRegistrationBean(new CorsFilter(codigo));
	}

	public BCryptPasswordEncoder codifica() {
		return new BCryptPasswordEncoder();
	}
		
}
