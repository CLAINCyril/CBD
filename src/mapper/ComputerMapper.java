package mapper;

import modele.Company;
import modele.Computer;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ComputerMapper {
	Computer computer;
	Company company;
	private static volatile ComputerMapper instance = null;

	private ComputerMapper() {

	}

	public final static ComputerMapper getInstance() {
		if (ComputerMapper.instance == null) {
			synchronized (CompanyMapper.class) {
				if (ComputerMapper.instance == null) {
					ComputerMapper.instance = new ComputerMapper();
				}
			}
		}
		return ComputerMapper.instance;
	}

	public Computer getComputer(ResultSet resDetailcomputer) throws SQLException {
		if (resDetailcomputer.next()) {
			company = new Company.CompanyBuilder().setName(resDetailcomputer.getString("company.name"))
					.setId(resDetailcomputer.getInt("company_id")).build();
			computer = new Computer.ComputerBuilder().setCompany(company).setId(resDetailcomputer.getInt("computer.id"))
					.setName(resDetailcomputer.getString("computer.name"))
					.setIntroduced(resDetailcomputer.getTimestamp("computer.introduced") != null
							? resDetailcomputer.getTimestamp("computer.introduced").toLocalDateTime()
							: null)
					.setDiscontinued(resDetailcomputer.getTimestamp("discontinued") != null
							? resDetailcomputer.getTimestamp("computer.discontinued").toLocalDateTime()
							: null)
					.build();
		}

		return computer;
	}
}
