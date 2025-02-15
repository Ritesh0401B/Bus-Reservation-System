package brs.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String url = "jdbc:mysql://localhost:3306/Bus_Reservation_System";
	private static final String userName = "root";
	private static final String password = "R0206@br%";
	
	public static Connection getConnection() throws SQLException {
	
		Connection con = DriverManager.getConnection(url, userName, password);
		
		return con; 
			
	}

}
