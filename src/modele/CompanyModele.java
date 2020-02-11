package modele;

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

public class CompanyModele {
	private int Id;
	private String name;

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

	public CompanyModele(int id) {
		Id = id;
	}

	public static class Builder {
		private int Id;
		private String name;

		public Builder Id(int Id) {
			this.Id = Id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public CompanyModele build() {
			return new CompanyModele(this);
		}
	}

	private CompanyModele(Builder builder) {
		this.Id = builder.Id;
		this.name = builder.name;
	}

	
	@Override
	public String toString() {
		return "CompanyModele [Id=" + Id + ", name=" + name + "]";
	}

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
		CompanyModele other = (CompanyModele) obj;
		if (Id != other.Id)
			return false;
		return true;
	}
}
