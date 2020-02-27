import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import modele.Computer;
import service.Page;

public class PageTest extends Mockito{
	
	@Test
	public void getPageoffTwentyComputerExpected() {
		int pageIterator = 0;
		int taillePage = 20;
		List<Computer> computers = new Page().getPage(pageIterator,
				taillePage);
		assertEquals(computers.size(), 20);
		}
}
