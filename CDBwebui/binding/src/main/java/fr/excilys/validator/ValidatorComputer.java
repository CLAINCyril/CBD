package fr.excilys.validator;

import java.time.LocalDateTime;

import fr.excilys.exception.DateException;

public class ValidatorComputer {

	public boolean discontinuedAfterIntroduced(LocalDateTime discontinued, LocalDateTime introduced) throws DateException {
		if (!discontinued.isAfter(introduced)) {

			throw new DateException("discontinued must be after introduced");
		}
		return true;
	}
}