package fr.excilys.controller;

import java.util.Objects;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.excilys.DTO.UserDTO;
import fr.excilys.jwtToken.JwtTokenUtil;
import fr.excilys.service.ServiceUser;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/login")
public class LoginController {

	private JwtTokenUtil jwtTokenUtil;
	private ServiceUser usersService;

	public LoginController(ServiceUser usersService, JwtTokenUtil jwtTokenUtil) {
		this.usersService = usersService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@PostMapping
	public ResponseEntity<?> login(@RequestBody UserDTO authentificationRequest) throws AuthenticationException {
		authenticate(authentificationRequest.getName(), authentificationRequest.getPassword());
		final UserDetails userDetails = usersService.loadUserByUsername(authentificationRequest.getName());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(token);
	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	private void authenticate(String username, String password) throws AuthenticationException {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			 UserDetails userDetails = usersService.loadUserByUsername(username);
			 if(password.equals(userDetails.getPassword())) {
				 throw new AuthenticationException("INVALID_CREDENTIALS" );
			 }
		} catch (UsernameNotFoundException usernameNotFoundException) {
			throw new UsernameNotFoundException("USER_DISABLED" );
		}
	}

}