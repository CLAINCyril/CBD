package entite;

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
	private int IdCompagny;

	public Computer(int id, String name, int idCompagny) {
		Id = id;
		this.name = name;
		IdCompagny = idCompagny;
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

	public int getIdCompagny() {
		return IdCompagny;
	}

	public void setIdCompagny(int idCompagny) {
		IdCompagny = idCompagny;
	}

	public static class Builder {
		private int Id;
		private String name;
		private LocalDateTime introduced;
		private LocalDateTime Discontinued;
		private int IdCompagny;

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

		public Builder IdCompagny(int IdCompagny) {
			this.IdCompagny = IdCompagny;
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
		this.IdCompagny = builder.IdCompagny;
	}
	
	public Computer() {};

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + IdCompagny;
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
		if (IdCompagny != other.IdCompagny)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ComputerModele [Id=" + Id + ", name=" + name + ", introduced=" + introduced + ", Dicontinued="
				+ Discontinued + ", IdCompagny=" + IdCompagny + "]";
	}
}
