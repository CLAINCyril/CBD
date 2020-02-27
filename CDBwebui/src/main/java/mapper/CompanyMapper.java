package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import DTO.CompanyDTO;
import modele.Company;

public class CompanyMapper {

	Company company;
	private static volatile CompanyMapper instance = null;

	private CompanyMapper() {

	}

	public final static CompanyMapper getInstance() {
		if (CompanyMapper.instance == null) {
			synchronized (CompanyMapper.class) {
				if (CompanyMapper.instance == null) {
					CompanyMapper.instance = new CompanyMapper();
				}
			}
		}
		return CompanyMapper.instance;
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
	

}
