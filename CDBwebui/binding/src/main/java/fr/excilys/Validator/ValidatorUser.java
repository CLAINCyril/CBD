package fr.excilys.Validator;

import fr.excilys.model.User;

public class ValidatorUser {

	public boolean testMdp(User userNotVerif, User userVerif) {
		return((userVerif.getPassword()).equals(userNotVerif.getPassword()));
		
	}
}
