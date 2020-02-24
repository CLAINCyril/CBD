package DTO;

public class ComputerDTO {

	public ComputerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int id;
	public String name;
	private String introduced;
	private String discontinued;
	private CompanyDTO company;
	
	public ComputerDTO( String name, String introduced, String discontinued, CompanyDTO company) {
		super();
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public CompanyDTO getCompany() {
		return company;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	
	
}