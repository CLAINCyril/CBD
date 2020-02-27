package Validator;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import modele.Computer;

public class ValidatorTest extends Mockito{
	Computer computer;
	
	@Before
	public void init() {
		
		computer = mock(Computer.class);
	}

	@Test
	public void discontinuedAfterIntroducedExpectedTrue(){
	ValidatorComputer validatorComputer = new ValidatorComputer();
	when(computer.getDiscontinued()).thenReturn(LocalDateTime.now().plusDays(1));
	when(computer.getIntroduced()).thenReturn(LocalDateTime.now());

	assertTrue(validatorComputer.discontinuedAfterIntroduced(computer.getDiscontinued(), computer.getIntroduced()));
	}
}
