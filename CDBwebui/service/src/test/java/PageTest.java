import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.excilys.configuration.PersistenceConfig;
import fr.excilys.model.Computer;
import fr.excilys.service.Page;
import fr.excilys.service.ServiceComputer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@Transactional
public class PageTest extends Mockito{
	
	
	@Autowired
	ServiceComputer serviceComputer;

	@Test
	public void getPageoffTwentyComputerExpected() {
		int pageIterator = 0;
		int taillePage = 20;


		List<Computer> computers = new Page(pageIterator, taillePage, serviceComputer).getPage();
		assertEquals(computers.size(), 20);
		}
}
