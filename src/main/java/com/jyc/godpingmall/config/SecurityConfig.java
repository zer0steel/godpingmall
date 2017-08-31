package com.jyc.godpingmall.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and()
		.authorizeRequests().antMatchers("/my/**").authenticated().and()
		.authorizeRequests().antMatchers("/login").anonymous().and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/main").and()
		.logout().logoutUrl("/logout").and()
		.csrf().and()
		.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication()
		.withUser("admin").password("1234").roles("ADMIN").and()
		.withUser("user").password("1234").roles("USER");
	}

	
}
