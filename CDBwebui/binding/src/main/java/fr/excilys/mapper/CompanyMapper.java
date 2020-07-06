package fr.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.model.Company;

@Component
public class CompanyMapper {

	Company company;

	public CompanyMapper() {
		super();
	}


	public List<String> convertIdlistfromInteger(List<Integer> allCompanyid) {
		List<String> listString = allCompanyid.stream().map(Integer -> Integer.toString()).collect(Collectors.toList());

		return listString;
	}

	public CompanyDTO convertFromCompanyToCompanyDTO(Company company) {
		System.out.println(company);
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(Integer.toString((company.getId())));
		companyDTO.setName(company.getName());
		return companyDTO;
	}

	public List<CompanyDTO> convertToCompanyDTO(List<Company> allCompany) {
		List<CompanyDTO> listCompanyDTO = allCompany.stream().map(company -> convertFromCompanyToCompanyDTO(company))
				.collect(Collectors.toList());

		return listCompanyDTO;
	}

	public Company fromCompanyDTOToCompany(CompanyDTO companyDTO) {
		return new Company.CompanyBuilder()
				.setId(companyDTO.getId() == null ? 0 : Integer.valueOf(companyDTO.getId()))
				.setName(companyDTO.getName().toString())
				.build();

		
	}

}
