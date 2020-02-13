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
	public Computer getComputer(ResultSet resDetailcomputer) {
		this.computer = new Computer();
		this.company = new Company();
		try {
			computer.setId(resDetailcomputer.getInt("id"));
			computer.setName(resDetailcomputer.getString("ResultSet"));
			computer.setIntroduced(resDetailcomputer.getTimestamp("introduced")!=null?
					resDetailcomputer.getTimestamp("introduced").toLocalDateTime():null);
			computer.setDiscontinued(resDetailcomputer.getTimestamp("discontinued")!=null?
					resDetailcomputer.getTimestamp("discontinued").toLocalDateTime():null);
			company.setId(resDetailcomputer.getInt("company_id"));
			company.setName(resDetailcomputer.getString("company.name"));
			computer.setCompany(company);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return computer;
	}
}
