package entite;

import java.sql.Date;

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
	private Date introduced;
	private Date Dicontinued;
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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDicontinued() {
		return Dicontinued;
	}

	public void setDicontinued(Date dicontinued) {
		Dicontinued = dicontinued;
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
		private Date introduced;
		private Date Dicontinued;
		private int IdCompagny;

		public Builder Id(int Id) {
			this.Id = Id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder introduced(Date introduced) {
			this.introduced = introduced;
			return this;
		}

		public Builder Dicontinued(Date Dicontinued) {
			this.Dicontinued = Dicontinued;
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
		this.Dicontinued = builder.Dicontinued;
		this.IdCompagny = builder.IdCompagny;
	}
	
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
				+ Dicontinued + ", IdCompagny=" + IdCompagny + "]";
	}
}
