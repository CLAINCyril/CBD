package fr.excilys.validator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;

import org.junit.Test;
import org.mockito.Mockito;

import fr.excilys.exception.DateException;
import fr.excilys.validator.ValidatorComputer;

public class ValidatorTest extends Mockito {

	@Test
	public void discontinuedAfterIntroducedExpectedTrue() {

		ValidatorComputer validatorComputer = new ValidatorComputer();

		try {
			assertTrue(validatorComputer.discontinuedAfterIntroduced(LocalDateTime.now().plusDays(1),
					LocalDateTime.now()));
		} catch (DateException dateException) {
			fail("test should not fail");
		}
	}

	@Test(expected = DateException.class)
	public void discontinuedBeforeIntroducedExpectedThrow() {

		ValidatorComputer validatorComputer = new ValidatorComputer();

		try {
			validatorComputer.discontinuedAfterIntroduced(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
		} catch (DateException dateException) {
			fail("test should fail");
		}
	}

}
