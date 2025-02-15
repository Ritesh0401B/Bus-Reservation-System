package brs.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import brs.Dao.BookingDao;
import brs.Exception.BookingException;
import brs.model.Booking;
import brs.utility.DBConnection;

public class BookingDaoImpl implements BookingDao {

	@Override
	public void addBooking(Booking booking) throws BookingException {
		String sql = "INSERT INTO Booking (busId, customerId, seatsBooked) VALUES (?, ?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, booking.getBusId());
			pstmt.setInt(2, booking.getCustomerId());
			pstmt.setInt(3, booking.getSeatsBooked());
			pstmt.executeUpdate();
			System.out.println("Booking added successfully.");

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	@Override
	public Booking getBookingById(int bookingId) throws BookingException {
		
		String sql = "SELECT * FROM Booking WHERE bookingId = ?";
		
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bookingId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new Booking(rs.getInt("bookingId"), 
							rs.getInt("busId"), 
							rs.getInt("customerId"),
							rs.getInt("seatsBooked"), 
							rs.getTimestamp("bookingTime").toLocalDateTime());
				}
			}
		} catch (SQLException e) { 
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Booking> getBookingsByCustomerId(int customerId) throws BookingException {
		 List<Booking> bookings = new ArrayList<>();
		 
	        String sql = "SELECT * FROM Booking WHERE customerId = ?";
	        
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, customerId);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    Booking booking = new Booking(
	                            rs.getInt("bookingId"),
	                            rs.getInt("busId"),
	                            rs.getInt("customerId"),
	                            rs.getInt("seatsBooked"),
	                            rs.getTimestamp("bookingTime").toLocalDateTime()
	                    );
	                    bookings.add(booking);
	                }
	            }
	        } catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
	        return bookings;
	}

	@Override
	public List<Booking> getBookingsByBusId(int busId) throws BookingException {
		 List<Booking> bookings = new ArrayList<>();
		 
	        String sql = "SELECT * FROM Booking WHERE busId = ?";
	        
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, busId);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    Booking booking = new Booking(
	                            rs.getInt("bookingId"),
	                            rs.getInt("busId"),
	                            rs.getInt("customerId"),
	                            rs.getInt("seatsBooked"),
	                            rs.getTimestamp("bookingTime").toLocalDateTime()
	                    );
	                    bookings.add(booking);
	                }
	            }
	        } catch (SQLException e) {
	        	System.out.println("Error: " + e.getMessage());
			}
	        return bookings;
	}

	@Override
	public List<Booking> getAllBookings() throws BookingException {
		List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("bookingId"), 
                        rs.getInt("busId"),
                        rs.getInt("customerId"),
                        rs.getInt("seatsBooked"),
                        rs.getTimestamp("bookingTime").toLocalDateTime()
                );
                bookings.add(booking);
            }
        } catch (SQLException e) {
        	System.out.println("Error: " + e.getMessage());
		}
        return bookings;
	}

	@Override
	public void cancelBooking(int bookingId) throws BookingException {
		
		 String sql = "DELETE FROM Booking WHERE bookingId = ?";
		 
	        try (Connection conn = DBConnection.getConnection();
	        		
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, bookingId);
	            pstmt.executeUpdate();
	            
	            System.out.println("Booking deleted successfully.");
	            
	        } catch (SQLException e) {
	        	System.out.println("Error: " + e.getMessage());

			}

	}

}
