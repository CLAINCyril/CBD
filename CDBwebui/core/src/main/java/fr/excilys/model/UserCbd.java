package fr.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class UserCbd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "name")
	String name;

	@Column(name = "password")
	String password;

	@Column(name = "role")
	String role;

	public UserCbd() {
		
	}
	
	
	public static class Builder {
		private String name;
		private String password;
		private String role;

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

		public UserCbd build() {
			return new UserCbd(this);
		}
	}

	public UserCbd(Builder builder) {
		this.role = builder.role;
		this.name = builder.name;
		this.password = builder.password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
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
		UserCbd other = (UserCbd) obj;
		if (name != other.name)
			return false;
		return true;
	}

}
