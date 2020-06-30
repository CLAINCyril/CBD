import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.excilys.model.Computer;
import fr.excilys.service.Page;
import fr.excilys.service.ServiceComputer;

public class PageTest{
	
	List<Computer> testComputers = new ArrayList<Computer>();
	ServiceComputer serviceComputer = Mockito.mock(ServiceComputer.class);;

	@Test
	public void getPageoffTwentyComputerExpected() {
		int pageIterator = 0;
		int taillePage = 20;
		for (int i = 0; i < 20; i++) {
			testComputers.add(new Computer());
			
		}
		Mockito.when(serviceComputer.getPageComputer(pageIterator, taillePage))
		.thenReturn(testComputers);

		
		List<Computer> computers = new Page(pageIterator, taillePage, serviceComputer).getPage();
		assertEquals(computers.size(), 20);
		}
}
