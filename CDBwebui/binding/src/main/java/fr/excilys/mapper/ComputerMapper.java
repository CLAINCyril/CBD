package fr.excilys.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.DTO.ComputerDTO;
import fr.excilys.model.Company;
import fr.excilys.model.Computer;

@Component
public class ComputerMapper {
	
	Computer computer;
	Company company;
	CompanyMapper companyMapper;
	public ComputerMapper(CompanyMapper companyMapper) {
		this.companyMapper = companyMapper;

	}


	public  ComputerDTO convertFromComputerToComputerDTO(Computer computer) {
		CompanyDTO companyDTO = new CompanyDTO();
		if (computer.getCompany() != null) {
			companyDTO = companyMapper.convertFromCompanyToCompanyDTO(computer.getCompany());

		}
		ComputerDTO compDTO = new ComputerDTO( computer.getName(),
				computer.getIntroduced()==null?null:computer.getIntroduced().toString(),
				computer.getDiscontinued()==null?null:computer.getDiscontinued().toString(),companyDTO);
		compDTO.setId(Integer.toString(computer.getId()));
		return compDTO;
	}

	/**
	 * Transform un type String en type LocalDatetime.
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDate ConvertStringToLocalDate(String date) {
		if ((date != null) && !date.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateTime = LocalDate.parse(date, formatter);
			return dateTime;
		} else {
			return null;
		}
	}
	
	

	public Computer fromComputerDTOToComputer(ComputerDTO computerDTO) {
		Company company = new CompanyMapper().fromCompanyDTOToCompany(computerDTO.getCompany());

		Computer computer = new Computer.ComputerBuilder().setCompany(company)
				.setId(computerDTO.getId() == null ? 0 : Integer.parseInt(computerDTO.getId()))
				.setDiscontinued(computerDTO.getDiscontinued() == null?
						null : ConvertStringToLocalDate(computerDTO.getDiscontinued()))
				.setIntroduced(computerDTO.getIntroduced() == null ?
						null : ConvertStringToLocalDate(computerDTO.getIntroduced()))
				.setName(computerDTO.getName())
				.build();
		return computer;
	}

	public List<Integer> stringToIntegers(List<String> listIdComputer) {
		List<Integer> numbers = listIdComputer.stream()
				.map(Integer::valueOf)
				.collect(Collectors.toList());
		return numbers;
	}


}
