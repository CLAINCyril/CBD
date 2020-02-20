package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	public Company getCompany(ResultSet res) {
		try {
			company = new Company.CompanyBuilder().setId(res.getInt("company.id")).
					setName(res.getString("company.name")).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

}