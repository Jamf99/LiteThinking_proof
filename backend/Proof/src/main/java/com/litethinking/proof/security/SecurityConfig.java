package com.litethinking.proof.security;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers().frameOptions().disable()
		.and()
		.authorizeRequests()
		.antMatchers( "/**").permitAll()
		.antMatchers(HttpMethod.GET, "/**").permitAll()
		.antMatchers(HttpMethod.POST, "/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/**").permitAll()
		.antMatchers(HttpMethod.DELETE, "/**").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "*").permitAll().and().cors().configurationSource(corsConfigurationSource()).and().csrf().disable();
		
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOriginPatterns(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
	    configuration.setAllowCredentials(true);
	    configuration.setAllowedHeaders(Arrays.asList(
	            "Accept", "Origin", "Content-Type", "Depth", "User-Agent", "If-Modified-Since,",
	            "Cache-Control", "Authorization", "X-Req", "X-File-Size", "X-Requested-With", "X-File-Name"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return (CorsConfigurationSource) source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
	    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return bean;
	}
	
}
