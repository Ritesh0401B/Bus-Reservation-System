package brs.DaoImpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import brs.Dao.BusDao;
import brs.Exception.BusException;
import brs.model.Bus;
import brs.utility.DBConnection;

public class BusDaoImpl implements BusDao {

	@Override
	public void addBus(Bus bus) throws BusException {
		String addQuery = "INSERT INTO Bus (busName, source, destination, totalSeats, availableSeats, ticketPrice) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection()) {

			PreparedStatement ptmt = con.prepareStatement(addQuery);

			ptmt.setString(1, bus.getBusName());
			ptmt.setString(2, bus.getSource());
			ptmt.setString(3, bus.getDestination());
			ptmt.setInt(4, bus.getTotalSeats());
			ptmt.setInt(5, bus.getAvailableSeats());
			ptmt.setDouble(6, bus.getTicketPrice());
			

			int affectedRow = ptmt.executeUpdate();

			if (affectedRow > 0) {
				System.out.println("Bus Added Successfuly!!");
			}

		} catch (SQLException e) {
			throw new BusException("Bus not Added!!");
		}

	}

	@Override
	public List<Bus> getAllBuses() throws BusException {
		List<Bus> buses = new ArrayList<>();
		String getAllQuery = "SELECT * FROM Bus";

		try (Connection con = DBConnection.getConnection()) {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(getAllQuery);

			while (rs.next()) {
				Bus bus = new Bus(rs.getInt("busId"), rs.getString("busName"), rs.getString("source"),
						rs.getString("destination"), rs.getInt("totalSeats"), rs.getDouble("ticketPrice"));
				bus.setAvailableSeats(rs.getInt("availableSeats"));
				buses.add(bus);

			}

		} catch (SQLException e) {
			throw new BusException("Database error: " + e.getMessage());
		}

		return buses;
	}

	@Override
	public void updateAvailableSeats(int busId, int seats) throws BusException {

		String updateQuery = "UPDATE Bus SET available_seats = ? WHERE busId = ?";

		try (Connection con = DBConnection.getConnection()) {

			PreparedStatement ptmt = con.prepareStatement(updateQuery);
			ptmt.setInt(1, seats);
			ptmt.setInt(2, busId);
			int rowsAffected = ptmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Seats Updated Successfuly!!");
			} else {
				throw new BusException("Bus With ID " + busId + "not found.");
			}

		} catch (SQLException e) {
			throw new BusException("Database error: " + e.getMessage());
		}

	}

	@Override
	public void deleteBus(int busId) throws BusException {

		String deleteQuery = "DELETE FROM Bus WHERE busId = ?";

		try (Connection con = DBConnection.getConnection()) {
			PreparedStatement ptmt = con.prepareStatement(deleteQuery);

			ptmt.setInt(1, busId);
			int rowsAffected = ptmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Bus with ID " + busId + " deleted successfully.");
			} else {
				throw new BusException("Bus with ID " + busId + " not found.");
			}

		} catch (SQLException e) {
			throw new BusException("Database error: " + e.getMessage());
		}

	}

	@Override
	public Bus getBusById(int busId) throws BusException {

		String getQuery = "SELECT * FROM Bus WHERE busId = ?";

		try (Connection con = DBConnection.getConnection()) {

			PreparedStatement ptmt = con.prepareStatement(getQuery);
			ptmt.setInt(1, busId);

			try (ResultSet rs = ptmt.executeQuery()) {

				if (rs.next()) {
					Bus bus = new Bus(rs.getInt("busId"), rs.getString("busName"), rs.getString("source"),
							rs.getString("destination"), rs.getInt("totalSeats"), rs.getDouble("ticketPrice"));
					bus.setAvailableSeats(rs.getInt("availableSeats"));
					return bus;
				} else {
					throw new BusException("No bus found with ID " + busId);
				}

			}

		} catch (SQLException e) {
			throw new BusException("Database error: " + e.getMessage());
		}
		

	}

	@Override
	public void updateBusDetails(Bus bus) throws BusException {
		try (Connection conn = DBConnection.getConnection()) {

			String sql ="UPDATE Bus SET busName = ?, source = ?, destination = ?, totalSeats = ?, availableSeats = ?, ticketPrice = ? WHERE busId = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bus.getBusName());
			ps.setString(2, bus.getSource());
			ps.setString(3, bus.getDestination());
			ps.setInt(4, bus.getTotalSeats());
			ps.setInt(5, bus.getAvailableSeats());
			ps.setDouble(6, bus.getTicketPrice());
			ps.setInt(7, bus.getBusId());
            

			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated == 0) {
				throw new BusException("Bus with not found.");
			} else {
				System.out.println("Bus details updated successfully.");
			}
		} catch (SQLException e) {
			throw new BusException("Database error: " + e.getMessage());

		}
	}

	@Override
	public void generateReport() throws SQLException {
		 String sql = "SELECT * FROM Bus";
	        try (Connection con = DBConnection.getConnection();
	             Statement stmt = con.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            System.out.println("Bus Report:");
	            while (rs.next()) {
	                System.out.println("Bus ID: " + rs.getInt("busId") + ", Name: " + rs.getString("busName") +
	                        ", Source: " + rs.getString("source") + ", Destination: " + rs.getString("destination") +
	                        ", Available Seats: " + rs.getInt("availableSeats"));
	            }
	        }

	}

	@Override
	public void manageUsers() {
		// TODO Auto-generated method stub

	}

}
