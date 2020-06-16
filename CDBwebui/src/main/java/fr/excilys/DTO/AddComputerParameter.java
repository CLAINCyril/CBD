package fr.excilys.DTO;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AddComputerParameter {

	private String computerName;
	private String introduced;
	private String discontinued;
	private String companyId;

	public AddComputerParameter(String computerName, String introduced, String discontinued, String companyId) {
		this.computerName = computerName;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(this.companyId)
				.append(this.computerName)
				.append(this.discontinued)
				.append(this.introduced)
				.toHashCode();

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddComputerParameter other = (AddComputerParameter) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (computerName == null) {
			if (other.computerName != null)
				return false;
		} else if (!computerName.equals(other.computerName))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);

	}
	
}