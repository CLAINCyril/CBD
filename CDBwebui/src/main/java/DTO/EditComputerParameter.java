package DTO;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class EditComputerParameter {
	private String computerId;
	private String computerName;
	private String introduced;
	private String discontinued;
	private String companyId;

	public EditComputerParameter(String computerId, String computerName, String introduced, String discontinued,
			String companyId) {
		this.computerId = computerId;
		this.computerName = computerName;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	public String getComputerId() {
		return computerId;
	}

	public void setComputerId(String computerId) {
		this.computerId = computerId;
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
				.append(this.computerId)
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
		EditComputerParameter other = (EditComputerParameter) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (computerId == null) {
			if (other.computerId != null)
				return false;
		} else if (!computerId.equals(other.computerId))
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
	
	
}