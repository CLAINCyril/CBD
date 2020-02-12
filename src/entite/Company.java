package entite;

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

public class Company {
	private int Id;
	private String name;
	

	public Company() {
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

	public Company(int id) {
		Id = id;
	}

	public static class CompanyBuilder {
		private int Id;
		private String name;

		public CompanyBuilder Id(int Id) {
			this.Id = Id;
			return this;
		}

		public CompanyBuilder name(String name) {
			this.name = name;
			return this;
		}

		public Company build() {
			return new Company(this);
		}
	}

	private Company(CompanyBuilder companyBuilder) {
		this.Id = companyBuilder.Id;
		this.name = companyBuilder.name;
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
		Company other = (Company) obj;
		if (Id != other.Id)
			return false;
		return true;
	}
}
