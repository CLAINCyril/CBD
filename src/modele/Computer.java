package modele;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Nom de classe : ComputerModele
 *
 * Description   : Modele Computer (MVC) contient les méthode a appliquer
 *
 * Version       : 1.0
 *
 * Date          : 10/02/2020
 *
 * Copyright     : CLAIN Cyril
 */

public class Computer {

	private int Id;
	private String name;
	private LocalDateTime introduced;
	private LocalDateTime Discontinued;
	private Company company;

	public Computer(int id, String name, Company compagny) {
		Id = id;
		this.name = name;
		this.company = company;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	public LocalDateTime getDiscontinued() {
		return Discontinued;
	}

	public void setDiscontinued(LocalDateTime dicontinued) {
		Discontinued = dicontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static class Builder {
		private int Id;
		private String name;
		private LocalDateTime introduced;
		private LocalDateTime Discontinued;
		private Company company;

		public Builder Id(int Id) {
			this.Id = Id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder introduced(LocalDateTime introduced) {
			this.introduced = introduced;
			return this;
		}

		public Builder Discontinued(LocalDateTime Dicontinued) {
			this.Discontinued = Dicontinued;
			return this;
		}

		public Builder IdCompagny(Company company) {
			this.company = company;
			return this;
		}

		public Computer build() {
			return new Computer(this);
		}
	}

	private Computer(Builder builder) {
		this.Id = builder.Id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.Discontinued = builder.Discontinued;
		this.company = builder.company;
	}
	
	public Computer() {};



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (Id != other.Id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computer [Id=" + Id + ", name=" + name + ", introduced=" + introduced + ", Discontinued=" + Discontinued
				+ ", company=" + company + "]";
	}

}
