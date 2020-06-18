package fr.excilys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.excilys.DTO.UserDTO;
import fr.excilys.model.UserCbd;
import fr.excilys.model.UserCbd.Builder;
import fr.excilys.persistence.UserDAO;

@Service
public class ServiceUser implements UserDetailsService {

	UserDAO userDAO;

	public ServiceUser(UserDAO userDAO) {
		this.userDAO = userDAO;

	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCbd> optionalUser = userDAO.getUser(username);
		if (optionalUser.isPresent()) {
			
			UserCbd userCbd = userDAO.getUser(username).get();
			List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userCbd.getRole());
			grantList.add(authority);
			UserDetails userDetails = new User(userCbd.getName(),userCbd.getPassword(),grantList);
						
			return userDetails;
		} else {
			throw new UsernameNotFoundException("not found user: " + username);
		}

	}

	
	public UserCbd registerNewUserAccountUser(UserDTO newUserDto) {
		UserCbd userCbd = new UserCbd(new Builder()
				.name(newUserDto.getName())
				.password((newUserDto.getPassword()))
				.role(newUserDto.getRole()));
		userDAO.persist(userCbd);
		return userCbd;
	}

}
