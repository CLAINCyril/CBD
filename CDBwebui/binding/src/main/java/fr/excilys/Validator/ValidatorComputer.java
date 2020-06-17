package fr.excilys.Validator;

import java.time.LocalDateTime;

import fr.excilys.serviceException.DateException;

public class ValidatorComputer {

	
	public void discontinuedAfterIntroduced(LocalDateTime discontinued, LocalDateTime introduced) throws DateException {
		Boolean dateOk = discontinued.isAfter(introduced);
			if(dateOk.equals(false)){
				
				throw new DateException("discontinued must be after introduced");
				}
			}
}