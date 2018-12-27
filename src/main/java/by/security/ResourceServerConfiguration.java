package by.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "oauth2-server";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		anonymous().disable()
		.requestMatchers().antMatchers("/**")
		.and().authorizeRequests()
		.antMatchers("/user/**").access("hasRole('ADMIN')")
		.and().authorizeRequests()
		.antMatchers("/candidate/**").access("hasRole('ADMIN')")
		.and().authorizeRequests()
		.antMatchers("/vacancy/**").access("hasRole('ADMIN')")
		.and().authorizeRequests()
		.antMatchers("/skill/**").access("hasRole('ADMIN')")
		.and().authorizeRequests()
		.antMatchers("/interview/**").access("hasRole('ADMIN')")
		.and().authorizeRequests()
		.antMatchers("/feedback/**").access("hasRole('ADMIN')")
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}