package mapper;

import modele.Company;
import modele.Computer;
import java.sql.SQLException;
import java.util.Optional;

import DTO.CompanyDTO;
import DTO.ComputerDTO;

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

	public Optional<Computer> getComputer(ResultSet resDetailcomputer) throws SQLException {
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

		
		return Optional.ofNullable(computer);

	}

	public static  ComputerDTO convertFromComputerToComputerDTO(Computer computer) {
		System.out.println(computer);
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(computer.getCompany().getId());
		companyDTO.setName(computer.getCompany().getName());

		ComputerDTO compDTO = new ComputerDTO( computer.getName(),
				computer.getIntroduced()==null?null:computer.getIntroduced().toString(),
				computer.getDiscontinued()==null?null:computer.getDiscontinued().toString(),companyDTO);
		return compDTO;
	}


}
