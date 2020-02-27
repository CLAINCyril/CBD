package Validator;

import java.time.LocalDateTime;

public class ValidatorComputer {

	public boolean discontinuedAfterIntroduced(LocalDateTime discontinued, LocalDateTime introduced) {
		
		return discontinued.isAfter(introduced);
	}

}
