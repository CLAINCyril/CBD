import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import fr.excilys.modele.Computer;
import fr.excilys.service.Page;
import fr.excilys.service.ServiceComputer;

public class PageTest extends Mockito{
	
	
	@Mock
	ServiceComputer serviceComputer;	
	
	@Test
	public void getPageoffTwentyComputerExpected() {
		int pageIterator = 0;
		int taillePage = 20;
		List<Computer> computers = new Page(pageIterator, taillePage, serviceComputer).getPage();
		assertEquals(computers.size(), 20);
		}
}
