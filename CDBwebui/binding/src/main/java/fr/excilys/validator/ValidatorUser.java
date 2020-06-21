package fr.excilys.Validator;

import fr.excilys.model.UserCbd;;

public class ValidatorUser {

	public boolean testMdp(UserCbd userNotVerif, UserCbd userVerif) {
		return((userVerif.getPassword()).equals(userNotVerif.getPassword()));
		
	}
}
