package Validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;
import org.mockito.Mockito;

import fr.excilys.Validator.ValidatorComputer;
import fr.excilys.serviceException.DateException;

public class ValidatorTest extends Mockito{
	
	
	@Test
	public void discontinuedAfterIntroducedExpectedTrue(){
		
	ValidatorComputer validatorComputer = new ValidatorComputer();
	
	boolean discontinuedAfterIntroduced;
	
	try {
		validatorComputer.discontinuedAfterIntroduced(LocalDateTime.now().plusDays(1), LocalDateTime.now());
		discontinuedAfterIntroduced = true;
	} catch (DateException dateException) {
		discontinuedAfterIntroduced= false;
	}
	assertTrue(discontinuedAfterIntroduced);
	}
	
	@Test
	public void discontinuedBeforeIntroducedExpectedFalse(){
		
	ValidatorComputer validatorComputer = new ValidatorComputer();

	
	boolean discontinuedAfterIntroduced;
	
	try {
		validatorComputer.discontinuedAfterIntroduced(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
		discontinuedAfterIntroduced = true;
	} catch (DateException dateException) {
		discontinuedAfterIntroduced= false;
	}
	assertFalse(discontinuedAfterIntroduced);
	}
	
}
