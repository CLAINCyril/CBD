package mapper;

import modele.Company;
import modele.Computer;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
			computer = new Computer.ComputerBuilder().setCompany(company)
					.setId(resDetailcomputer.getInt("computer.id"))
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
		CompanyDTO companyDTO = CompanyMapper.getInstance().convertFromCompanyToCompanyDTO(computer.getCompany());
		ComputerDTO compDTO = new ComputerDTO( computer.getName(),
				computer.getIntroduced()==null?null:computer.getIntroduced().toString(),
				computer.getDiscontinued()==null?null:computer.getDiscontinued().toString(),companyDTO);
		compDTO.setId(computer.getId());
		return compDTO;
	}

	/**
	 * Transform un type String en type LocalDatetime.
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime ConvertStringToLocalDateTime(String date) {
		if (date.isEmpty()) {
			return null;
		}
		System.out.println(date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime datetime = LocalDate.parse(date, formatter).atTime(0, 0, 0);
		return datetime;
	}
	
	

	public Computer fromComputerDTOToComputer(ComputerDTO computerDTO) {
		Company company = CompanyMapper.getInstance().fromCompanyDTOToCompany(computerDTO.getCompany());
		
		Computer computer = new Computer.ComputerBuilder().setCompany(company)
				.setId(computerDTO.getId())
				.setDiscontinued(ConvertStringToLocalDateTime(computerDTO.getDiscontinued()))
				.setIntroduced(ConvertStringToLocalDateTime(computerDTO.getIntroduced()))
				.setName(computerDTO.getName())
				.build();
		return computer;
	}


}
