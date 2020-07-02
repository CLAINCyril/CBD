package fr.excilys.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.excilys.jwtToken.JwtTokenAuthorizationOncePerRequestFilter;
import fr.excilys.jwtToken.JwtUnAuthorizedResponseAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserDetailsService serviceUser;
	private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;
	private JwtTokenAuthorizationOncePerRequestFilter jwtTokenAuthorization;

	@Autowired
	WebSecurityConfig(UserDetailsService userService,
			JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint,
			JwtTokenAuthorizationOncePerRequestFilter jwtTokenAuthorization) {
		this.serviceUser = userService;
		this.jwtUnAuthorizedResponseAuthenticationEntryPoint = jwtUnAuthorizedResponseAuthenticationEntryPoint;
		this.jwtTokenAuthorization = jwtTokenAuthorization;

	}	

	@Autowired
	public void configureAuthenticationManagerBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		authenticationManagerBuilder.userDetailsService(serviceUser).passwordEncoder(passwordEncoder());
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
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(serviceUser);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().anyRequest()
				.authenticated();

		http.addFilterBefore(jwtTokenAuthorization, UsernamePasswordAuthenticationFilter.class);

		http.headers().frameOptions().sameOrigin().cacheControl();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
            .antMatchers(
                HttpMethod.POST,
                "/login"
            )
            .antMatchers(HttpMethod.OPTIONS, "/**");
	}
}