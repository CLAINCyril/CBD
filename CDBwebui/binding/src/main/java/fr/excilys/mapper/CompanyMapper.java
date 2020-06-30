package fr.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.model.Company;

@Component
public class CompanyMapper implements RowMapper<Company> {

	Company company;

	public CompanyMapper() {
		super();
	}

	public Optional<Company> getCompany(ResultSet res) throws SQLException {
		company = new Company.CompanyBuilder().setName(res.getString("company.name")).setId(res.getInt("company.id"))
				.build();
		return Optional.ofNullable(company);
	}

	public List<String> convertIdlistfromInteger(List<Integer> allCompanyid) {
		List<String> listString = allCompanyid.stream().map(Integer -> Integer.toString()).collect(Collectors.toList());

		return listString;
	}

	public CompanyDTO convertFromCompanyToCompanyDTO(Company company) {
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

	@Override
	public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		return getCompany(resultSet).get();
	}

}
