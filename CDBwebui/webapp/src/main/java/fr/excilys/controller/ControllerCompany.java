package fr.excilys.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.excilys.DTO.CompanyDTO;
import fr.excilys.model.Company;
import fr.excilys.service.ServiceCompany;
import fr.excilys.service.ServiceComputer;


@RestController
@RequestMapping(value = "/companys")
public class ControllerCompany {
	
	

	ServiceCompany serviceCompany;
	ServiceComputer serviceComputer;

	public ControllerCompany(ServiceCompany serviceCompany) {
		this.serviceCompany = serviceCompany;
	}
	
	@GetMapping
	public ResponseEntity<List<Company>> getCompany(){

		List<Company> companys= serviceCompany.getAllCompany();
		List<CompanyDTO> companysDTO = serviceCompany.fromCompanyListToDTOList(companys);
		
		ResponseEntity<List<Company>> responsEntity = 
				new ResponseEntity<List<Company>>(companys,HttpStatus.OK);
		
		return responsEntity;
 }

	
	@PostMapping
	public void createCompany(@RequestBody CompanyDTO companyDTO ){

		Company company = serviceCompany.mapCompanyDTOTOCompany(companyDTO);
		serviceCompany.persisteCompany(company);

	}	
	
	@PutMapping("/{id}")
	public void updateCompany(@RequestBody CompanyDTO companyDTO ){
		Company company = serviceCompany.mapCompanyDTOTOCompany(companyDTO);

		serviceCompany.updateCompany(company);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteCompany(@PathVariable("id") String id){
		Company company = new Company(Integer.valueOf(id));
		serviceCompany.deleteCompany(company);

		return ResponseEntity.ok(null);

	}
}
