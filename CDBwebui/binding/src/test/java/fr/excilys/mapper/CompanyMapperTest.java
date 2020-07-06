package fr.excilys.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.model.Company;

public class CompanyMapperTest {
	
	Company company = new Company
			.CompanyBuilder()
			.setId(1)
			.setName("toto")
			.build();
	List<Company> companies = new ArrayList<Company>();
	CompanyDTO companyDto = new CompanyDTO();
	CompanyMapper companyMapper = new CompanyMapper();
	
	@Test
	public void convertIdStringFromInteger() {
		List<Integer> integers = new ArrayList<Integer>();
		integers.add(1);
		
		assertEquals(integers.stream().map(Integer -> Integer.toString()).collect(Collectors.toList())
				, companyMapper.convertIdlistfromInteger(integers));
	}
	
	@Test
	public void convertDtoToCompany() {
		companyDto.setId("1");
		companyDto.setName("toto");
		
		assertEquals(company, companyMapper.fromCompanyDTOToCompany(companyDto));
	}
	
	@Test
	public void convertCompanyToDto() {
		companyDto.setId("1");
		companyDto.setName("toto");
		assertEquals(companyDto, companyMapper.convertFromCompanyToCompanyDTO(company));
	}
	
	@Test
	public void assertConvertToCompanyDTOListReturnVoidList() {
		
		assertTrue(companyMapper.convertToCompanyDTO(companies).isEmpty());
	}
	
	@Test
	public void assertConvertToCompanyDTOListHaveNoDupplicatedItem() {
		companies.add(company);
		assertEquals(1, companyMapper.convertToCompanyDTO(companies).size());
	}
	
	@Test
	public void convertToCompanyDTOList() {
		companies.add(company);
		companyDto.setId("1");
		companyDto.setName("toto");
		
		assertTrue(companyMapper
				.convertToCompanyDTO(companies)
				.contains(companyDto));
	}
}
