package fr.excilys.service;

import fr.excilys.Validator.ValidatorUser;
import fr.excilys.model.User;
import fr.excilys.persistence.UserDAO;

public class ServiceUser {
	
	UserDAO userDAO;

	public ServiceUser(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public boolean testMdp(User userNotVerif) {
		User userVerif = userDAO.getUser(userNotVerif.getName()).get();
		ValidatorUser validatorUser = new ValidatorUser();
		
		return(validatorUser.testMdp(userNotVerif, userVerif));
		
	}

}
