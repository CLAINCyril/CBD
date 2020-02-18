package fr.excilys.cclain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import fr.excilys.cclain.modele.Company;

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
				.setId(res.getInt("company_id")).build();
		return Optional.ofNullable(company);
	}

}
