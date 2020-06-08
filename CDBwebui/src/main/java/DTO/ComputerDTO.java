package DTO;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ComputerDTO {

	public ComputerDTO() {}
	
	private int id;
	private String name;
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
	
	public ComputerDTO(int id, String name, String introduced, String discontinued, CompanyDTO company) {
		super();
		this.id = id;
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
	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.id).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDTO other = (ComputerDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
	
}