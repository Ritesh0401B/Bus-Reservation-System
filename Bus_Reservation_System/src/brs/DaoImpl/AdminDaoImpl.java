package brs.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import brs.Dao.AdminDao;
import brs.Exception.AdminException;
import brs.utility.DBConnection;

public class AdminDaoImpl implements AdminDao {

	@Override
	public boolean validateAdmin(String userName, String password) throws AdminException {
		 try (Connection conn = DBConnection.getConnection()) {
	            String query = "SELECT * FROM Admin WHERE username = ? AND password = ?";
	            PreparedStatement ps = conn.prepareStatement(query);
	            ps.setString(1, userName);
	            ps.setString(2, password);
	            ResultSet rs = ps.executeQuery();
	            return rs.next();  // Returns true if a match is found
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}

	@Override
	public void registerAdmin(String userName, String password) throws AdminException {
		 try (Connection conn = DBConnection.getConnection()) {
		        String query = "INSERT INTO Admin (username, password) VALUES (?, ?)";
		        PreparedStatement ps = conn.prepareStatement(query);
		        ps.setString(1, userName);
		        ps.setString(2, password);
		        ps.executeUpdate();
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
