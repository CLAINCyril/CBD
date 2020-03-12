package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.stereotype.Component;

import DTO.CompanyDTO;
import modele.Company;

@Component
public class CompanyMapper implements RowMapper<Company>{

	Company company;

	public CompanyMapper() {

	}

	
	public Optional<Company> getCompany(ResultSet res) throws SQLException {
		company = new Company.CompanyBuilder().setName(res.getString("company.name"))
				.setId(res.getInt("company.id")).build();
		return Optional.ofNullable(company);
	}

	public List<String> convertIdlistfromInteger(List<Integer> allCompanyid) {
		List<String> listString = new ArrayList<String>();
		for (Integer integer : allCompanyid) {
			listString.add(integer.toString());
		}
		return listString;
	}
	
	public static CompanyDTO convertFromCompanyToCompanyDTO(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		return companyDTO;
	}

	public List<CompanyDTO> convertToCompanyDTO(List<Company> allCompanyid) {
		List<CompanyDTO> listCompanyDTO = new ArrayList<CompanyDTO>();
		for (Company company : allCompanyid) {
			listCompanyDTO.add(convertFromCompanyToCompanyDTO(company));
		}
		return listCompanyDTO;
	}

	public Company fromCompanyDTOToCompany(CompanyDTO company2) {
		Company company;
		company = new Company.CompanyBuilder().setId(company2.getId()).build();

		
		return company;
	}


	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return getCompany(rs).get();
	}

}
