package fr.excilys.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import fr.excilys.DTO.ComputerDTO;
import fr.excilys.DTO.ListComputerParameter;
import fr.excilys.model.Computer;
import fr.excilys.service.Page;
import fr.excilys.service.ServiceComputer;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/computers")
public class ControllerComputer {

	
	public ServiceComputer serviceComputer;
	private int startItemPage = 0;
	private int lastItemPage = 20;

	public ControllerComputer(ServiceComputer serviceComputer) {
		this.serviceComputer = serviceComputer;
	}
	
	@GetMapping
	public ResponseEntity<List<ComputerDTO>> getDashboard(ListComputerParameter listComputerParameter) {
		List<Computer> computerList = new ArrayList<Computer>();

		Page page = serviceComputer.getFirstPage(listComputerParameter.getPageIterator(),
				listComputerParameter.getTaillePage(), startItemPage, lastItemPage);

		computerList = serviceComputer.getPage(listComputerParameter.getOrder(),
				listComputerParameter.getSearch(), page);
		List<ComputerDTO> computerDTOList = serviceComputer.mapComputerToDTOList(computerList);

		ResponseEntity<List<ComputerDTO>> responseEntitycomputerDTOList = 
				new ResponseEntity<List<ComputerDTO>>(computerDTOList,HttpStatus.OK);


		return responseEntitycomputerDTOList;

	}
	

	@GetMapping("/{id}")
	public ResponseEntity getComputer(@PathVariable String id) {
		
		Optional<Computer> Optionalcomputer = serviceComputer.getComputer(Integer.valueOf(id));		
		Optional<ComputerDTO> computerDTO = Optionalcomputer.map(computer ->  serviceComputer.mapFromComputerToDTO(computer));
		if( computerDTO.isPresent()) {
		
		ResponseEntity<ComputerDTO> responseEntitycomputerDTOList = 
				new ResponseEntity<ComputerDTO>(computerDTO.get(),HttpStatus.OK);
		
		return responseEntitycomputerDTOList;

		}
		
		return ResponseEntity.notFound().build();


	}
	
	
	@PostMapping
	public ResponseEntity createComputer(@RequestBody ComputerDTO computerDTO ){
		Computer computer  = serviceComputer.mapComputerDTOToComputer(computerDTO);
		serviceComputer.persisteComputer(computer);
		return new ResponseEntity(HttpStatus.CREATED);
		
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity updateComputer(@RequestBody ComputerDTO computerDTO ){
		Computer computer  = serviceComputer.mapComputerDTOToComputer(computerDTO);
		serviceComputer.updateComputer(computer);
		return new ResponseEntity(HttpStatus.ACCEPTED);


	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteComputer(@PathVariable("id") String id){
		serviceComputer.deleteComputer(Integer.valueOf(id));

		return new ResponseEntity(HttpStatus.ACCEPTED);

	}
	
}
