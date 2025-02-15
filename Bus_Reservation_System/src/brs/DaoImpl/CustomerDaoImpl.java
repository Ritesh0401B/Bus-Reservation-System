package brs.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import brs.Dao.CustomerDao;
import brs.Exception.CustomerException;
import brs.model.Customer;
import brs.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void registerCustomer(Customer customer) throws CustomerException {
		 try (Connection conn = DBConnection.getConnection()) {
		        String query = "INSERT INTO Customer (name, email, phone) VALUES (?, ?, ?)";
		        PreparedStatement ps = conn.prepareStatement(query);
		        ps.setString(1, customer.getName());
		        ps.setString(2, customer.getEmail());
		        ps.setString(3, customer.getPhone());
		        ps.executeUpdate();
		        
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		 
	}

	@Override
	public Customer getCustomerById(int customerId) throws CustomerException {
		String sql = "SELECT * FROM Customer WHERE customerId = ?";

		try (Connection conn = DBConnection.getConnection();) {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new Customer(rs.getInt("customerId"), rs.getString("name"), rs.getString("email"),
							rs.getString("phone"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerException {
		List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM Customer";
		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("customerId"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public void updateCustomer(Customer customer) throws CustomerException {
		String sql = "UPDATE Customer SET name = ?, email = ?, phone = ? WHERE customerId = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getEmail());
			pstmt.setString(3, customer.getPhone());
			pstmt.setInt(4, customer.getCustomerId());
			pstmt.executeUpdate();
			System.out.println("Customer details updated successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCustomer(int customerId) throws CustomerException {
		String sql = "DELETE FROM Customer WHERE customerId = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
			System.out.println("Customer deleted successfully.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean validateCustomer(int customerId) throws CustomerException {
		String sql = "SELECT 1 FROM Customer WHERE customerId = ?";
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next(); // Returns true if customer exists

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	

}
