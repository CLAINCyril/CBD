package fr.excilys.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.excilys.service.ServiceUser;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	ServiceUser serviceUser;
	 
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
        // For User in database.
        auth.userDetailsService(serviceUser);
 
    }

	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
		.csrf()
		.disable();

		httpSecurity
		.authorizeRequests()
		.antMatchers("/", "/login", "/acceuil")
		.permitAll();

		httpSecurity
		.authorizeRequests()
		.antMatchers("/ListComputer")
		.access("hasAnyRole('USER', 'ADMIN')");

		httpSecurity
		.authorizeRequests()
		.antMatchers("/ListComputer", "/EditComputer", "/AddComputer")
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