package fr.excilys.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.excilys.DTO.UserDTO;
import fr.excilys.Validator.ValidatorUser;
import fr.excilys.model.User;
import fr.excilys.model.User.Builder;
import fr.excilys.persistence.UserDAO;

@Service
public class ServiceUser implements UserDetailsService {

	UserDAO userDAO;


	public ServiceUser(UserDAO userDAO) {
		this.userDAO = userDAO;

	}

	@Transactional
	public boolean testMdp(User userNotVerif) {
		User userVerif = userDAO.getUser(userNotVerif.getName()).get();
		ValidatorUser validatorUser = new ValidatorUser();

		return (validatorUser.testMdp(userNotVerif, userVerif));

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userDAO.getUser(username);
		if (optionalUser.isPresent()) {
			
			User user = userDAO.getUser(username).get();
						
			return (UserDetails)user;
		} else {
			throw new UsernameNotFoundException("not found username: " + username);
		}

	}

	public User registerNewUserAccountUser(UserDTO newUserDto) {
		User user = new User(new Builder()
				.name(newUserDto.getName())
				.password((newUserDto.getPassword()))
				.role(newUserDto.getRole()));
		userDAO.persist(user);
		return user;
	}

}
