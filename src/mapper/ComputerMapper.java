package mapper;

import modele.Company;
import modele.Computer;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ComputerMapper {
	Computer computer;
	Company company;
	private static volatile ComputerMapper instance = null;
		
	private ComputerMapper() {
		
	}
		
	public final static ComputerMapper getInstance() {
        if (ComputerMapper.instance == null) {
           synchronized(CompanyMapper.class) {
             if (ComputerMapper.instance == null) {
            	 ComputerMapper.instance = new ComputerMapper();
             }
           }
        }
        return ComputerMapper.instance;
}
	public Computer getComputer(ResultSet resDetailcomputer) throws SQLException {
		this.computer = new Computer();
		this.company = new Company();
		if (resDetailcomputer.next()){
			computer.setId(resDetailcomputer.getInt("computer.id"));
			computer.setName(resDetailcomputer.getString("computer.name"));
			computer.setIntroduced(resDetailcomputer.getTimestamp("computer.introduced")!=null?
					resDetailcomputer.getTimestamp("computer.introduced").toLocalDateTime():null);
			computer.setDiscontinued(resDetailcomputer.getTimestamp("discontinued")!=null?
					resDetailcomputer.getTimestamp("computer.discontinued").toLocalDateTime():null);
			company.setId(resDetailcomputer.getInt("company_id"));
			company.setName(resDetailcomputer.getString("company.name"));
			computer.setCompany(company);
		}
		
		return computer;
	}
}
