package fr.excilys.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.excilys.DTO.UserDTO;
import fr.excilys.model.UserCbd;
import fr.excilys.service.ServiceUser;

@Controller
public class ControllerUser {

	private PasswordEncoder passwordEncoder;
	
	private ServiceUser serviceUser;
	
	public ControllerUser(ServiceUser serviceUser, PasswordEncoder passwordEncoder) {
		this.serviceUser = serviceUser;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping(value = { "", "/login", "/home" })
	public String home() {
		return "login";
	}

	@GetMapping(value = "/registerPage")
	public String register(Model model) {
		return "register";
	}

	@PostMapping(value = "/register")
	public String register(@ModelAttribute("newUser") UserDTO newUserDto) {
		// voir code review bonne pratique 
		newUserDto.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
		UserCbd userCreated = serviceUser.registerNewUserAccountUser(newUserDto);
		return "loginPage";
	}
}
