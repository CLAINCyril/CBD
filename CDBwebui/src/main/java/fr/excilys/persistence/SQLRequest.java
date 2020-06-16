package fr.excilys.persistence;

public enum SQLRequest {
	PERSISTE_COMPUTER(
			"INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (:computerName, :introduced, :discontinued, :companyId);"),
	DELETE_COMPUTER("DELETE FROM computer WHERE id= :id;"),
	GET_COMPUTER(
			"SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.id = :computer.id;"),
	GET_ALL_COMPUTER(
			"SELECT computer.id, computer.name, introduced , discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id;"),
	GET_PAGE_COMPUTER(
			"SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id  LIMIT :offset,:number;"),
	GET_PAGE_ORDER_BY(
			"SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id ORDER BY "),
	GET_PAGE_COMPUTER_NAME(
			"SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.name LIKE :search LIMIT :offset,:number;"),
	FIND_BY_NAME_PAG("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id,"
			+"company.name FROM computer LEFT JOIN company ON company_id = company.id  WHERE computer.name LIKE :search LIMIT :offset, :step;"),
	DELETE_ALL_COMPUTER_WHERE_COMPANY_EGALE("DELETE FROM computer WHERE company_id = :id;"),
	DELETE_ALL_COMPUTER_BY_ID("DELETE FROM computer WHERE id IN (:id)"),
	UPDATE_COMPUTER("UPDATE computer " + "SET  name= :name, Introduced= :Introduced,"
			+ "Discontinued= :Discontinued, company_id= :company_id WHERE Id= :Id;"),

	PERSISTE_COMPANY("INSERT INTO company name= :name;"), DELETE_COMPANY("DELETE FROM company WHERE id= :id;"),
	GET_By_ID(" SELECT id,name FROM company WHERE id= :id;"),
	UPDATE_COMPANY("UPDATE company SET name(:name WHERE Id= :id;"), GET_ALL_COMPANY("SELECT id,name FROM company;"),
	SELECT_COMPANY_PAGE("SELECT * FROM company LIMIT :limit,:offset ");

	private String query;

	SQLRequest(String query) {
		this.query = query;
	}

	public String getQuery() {
		return this.query;
	}
}