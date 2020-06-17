package fr.excilys.DTO;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserDTO {

	public UserDTO() {
	}

	private String name;
	private String role;
	private String password;

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.name).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (name != other.name)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	public static class Builder {
		private String name;
		private String role;
		private String password;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder role(String role) {
			this.role = role;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public UserDTO build() {
			return new UserDTO(this);
		}
	}

	private UserDTO(Builder builder) {
		this.name = builder.name;
		this.role = builder.role;
		this.password = builder.password;
	}
}