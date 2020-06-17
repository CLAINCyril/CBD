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

/**
 * Nom de classe : Company Modele
 *
 * Description   : Modele company (MVC) contient les méthode a appliquer
 *
 * Version       : 1.0
 *
 * Date          : 10/02/2020
 *
 * Copyright     : CLAIN Cyril
 */

@Entity
@Table(name = "company")
public class Company {
	
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column
	public String name;
	

	public Company() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company(int id) {
		this.id = id;
	}

	public static class CompanyBuilder {
		private int id;
		private String name;

		public CompanyBuilder setId(int id) {
			this.id = id;
			return this;
		}

		public CompanyBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public Company build() {
			return new Company(this);
		}
	}

	private Company(CompanyBuilder companyBuilder) {
		this.id = companyBuilder.id;
		this.name = companyBuilder.name;
	}

	
	@Override
	public String toString() {
 		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
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
		Company other = (Company) obj;
		return id == other.id;
	}
}
