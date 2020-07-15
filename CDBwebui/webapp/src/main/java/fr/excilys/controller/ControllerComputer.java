package fr.excilys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.excilys.DTO.ComputerDTO;
import fr.excilys.DTO.ListComputerParameter;
import fr.excilys.exception.DateException;
import fr.excilys.model.Computer;
import fr.excilys.service.ServiceComputer;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/computers")
public class ControllerComputer {

	
	public ServiceComputer serviceComputer;

	public ControllerComputer(ServiceComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}
	
	@GetMapping
	public ResponseEntity getDashboard(ListComputerParameter listComputerParameter) {
		List<Computer> computerList = new ArrayList<Computer>();
		testNonNulParameter(listComputerParameter);
		long nbComputer = serviceComputer.countWithSearch(listComputerParameter.getSearch()); 
		computerList = serviceComputer.getPage(listComputerParameter);
		List<ComputerDTO> computerDTOList = serviceComputer.mapComputerToDTOList(computerList);
		List<Object> listResult = new ArrayList();
		listResult.add(nbComputer);
		listResult.add(computerDTOList);

		ResponseEntity<List<ComputerDTO>> responseEntitycomputerDTOList = 
				new ResponseEntity(listResult,HttpStatus.OK);


		return responseEntitycomputerDTOList;

	}
	

	@GetMapping("/{id}")
	public ResponseEntity getComputer(@PathVariable String id) {
		
		Optional<Computer> Optionalcomputer = serviceComputer.getComputer(Integer.valueOf(id));		
		Optional<ComputerDTO> computerDTO = Optionalcomputer.map(computer ->  serviceComputer.mapFromComputerToDTO(computer));
		return ResponseEntity.of(computerDTO);


	}
	
	
	@PostMapping
	public ResponseEntity createComputer(@RequestBody ComputerDTO computerDTO ){
		Computer computer  = serviceComputer.mapComputerDTOToComputer(computerDTO);
		try {
			serviceComputer.persisteComputer(computer);
			return new ResponseEntity(HttpStatus.CREATED);
		}
		catch (DateException dateException) {
			return new ResponseEntity(dateException.getMessage(),HttpStatus.BAD_REQUEST);

		}
		
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity updateComputer(@RequestBody ComputerDTO computerDTO ){
		try {

			Computer computer  = serviceComputer.mapComputerDTOToComputer(computerDTO);
			serviceComputer.updateComputer(computer);
			return new ResponseEntity(HttpStatus.ACCEPTED);
		}
		catch (DateException dateException) {
			return new ResponseEntity(dateException.getMessage(),HttpStatus.BAD_REQUEST);

		}
		


	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteComputer(@PathVariable("id") String id){
		serviceComputer.getComputer(Integer.valueOf(id))
		.ifPresent(computer -> serviceComputer.deleteComputer(computer.getId()));
		

		return new ResponseEntity(HttpStatus.NO_CONTENT);

	}
	
	
	@DeleteMapping()
	public ResponseEntity deleteComputers(@RequestParam(value="ids", required = false) String ids){
		List<String> listDeleteComputer = Arrays.asList(ids.split(","));
		serviceComputer.deleteComputerList(listDeleteComputer);
		return new ResponseEntity(HttpStatus.NO_CONTENT);

	}
	
	
	private List<String> fromStringToList(String ids) {
		List<String> listString = new ArrayList<String>();
		
		return null;
	}

	public void testNonNulParameter(ListComputerParameter listComputerParameter) {
		if(listComputerParameter.getPageIterator().isEmpty()) {
			listComputerParameter.setPageIterator("0");
		}
		if(listComputerParameter.getTaillePage().isEmpty()) {
			listComputerParameter.setTaillePage("20");
		}
	}
	
}
