package fr.excilys.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.excilys.service.ServiceUser;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ServiceUser serviceUser;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureAuthenticationManagerBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		
		authenticationManagerBuilder.userDetailsService(serviceUser).passwordEncoder(passwordEncoder());
		authenticationManagerBuilder.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("toto")).roles("USER");
		authenticationManagerBuilder.inMemoryAuthentication().withUser("admin1").password(passwordEncoder().encode("12345")).roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
		.csrf()
		.disable();

		httpSecurity
		.authorizeRequests()
		.antMatchers("/", "/login", "/acceuil","/registerPage")
		.permitAll();

		httpSecurity
		.authorizeRequests()
		.antMatchers("/ListComputer")
		.access("hasAnyRole('USER', 'ADMIN')");

		httpSecurity
		.authorizeRequests()
		.antMatchers("/EditComputer", "/AddComputer")
		.access("hasRole('ADMIN')");

		httpSecurity.authorizeRequests().and().formLogin()
				.loginProcessingUrl("/j_spring_security_check")
				.loginPage("/login")
				.defaultSuccessUrl("/ListComputer")
				.failureUrl("/login?error=true")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/home");
	}

}