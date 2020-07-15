package fr.excilys.validator;

import java.time.LocalDate;

import fr.excilys.exception.DateException;
import fr.excilys.model.Computer;

public class ValidatorComputer {
	
	Computer computer;
	

	public ValidatorComputer(Computer computer) {
		this.computer = computer;
	}

	public boolean isValideComputer() throws DateException {
		return(discontinuedAfterIntroduced(this.computer.getDiscontinued(), this.computer.getIntroduced()));
	}
	
	private boolean discontinuedAfterIntroduced(LocalDate discontinued, LocalDate introduced) throws DateException {
		if (!discontinued.isAfter(introduced)) {

			throw new DateException("discontinued must be after introduced");
		}
		return true;
	}
}