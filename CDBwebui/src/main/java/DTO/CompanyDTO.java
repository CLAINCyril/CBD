package DTO;

public class CompanyDTO {

	private int id;
	private String name;
	
	public CompanyDTO(int id) {
		this.id=id;
	}
	public CompanyDTO() {
		super();
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}