package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class ServidorAutorizacion extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private AuthenticationManager authoriza;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		super.configure(security);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("admin")
		.secret(codifica().encode("123456"))
		.authorizedGrantTypes("password", "authorization_code","refresh_token","implicit")
		.scopes("read","write")
		.accessTokenValiditySeconds(3600) //tiempo maximo para relaizar peticion
		.refreshTokenValiditySeconds(4000); //tiempo de actualizacion de token
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
		.authenticationManager(authoriza);
	}

	
	@Bean
	public BCryptPasswordEncoder codifica() {
		return new BCryptPasswordEncoder();
	}
	
}
