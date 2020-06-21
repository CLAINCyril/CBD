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
import fr.excilys.model.UserCdb;
import fr.excilys.model.UserCdb.Builder;
import fr.excilys.persistence.DAOComputer;
import fr.excilys.persistence.DAOUser;

@Service
public class ServiceUser implements UserDetailsService {

	DAOUser userDAO;

	public ServiceUser(DAOUser userDAO) {
		this.userDAO = userDAO;

	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCdb> optionalUser = userDAO.getUser(username);
		if (optionalUser.isPresent()) {
			
			UserCdb userCbd = userDAO.getUser(username).get();
			List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userCbd.getRole());
			grantList.add(authority);
			UserDetails userDetails = new User(userCbd.getName(),userCbd.getPassword(),grantList);
						
			return userDetails;
		} else {
			throw new UsernameNotFoundException("not found user: " + username);
		}

	}

	
	public UserCdb registerNewUserAccountUser(UserDTO newUserDto) {
		UserCdb userCbd = new UserCdb(new Builder()
				.name(newUserDto.getName())
				.password((newUserDto.getPassword()))
				.role(newUserDto.getRole()));
		System.out.println(" juste avant persist  :  "+userCbd);
		userDAO.persist(userCbd);
		return userCbd;
	}

}
