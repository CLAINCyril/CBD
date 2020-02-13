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

	private int id;
	private String name;
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	private Company company;

	public Computer(int id, String name, Company compagny) {
		this.id = id;
		this.name = name;
		this.company = company;
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

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDateTime dicontinued) {
		discontinued = dicontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static class ComputerBuilder {
		private int id;
		private String name;
		private LocalDateTime introduced;
		private LocalDateTime discontinued;
		private Company company;

		public ComputerBuilder id(int id) {
			this.id = id;
			return this;
		}

		public ComputerBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ComputerBuilder introduced(LocalDateTime introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilder Discontinued(LocalDateTime dicontinued) {
			this.discontinued = dicontinued;
			return this;
		}

		public ComputerBuilder idCompagny(Company company) {
			this.company = company;
			return this;
		}

		public Computer build() {
			return new Computer(this);
		}
	}

	private Computer(ComputerBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.introduced = builder.introduced;
		this.discontinued = builder.discontinued;
		this.company = builder.company;
	}
	
	public Computer() {};



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computer [Id=" + id + ", name=" + name + ", introduced=" + introduced + ", Discontinued=" + discontinued
				+ ", company=" + company + "]\n";
	}


}
