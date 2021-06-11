package com.flyhub.ideamanagementsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.flyhub.ideamanagementsystem.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
		}
	
	//It has to be CustomUserDetailsService
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/v1/auth/**").permitAll()
			.antMatchers("/api/v1/test/**").permitAll()
			.antMatchers("/api/v1/gender").permitAll()
			.antMatchers("/api/v1/prefix").permitAll()
			.antMatchers("/api/v1/postfix").permitAll()
			.antMatchers("/api/v1/category").permitAll()
			.antMatchers("/api/v1/priority").permitAll()
			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
		
	}
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//		
//	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
		
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/").authenticated()
//			.antMatchers("/list_users").authenticated()
//			.antMatchers("/list_ideas").authenticated()
//			.antMatchers("/list_notes").authenticated()
//			.antMatchers("/documents").authenticated()
//			.antMatchers("/notes").authenticated()
//			.antMatchers("/ideas").authenticated()
//			.anyRequest().permitAll()
//			.and()
//			.formLogin()
//				.usernameParameter("email")
//				.defaultSuccessUrl("/list_users")
//				.permitAll()
//			.and()
//			.logout().logoutSuccessUrl("/").permitAll();
//		
//	}
	


//	
//	@Override  
//	protected void configure(HttpSecurity http) throws Exception { 
//		http.cors().and().csrf().disable().authorizeRequests()
//		.anyRequest().permitAll()
////		.antMatchers("/delete/**").hasAuthority("Admin") 
////		.antMatchers("/edit/**").hasAnyAuthority("Admin", "User")
//		//.anyRequest().authenticated()  
//		//.and() 
//		//.formLogin().permitAll() 
//		//.and()
//		//.logout().permitAll()
//		.and() 
//		.exceptionHandling().accessDeniedPage("/403")
//		; 
//	}
	
}
