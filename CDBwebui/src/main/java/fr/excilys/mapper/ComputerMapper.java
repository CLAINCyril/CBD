package fr.excilys.mapper;

import fr.excilys.modele.Company;
import fr.excilys.modele.Computer;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.DTO.ComputerDTO;

import java.sql.ResultSet;

@Component
public class ComputerMapper implements RowMapper<Computer>{
	
	Computer computer;
	Company company;
	CompanyMapper companyMapper;
	public ComputerMapper(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;

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

	public  ComputerDTO convertFromComputerToComputerDTO(Computer computer) {
		CompanyDTO companyDTO = new CompanyDTO();
		if (computer.getCompany() != null) {
			companyDTO = companyMapper.convertFromCompanyToCompanyDTO(computer.getCompany());

		}
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
		Company company = new CompanyMapper().fromCompanyDTOToCompany(computerDTO.getCompany());
		
		Computer computer = new Computer.ComputerBuilder().setCompany(company)
				.setId(computerDTO.getId())
				.setDiscontinued(ConvertStringToLocalDateTime(computerDTO.getDiscontinued()))
				.setIntroduced(ConvertStringToLocalDateTime(computerDTO.getIntroduced()))
				.setName(computerDTO.getName())
				.build();
		return computer;
	}

	public List<Integer> stringToIntegers(List<String> listIdComputer) {
		List<Integer> Numbers = listIdComputer.stream()
				.map(Integer::valueOf)
				.collect(Collectors.toList());
		return Numbers;
	}

	@Override
	public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		return 	getComputer(resultSet).get();
	}


}
