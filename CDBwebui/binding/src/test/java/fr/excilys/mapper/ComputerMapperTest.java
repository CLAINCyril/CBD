package fr.excilys.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.Test;
import org.mockito.Mock;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.DTO.ComputerDTO;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;


public class ComputerMapperTest {

	Company company = new Company
			.CompanyBuilder()
			.setId(1)
			.setName("toto")
			.build();
	
	LocalDate introduced = LocalDate.of(2020,04,05);
	LocalDate discontinued = LocalDate.of(2021,04,05);
	
	Computer computer = new Computer.ComputerBuilder()
			.setCompany(company)
			.setId(1)
			.setDiscontinued(discontinued)
			.setIntroduced(introduced)
			.setName("toto")
			.build();
	
	CompanyDTO companyDto = new CompanyDTO();

	ComputerDTO computerDto = new ComputerDTO("1",
			"toto", "2020-04-05T05:06", "2021-04-05T05:06", companyDto);
	
	@Mock 
	CompanyMapper companyMapper;

	ComputerMapper computerMapper = new ComputerMapper(companyMapper);
	
	@Test
	public void assertNullConvertStringToLocalDate() {
		assertNull(computerMapper.ConvertStringToLocalDate(null));
	}

	@Test
	public void assertNotNullConvertStringToLocalDate() {
		assertNotNull(computerMapper.ConvertStringToLocalDate("2020-06-05T05:06"));
	}
	
	@Test
	public void convertStringToLocalDate() {
		LocalDate date = LocalDate.of(2020, 06, 05);
		assertEquals(date, computerMapper.ConvertStringToLocalDate("2020-06-05T05:06"));
	}
	
	
	@Test
	public void convertFromComputerToComputerDto() {
		companyDto.setId("1");
		companyDto.setName("toto");
		
		assertEquals(computerDto,
				computerMapper.convertFromComputerToComputerDTO(computer));
	}
	
}
